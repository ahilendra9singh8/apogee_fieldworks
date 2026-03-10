package fieldworks.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fieldworks.dto.EmployeeDTO;
import fieldworks.models.Employee;
import fieldworks.repository.EmployeeRepository;
import fieldworks.security.EmployeePrincipal;
import fieldworks.security.JwtUtil;

@Service
public class EmployeeService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Employee register(EmployeeDTO req) {

		if (repo.findByContactNumber(req.getContact_number()).isPresent()) {
			throw new RuntimeException("User already exists");
		}

		Employee emp = new Employee();
		emp.setName(req.getName());
		emp.setUserName(req.getUser_name());
		emp.setPassword(passwordEncoder.encode(req.getPassword()));
		emp.setContactNumber(req.getContact_number());
		emp.setContactEmail(req.getContact_email());
		emp.setAuthToken(req.getAuth_token());
		emp.setActive(req.getActive());
		emp.setIsAvailable(req.getIsAvailable());
		emp.setRemark(req.getRemark());
		emp.setRole(req.getRole());
		emp.setSuperiorFkId(req.getSuperior_fk_id());
		emp.setPrivilege(req.getPrivilege());

		if (req.getCreated_by() != null)
			emp.setCreatedBy(req.getCreated_by());

		return repo.save(emp);
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Employee> emp = repo.findByUserName(username);

		if (emp.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}

		return new EmployeePrincipal(emp.get());
	}

	public Map<String, Object> login(Map<String, String> request) {

		Map<String, Object> response = new HashMap<>();
		Map<String, Object> payload = new HashMap<>();

		try {
			String username = request.get("user_name");
			String password = request.get("password");

			if (username == null || password == null) {
				response.put("title", "Error");
				response.put("message", "Username & password required");
				response.put("responseCode", 1);
				return response;
			}

			// Fetch user from DB
			Optional<Employee> opt = repo.findByUserName(username);

			if (opt.isEmpty()) {
				response.put("title", "Error");
				response.put("message", "User not found");
				response.put("responseCode", 1);
				return response;
			}

			Employee user = opt.get();

			// Password check (BCrypt)
			if (!passwordEncoder.matches(password, user.getPassword())) {
				response.put("title", "Error");
				response.put("message", "Invalid password");
				response.put("responseCode", 1);
				return response;
			}

			if (user.getRole() == null) {
				response.put("title", "Error");
				response.put("message", "Role not assigned to user");
				response.put("responseCode", 1);
				return response;
			}

			String actualRole = user.getRole().toLowerCase();

			String systemRole;
			if (List.of("pd", "ro", "gm", "cgm", "member").contains(actualRole)) {
				systemRole = "admin";
			} else if (actualRole.equals("stakeholder")) {
				systemRole = "user";
			} else {
				response.put("title", "Error");
				response.put("message", "Invalid role: " + actualRole);
				response.put("responseCode", 1);
				return response;
			}

			// Custom Token Payload
			Map<String, Object> tokenPayload = new HashMap<>();
			tokenPayload.put("user_id", user.getId());
			tokenPayload.put("user_name", user.getUserName());
			tokenPayload.put("role", "ROLE_" + systemRole.toUpperCase());

			String token = jwtUtil.generateCustomToken(tokenPayload, user.getUserName());

			payload.put("token", token);

			response.put("title", "Success");
			response.put("message", "Login successful");
			response.put("payload", payload);
			response.put("responseCode", 0);

			return response;

		} catch (Exception e) {
			response.put("title", "Error");
			response.put("message", "Internal Server Error");
			response.put("responseCode", 1);
			return response;
		}
	}

}
