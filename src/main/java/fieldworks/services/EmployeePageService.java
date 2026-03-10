package fieldworks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fieldworks.exception.CustomException;
import fieldworks.models.Employee;
import fieldworks.repository.EmployeeRepository;

@Service
public class EmployeePageService {

	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/* LOGIN (JSP) */
	public Employee login(String username, String password) {

		Employee emp = repo.findByUserName(username).orElseThrow(() -> CustomException.notFound("User not found"));

		if (!passwordEncoder.matches(password, emp.getPassword())) {
			throw CustomException.unauthorized("Invalid credentials");
		}

		if (!emp.getActive()) {
			throw CustomException.forbidden("User is inactive");
		}

		return emp;
	}

	// crud

	public List<Employee> getAll() {
		return repo.findAll();
	}

	public Employee getById(Long id) {
		return repo.findById(id).orElseThrow(() -> CustomException.notFound("Employee not found"));
	}

	public void save(Employee emp) {
		repo.save(emp);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
