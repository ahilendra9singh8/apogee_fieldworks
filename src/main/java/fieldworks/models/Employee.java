package fieldworks.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "employee")
//@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Username is required")
	@Column(name = "user_name", unique = true)
	private String userName;

	@NotBlank(message = "Password is required")
	private String password;

	@NotBlank(message = "Contact number required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number")
	@Column(name = "contact_number", unique = true)
	private String contactNumber;

	@Email(message = "Invalid email format")
	@Column(name = "contact_email", unique = true)
	private String contactEmail;

	@Column(name = "auth_token")
	private String authToken;

	private Boolean active = true;

	private Boolean isAvailable = true;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	private String remark;

	@NotBlank(message = "Role is required")
	private String role; // pd, ro, gm, cgm, member, stakeholder

	@Column(name = "superior_fk_id")
	private Long superiorFkId;

	@NotBlank(message = "Privilege is required")
	private String privilege; // admin / user

	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private EmployeeProfile profile;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@ManyToMany
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private Set<Project> projects = new HashSet<>();

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeeDocument> documents = new ArrayList<>();

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, @NotBlank(message = "Name is required") String name,
			@NotBlank(message = "Username is required") String userName,
			@NotBlank(message = "Password is required") String password,
			@NotBlank(message = "Contact number required") @Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number") String contactNumber,
			@Email(message = "Invalid email format") String contactEmail, String authToken, Boolean active,
			Boolean isAvailable, Long createdBy, LocalDateTime createdAt, String remark,
			@NotBlank(message = "Role is required") String role, Long superiorFkId,
			@NotBlank(message = "Privilege is required") String privilege, EmployeeProfile profile,
			Department department, Set<Project> projects) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.contactNumber = contactNumber;
		this.contactEmail = contactEmail;
		this.authToken = authToken;
		this.active = active;
		this.isAvailable = isAvailable;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.remark = remark;
		this.role = role;
		this.superiorFkId = superiorFkId;
		this.privilege = privilege;
		this.profile = profile;
		this.department = department;
		this.projects = projects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getSuperiorFkId() {
		return superiorFkId;
	}

	public void setSuperiorFkId(Long superiorFkId) {
		this.superiorFkId = superiorFkId;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public EmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeProfile profile) {
		this.profile = profile;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

}
