package fieldworks.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeDTO {
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String user_name;

	@NotBlank
	private String password;

	@NotBlank
	private String contact_number;

	@Email
	private String contact_email;

	private String auth_token;

	private Boolean active = true;

	private Boolean isAvailable = true;

	private Long created_by;

	private LocalDateTime created_at = LocalDateTime.now();

	private String remark;

	@NotBlank
	private String role; // pd, ro, gm, cgm, member, stakeholder

	private Long superior_fk_id;

	@NotBlank
	private String privilege; // admin / user

	public EmployeeDTO(Long id, String name, String user_name, String password, String contact_number,
			String contact_email, String auth_token, Boolean active, Boolean isAvailable, Long created_by,
			LocalDateTime created_at, String remark, String role, Long superior_fk_id, String privilege) {
		super();
		this.id = id;
		this.name = name;
		this.user_name = user_name;
		this.password = password;
		this.contact_number = contact_number;
		this.contact_email = contact_email;
		this.auth_token = auth_token;
		this.active = active;
		this.isAvailable = isAvailable;
		this.created_by = created_by;
		this.created_at = created_at;
		this.remark = remark;
		this.role = role;
		this.superior_fk_id = superior_fk_id;
		this.privilege = privilege;
	}

	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
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

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
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

	public Long getSuperior_fk_id() {
		return superior_fk_id;
	}

	public void setSuperior_fk_id(Long superior_fk_id) {
		this.superior_fk_id = superior_fk_id;
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
