package fieldworks.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.exception.CustomException;
import fieldworks.models.Employee;
import fieldworks.models.EmployeeDocument;
import fieldworks.repository.EmployeeDocumentRepository;
import fieldworks.repository.EmployeeRepository;

@Service
//@Transactional
public class EmployeeDocumentService {

//	private static final String BASE_PATH = "uploads/employees/";
	 @Value("${BASE_PATH}")
	 private String BASE_PATH;
	

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private EmployeeDocumentRepository docRepo;

	public EmployeeDocument upload(Long employeeId, MultipartFile file) {
		if (file.isEmpty()) {
			throw CustomException.badRequest("File is empty");
		}

		if (file.getSize() > 5 * 1024 * 1024) {
			throw CustomException.badRequest("File size exceeds 5MB");
		}

		Employee emp = employeeRepo.findById(employeeId)
				.orElseThrow(() -> CustomException.notFound("Employee not found"));

		try {
			File dir = new File(BASE_PATH + employeeId);
			if (!dir.exists())
				dir.mkdirs();

			String maindir = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
			File filePath = new File(maindir);

			file.transferTo(filePath);   //save file in filePath

			EmployeeDocument doc = new EmployeeDocument();
			doc.setFileName(file.getOriginalFilename());
			doc.setFileType(file.getContentType());
			doc.setFileSize(file.getSize());
			doc.setFilePath(maindir);
			doc.setEmployee(emp);

			return docRepo.save(doc);

		} catch (Exception e) {
			throw CustomException.internalError("File upload failed");
		}
	}

	public File getFile(Long docId) {
		EmployeeDocument doc = docRepo.findById(docId)
				.orElseThrow(() -> CustomException.notFound("Document not found"));

		return new File(doc.getFilePath());
	}
}
