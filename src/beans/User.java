package beans;

import javax.faces.bean.*;

@ManagedBean
@RequestScoped
public class User {
	String username;
	String fullName;
	String emailAddress;
	int id;
	
	public User() {
		this.id = 1;		
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
		return 1;
	}
}
