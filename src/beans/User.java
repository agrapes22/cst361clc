package beans;

import javax.faces.bean.*;

@ManagedBean
@RequestScoped
public class User {
	String username;
	String fullName;
	String emailAddress;
	int id;
	private String password;
	
	public User() {
		
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public int getId() {
		return this.id;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}
}
