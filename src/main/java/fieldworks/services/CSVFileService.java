package fieldworks.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.models.CSVFiles;
import fieldworks.repository.CSVFileRepository;

@Service
public class CSVFileService {

	public static final List<String> REQUIRED_HEADERS = List.of("Time", "CellN_m", "CellE_m", "Elevation_m",
			"PassNumber", "LastRadioLtncy", "DesignName", "Task", "MeasuredData", "Machine", "Speed_km/h",
			"LastGPSMode", "GPSAccTol_m", "TargPassCount", "ValidPos", "Lift", "LastCMV", "TargCMV", "LastEVIB1",
			"TargEVIB1", "LastEVIB2", "TargEVIB2", "LastMDP", "TargMDP", "LastRMV", "LastFreq_Hz", "LastAmp_mm",
			"TargThickness_m", "MachineGear", "VibeState", "LastTemp_c");

	@Autowired
	private CSVFileRepository repository;
	@Autowired
	private RedissonClient redissonClient;

	@Value("${app.upload-dir}")
	private String uploadDir;

	@Value("${app.output-dir}")
	private String outputDir;

	@Value("${app.log-dir}")
	private String logDir;

	public Map<String, Object> processCSVFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		Map<String, Object> response = new HashMap<>();
		try {
			logToFile(logDir, fileName, "Received upload request");

			if (fileName == null || !fileName.endsWith(".csv")) {
				return fail(fileName, "Only .csv files are allowed.");
			}

			validateCsv(file);

			String checksum = calculateSHA256(file);
			if (repository.findByChecksum(checksum).isPresent()) {
				return fail(fileName, "Duplicate file found in database.");
			}

			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
			String savedName = fileName.replace(".csv", "_" + timestamp + ".csv");
			Path savedPath = Paths.get(uploadDir, savedName);
			Files.createDirectories(savedPath.getParent());
			file.transferTo(savedPath);

			String jobId = UUID.randomUUID().toString();
			CSVFiles record = new CSVFiles();
			record.setFileName(savedName);
			record.setFilePath(savedPath.toString());
			record.setChecksum(checksum);
			record.setJobId(UUID.randomUUID().toString());
			record.setStatus("QUEUED");
			repository.save(record);

			RQueue<Long> queue = redissonClient.getQueue("file-processing");
			queue.add(record.getId());
			logToFile(logDir, fileName, "Enqueued job " + jobId);

			response.put("title", "Queued");
			response.put("file_name", fileName);
			response.put("status", "QUEUED");
			response.put("job_id", jobId);
			response.put("responseCode", 202);
		} catch (Exception e) {
			response = fail(fileName, e.getMessage());
		}
		return response;
	}

	private Map<String, Object> fail(String fileName, String message) {
		logToFile(logDir, fileName, message);
		return Map.of("file_name", fileName, "status", "FAILED", "message", message, "responseCode", 400);
	}

	public static void logToFile(String logDir, String fileName, String message) {
		try {
			Files.createDirectories(Paths.get(logDir));
			String path = logDir + "/" + fileName + ".log";
			String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			Files.writeString(Paths.get(path), "[" + ts + "] " + message + "\n", StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.err.println("Failed to write log: " + e.getMessage());
		}
	}

	public static boolean validateCsv(MultipartFile file) {
		try (BOMInputStream bomInputStream = new BOMInputStream(file.getInputStream());
				InputStreamReader isr = new InputStreamReader(bomInputStream, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(isr)) {
			CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build();

			try (CSVParser parser = new CSVParser(reader, format)) {
				// Normalize header keys (trim + case insensitive)
				Set<String> headers = parser.getHeaderMap().keySet().stream().map(String::trim).map(String::toLowerCase)
						.collect(Collectors.toSet());

				List<String> missing = REQUIRED_HEADERS.stream().filter(h -> !headers.contains(h.toLowerCase()))
						.toList();

				if (!missing.isEmpty()) {
					throw new IllegalArgumentException("Missing headers: " + missing);
				}

				List<CSVRecord> rows = parser.getRecords();
				if (rows.isEmpty())
					throw new IllegalArgumentException("CSV has no data rows.");

				CSVRecord first = rows.get(0);
				if (first.get("Time").isBlank() || first.get("Machine").isBlank())
					throw new IllegalArgumentException("Required data missing in first row (Time or Machine).");

				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("CSV validation failed: " + e.getMessage());
		}
	}

	public static String calculateSHA256(MultipartFile file) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		try (InputStream is = file.getInputStream()) {
			byte[] buffer = new byte[4096];
			int read;
			while ((read = is.read(buffer)) != -1) {
				digest.update(buffer, 0, read);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : digest.digest())
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}