package fieldworks.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fieldworks.dto.ApiResponse;
import fieldworks.dto.EmployeeDTO;
import fieldworks.models.Employee;
import fieldworks.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

//	http://localhost:8090/employee/register

//	{
//		  "name": "Ajeet Sharma",
//		  "user_name": "ajeet123",
//		  "password": "ajeet@123",
//		  "contact_number": "9876543211",
//		  "contact_email": "ajeet.sharma@example.com",
//		  "auth_token": null,
//		  "active": true,
//		  "isAvailable": true,
//		  "created_by": 1,
//		  "remark": "New employee",
//		  "role": "stakeholder",
//		  "superior_fk_id": null,
//		  "privilege": "user"
//		}

	// CONTROLLER LEVEL VALIDATION (@Valid)
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<Employee>> register(@Valid @RequestBody EmployeeDTO reqEmpDTO) {

		Employee emp = service.register(reqEmpDTO);

		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created("Employee registered", emp));
	}

//	http://localhost:8090/employee/login
//	
//	{
//	    "user_name":"aman123",
//	    "password":"aman@123"
//	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

		Map<String, Object> response = new HashMap<>();

		try {

			Map<String, Object> serviceResponse = service.login(request);

			int code = (int) serviceResponse.get("responseCode");

			return ResponseEntity.status(code == 0 ? 200 : 401).body(serviceResponse);

		} catch (Exception ex) {

			response.put("title", "Error");
			response.put("message", "Internal Server Error");
			response.put("responseCode", 1);

			return ResponseEntity.status(500).body(response);
		}
	}

//	// PATH VARIABLE / REQUEST PARAM VALIDATION (Agar id <= 0 → validation error automatically)
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getEmployee(@PathVariable @Min(1) Long id) {
//
//		return ResponseEntity.ok(ApiResponse.success("Employee fetched", service.getById(id)));
//	}

}
