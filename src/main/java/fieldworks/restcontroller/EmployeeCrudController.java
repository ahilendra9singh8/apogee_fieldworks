package fieldworks.restcontroller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.dto.ApiResponse;
import fieldworks.exception.CustomException;
import fieldworks.models.Employee;
import fieldworks.models.EmployeeDocument;
import fieldworks.repository.EmployeeRepository;
import fieldworks.services.EmployeeCrudService;
import fieldworks.services.EmployeeDocumentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/employees")
public class EmployeeCrudController {
	
	 @Value("${product.path.images}")
	 private String productImagesPath;

	@Autowired
	private EmployeeCrudService service;

	@Autowired
	private EmployeeDocumentService documentService;

	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping("/dummy")
	public String dummy() {
		return "Hiiiii";
	}

	/* CREATE → ADMIN */
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Employee>> create(@Valid @RequestBody Employee emp) {
		Employee respemp = service.create(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created("Employee created", respemp));
	}

	/* READ → ADMIN + USER */
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ApiResponse<Employee>> get(@PathVariable @Min(1) Long id) {
		Employee getEmpById = service.getById(id);
		return ResponseEntity.ok(ApiResponse.success("Employee fetched", getEmpById));
	}

	/* UPDATE → ADMIN */
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Employee>> update(@PathVariable Long id, @RequestBody Employee emp) {
		Employee updateEmp = service.update(id, emp);
		return ResponseEntity.ok(ApiResponse.success("Employee updated", updateEmp));
	}

	/* DELETE (SOFT) → ADMIN */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
		service.deactivate(id);
		return ResponseEntity.ok(ApiResponse.success("Employee deactivated", null));
	}

//	http://localhost:8090/api/employees?page=0&size=10
	/* PAGINATION */
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse<List<Employee>>> list(@RequestParam int page, @RequestParam int size) {
		List<Employee> getEmployeeByPagination = service.getAll(page, size);
		return ResponseEntity.ok(ApiResponse.success("Employee list", getEmployeeByPagination));
	}

//	Image/File upload
	@PostMapping("/{id}/upload")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<ApiResponse<EmployeeDocument>> uploadFile(@PathVariable @Min(1) Long id,
			@RequestParam("file") MultipartFile file) {
		EmployeeDocument uploadDocument = documentService.upload(id, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created("File uploaded", uploadDocument));
	}

//	Image/File download
	@GetMapping("/document/{docId}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Resource> download(@PathVariable Long docId) {

		File file = documentService.getFile(docId);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(new FileSystemResource(file));
	}

	// Some other Apis for understanding about exception like, throw, throws,
	// try/catch, custom exception

//	throw CustomException (NO try-catch)
	@GetMapping("/demo/throw/{id}")
	public ResponseEntity<ApiResponse<Employee>> demoThrow(@PathVariable Long id) {
		Employee emp = employeeRepo.findById(id)
				.orElseThrow(() -> CustomException.notFound("Employee not found with id " + id));
		return ResponseEntity.ok(ApiResponse.success("Employee found", emp));
	}

//	throws CustomException (Method signature)
	@GetMapping("/demo/throws")
	public ResponseEntity<ApiResponse<String>> demoThrows(@RequestParam String role) throws CustomException {
		if (role.length() < 2) {
			throw CustomException.invalidData("Role name too short");
		}
		return ResponseEntity.ok(ApiResponse.success("Valid role", role));
	}

//	API-3 : try-catch + CustomException
	@GetMapping("/demo/try-catch")
	public ResponseEntity<ApiResponse<String>> demoTryCatch() {
		try {
			int a = 10 / 0; // force error
			return ResponseEntity.ok(ApiResponse.success("Success", "No error"));
		} catch (ArithmeticException ex) {
			throw CustomException.internalError("Calculation failed: " + ex.getMessage());
		}
	}

}
