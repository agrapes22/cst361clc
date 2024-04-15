package controllers;

import javax.faces.bean.*;

import data.UserDataService;
import beans.User;

@ManagedBean(name="UserController")
@ViewScoped
public class UserController {

	private User user;
	private UserDataService uds;
	
	public String updateUser(User user) {
		return "Account.xhtml";
	}
	
	public String login(int id) {
		this.uds = new UserDataService();
		this.user = new User();
		this.user = uds.get(user);
		System.out.println(this.user.getEmailAddress());
		System.out.println(this.user.getFullName());
		System.out.println(this.user.getUsername());
		return "Account.xhtml";
	}
	
}
