package fieldworks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fieldworks.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByContactNumber(String contactNumber);

	Optional<Employee> findByUserName(String userName);

	/* JPQL */
	@Query("SELECT e FROM Employee e WHERE e.active = true")
	Page<Employee> findActiveEmployees(Pageable pageable);
	
	@Query("SELECT e FROM Employee e WHERE e.role = :role AND e.active = true")
	List<Employee> findActiveByRole(@Param("role") String role);

	/* Native SQL */
	@Query(value = "SELECT * FROM employee WHERE role = :role", nativeQuery = true)
	List<Employee> findByRole(@Param("role") String role);

	@Query(value = "SELECT * FROM employee WHERE privilege = :privilege", nativeQuery = true)
	List<Employee> findByPrivilege(@Param("privilege") String privilege);

	@Query(value = "SELECT * FROM employee WHERE created_by = :createdBy",
	       nativeQuery = true)
	List<Employee> findByCreator(@Param("createdBy") Long createdBy);
}
