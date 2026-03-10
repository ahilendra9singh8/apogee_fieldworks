package fieldworks.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.models.CSVFiles;
import fieldworks.repository.CSVFileRepository;
import fieldworks.services.CSVFileService;


@RestController
@RequestMapping("/api/upload")
public class CSVFileController {

	@Autowired
	private CSVFileService cSVFileService;

	@Autowired
	private CSVFileRepository repository;

	@PostMapping("/csv")
	public ResponseEntity<?> uploadCsv(@RequestParam("files") List<MultipartFile> files) {
		List<Map<String, Object>> results = new ArrayList<>();
		for (MultipartFile file : files) {
			Map<String, Object> result = cSVFileService.processCSVFile(file);
			results.add(result);
		}
		return ResponseEntity.ok(Map.of("title", "Upload Result", "message",
				"Files processed with individual statuses.", "payload", results, "responseCode", 200));
	}

	@GetMapping("/job/{jobId}")
	public ResponseEntity<?> getJobStatus(@PathVariable String jobId) {
		Optional<CSVFiles> rec = repository.findByJobId(jobId);
		if (rec.isEmpty()) {
			return ResponseEntity.status(404).body(
					Map.of("title", "Not Found", "message", "Job ID not found", "payload", null, "responseCode", 404));
		}

		CSVFiles file = rec.get();
		return ResponseEntity
				.ok(Map.of("title", "Job Status", "message", "Job found", "payload", file, "responseCode", 200));
	}
}
