package fieldworks.worker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fieldworks.models.CSVFiles;
import fieldworks.repository.CSVFileRepository;

@Component
public class FileProcessor {

	@Autowired
    private CSVFileRepository cSVFileRepository;

    public void process(Long recordId, String inputPath, String outputDir, String jobId) {
        Optional<CSVFiles> optional = cSVFileRepository.findById(recordId);
        if (optional.isEmpty()) return;
        CSVFiles record = optional.get();

        try {
            record.setStatus("PROCESSING");
            cSVFileRepository.save(record);

            List<String> lines = Files.readAllLines(Paths.get(inputPath));
            if (lines.isEmpty()) throw new RuntimeException("Empty input file");

            String header = lines.get(0);
            List<String> processed = new ArrayList<>();
            processed.add(header + ",sum");

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                double a = Double.parseDouble(parts[1]);
                double b = Double.parseDouble(parts[2]);
                processed.add(lines.get(i) + "," + (a + b));
            }

            Files.createDirectories(Paths.get(outputDir));
            Path outputPath = Paths.get(outputDir, "processed_" + Paths.get(inputPath).getFileName());
            Files.write(outputPath, processed);

            record.setStatus("SUCCESS");
            record.setMessage("Processed successfully");
            cSVFileRepository.save(record);
        } catch (Exception e) {
            record.setStatus("FAILED");
            record.setMessage("Error: " + e.getMessage());
            cSVFileRepository.save(record);
        }
    }
}
