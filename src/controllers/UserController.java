package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.UserDataService;
import beans.User;

@ManagedBean(name="UserController")
@SessionScoped
public class UserController {

	private User user;
	private UserDataService uds;
	private String errorMessage;
	
	public UserController() {
		this.uds = new UserDataService();
		this.errorMessage = "";
	}
	
	public void updateUser(User user) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		User u = this.uds.update(user);
		this.user = u;
		externalContext.redirect("Account.xhtml");
	}
	
	public void register(User user) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		User u = this.uds.create(user);
		if (u.getId() <= 0) {
			this.errorMessage = "User already exists";
			externalContext.redirect("Register.xhtml");
		}
		this.user = u;
		externalContext.redirect("Account.xhtml");
	}
	
	public void login(User user) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		this.errorMessage = "";
		this.user = uds.loginUser(user);
		System.out.println(this.user.getId() + this.user.getUsername() + this.user.getFullName());
		if (this.user.getId() <= 0) {
			this.errorMessage = "Username or password incorrect";
			try {
				externalContext.redirect("Login.xhtml");
			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
		this.errorMessage = "";
		try {
			externalContext.redirect("Account.xhtml");
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void logout() throws IOException {
		System.out.println("In logout");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		this.user = new User();
		externalContext.redirect("Login.xhtml");
	}
	
}
