package fieldworks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fieldworks.exception.CustomException;
import fieldworks.models.Employee;
import fieldworks.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@Service
//@Transactional
public class EmployeeCrudService {

	@Autowired
	private EmployeeRepository employeeRepo;

	/* CREATE */
	public Employee create(Employee emp) {
		return employeeRepo.save(emp);
	}

	/* READ */
	public Employee getById(Long id) {
		Employee findById = employeeRepo.findById(id).orElseThrow(() -> CustomException.notFound("Employee not found"));

//		without customexception
//		 Employee findById = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
		return findById;
	}

	/* UPDATE */
	public Employee update(Long id, Employee updated) {
		Employee emp = getById(id);
		emp.setName(updated.getName());
		emp.setRole(updated.getRole());
		emp.setPrivilege(updated.getPrivilege());
		emp.setRemark(updated.getRemark());
		return employeeRepo.save(emp);
	}

	/* SOFT DELETE */
	public void deactivate(Long id) {
		Employee emp = getById(id);
		emp.setActive(false);
		employeeRepo.save(emp);
	}

	/* PAGINATION */
	public List<Employee> getAll(int page, int size) {
		Sort sort = Sort.by("id").descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Employee> getEmployee = employeeRepo.findActiveEmployees(pageable);
		List<Employee> employees = getEmployee.getContent();
		return employees;
	}

//	Pageable pageable = PageRequest.of(0, 10);
//	PageRequest.of(0, 10, Sort.by("name"))
//	Sort.by("name").descending()
//	Sort.by("name").and(Sort.by("email"))
//	
//	page.getContent();       // List<User>
//	page.getTotalPages();   // total pages
//	page.getTotalElements();// total records
//	page.hasNext();         // next page?
}
