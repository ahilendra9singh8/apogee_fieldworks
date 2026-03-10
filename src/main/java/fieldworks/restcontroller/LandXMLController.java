package fieldworks.restcontroller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.dto.ApiResponse;
import fieldworks.exception.CustomException;
import fieldworks.services.LandProcessor;

@RestController
@RequestMapping("/api")
public class LandXMLController {

	@Autowired
	private LandProcessor landProcessor;

	@PostMapping("/generateLandXML")
	public ResponseEntity<ApiResponse<Object>> generateLandXML(@RequestParam("clFile") MultipartFile clFile,
			@RequestParam("frlFile") MultipartFile frlFile) {

		try {
			File xmlFile = landProcessor.processFiles(clFile, frlFile);
			if (xmlFile == null || !xmlFile.exists()) {
				throw CustomException.notFound("xmlFile not found");
			}
			String xmlContent = new String(Files.readAllBytes(xmlFile.toPath()), StandardCharsets.UTF_8);
			Map<String, Object> xmlFileObj = new HashMap<>();
			xmlFileObj.put("fileName", xmlFile.getName());
			xmlFileObj.put("content", xmlContent);
			xmlFileObj.put("contentType", "application/xml");
			xmlFileObj.put("size", xmlFile.length());

			Map<String, Object> payload = new HashMap<>();
			payload.put("xmlFile", xmlFileObj);
			payload.put("message", "File attached as 'xmlFile'");

			return ResponseEntity.ok(ApiResponse.success("LandXML generated successfully", payload));

		} catch (IOException e) {
			throw CustomException.internalError("Failed to read XML file: " + e.getMessage());
		}
	}

}
