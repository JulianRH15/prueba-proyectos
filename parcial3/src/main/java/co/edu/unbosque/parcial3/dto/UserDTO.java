package co.edu.unbosque.parcial3.dto;

import co.edu.unbosque.parcial3.model.User.Role;

public class UserDTO {
	private Long id;
	private String username;
	private String email;
	private String password;
	private String confirmPassword;
	private Role role;

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(String username, String password, Role role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UserDTO(String userName, String email, String password, String confirmPassword, Role role) {
		super();
		this.username = userName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", role=" + role + "]";
	}

}
