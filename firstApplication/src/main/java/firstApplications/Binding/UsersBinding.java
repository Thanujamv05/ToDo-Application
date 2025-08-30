package firstApplications.Binding;

import jakarta.persistence.Id;

public class UsersBinding {
	private String username;
	private String email;
	private String password;
	private String confirmpassword;
	
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
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password
				+ ", confirmpassword=" + confirmpassword + "]";
	}
	
	
	
}
