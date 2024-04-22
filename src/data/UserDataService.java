package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import beans.Thermometer;
import beans.User;

public class UserDataService implements DataAccessInterface<User> {

private Connection con;
	
	public UserDataService() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		
		DriverManager dm = null;
		String url = "jdbc:mysql://localhost:3306/cst361";
		
		try {
			con = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public User create(User o) {
		User u = new User();
		o.setId(0);
		try {
			String sql = "INSERT INTO USER (username, firstname, lastname, email, passwordHash, userCreated, lastLogin, accountLocked) VALUES('" + o.getUsername() + "', '" + o.getFullName().substring(0, o.getFullName().indexOf(" "))
					+ "', '" + o.getFullName().substring(o.getFullName().indexOf(" ") + 1)
					+ "', '" + o.getEmailAddress() + "', '" + o.getPassword() + "', SYSDATE(), SYSDATE(), 'N')";
			System.out.println(sql);
			Statement s = con.createStatement();
			s.executeUpdate(sql);
			u = get(o);
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println(u.getUsername() + " " + u.getFullName() + " " + u.getEmailAddress());
		return u;
	}

	@Override
	public User update(User o) {
		User u = new User();
		try {
			String sql = "UPDATE USER SET username = '" + o.getUsername() + "', firstname = '" + o.getFullName().substring(0, o.getFullName().indexOf(" "))
					+ "', lastname = '" + o.getFullName().substring(o.getFullName().indexOf(" ") + 1)
					+ "', email = '" + o.getEmailAddress() + "' WHERE userId = " + o.getId();
			System.out.println(sql);
			Statement s = con.createStatement();
			s.executeUpdate(sql);
			u = get(o);
		} catch (SQLException e) {
			System.out.println("Not connected");
		}
		System.out.println(u.getUsername() + " " + u.getFullName() + " " + u.getEmailAddress());
		return u;
	}

	@Override
	public void delete(User o) {
		
	}

	@Override
	public User get(User o) {
		User t = new User();
		try {
			if (o.getId() != 0) {
				Statement s = con.createStatement();
				ResultSet r = s.executeQuery("SELECT username, firstname, lastname, email FROM user WHERE userId = " + o.getId());
				while (r.next()) {
					t.setUsername(r.getString("username"));
					t.setFullName(r.getString("firstname") + " " + r.getString("lastname"));
					t.setEmailAddress(r.getString("email"));
					t.setId(o.getId());
				}
			}
			else {
				Statement s = con.createStatement();
				String sql = "SELECT userId, username, firstname, lastname, email FROM user WHERE username = '" + o.getUsername()
						+ "' AND email = '" + o.getEmailAddress() + "'";
				ResultSet r = s.executeQuery(sql);
				System.out.println(sql);
				while (r.next()) {
					t.setUsername(r.getString("username"));
					t.setFullName(r.getString("firstname") + " " + r.getString("lastname"));
					t.setEmailAddress(r.getString("email"));
					t.setId(r.getInt("userId"));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return t;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	public User loginUser(User user) {
		User t = new User();
		try {
			System.out.println("Username and pass: " + user.getUsername() + " " + user.getPassword());
			Statement s = con.createStatement();
			String sql = "SELECT userId, username, firstname, lastname, email FROM user WHERE username = '"
					+ user.getUsername() + "' AND passwordHash = '" + user.getPassword() + "'";
			ResultSet r = s.executeQuery(sql);
			System.out.println(sql);
			while (r.next()) {
				t.setId(r.getInt("userId"));
				t.setUsername(r.getString("username"));
				t.setFullName(r.getString("firstname") + " " + r.getString("lastname"));
				t.setEmailAddress(r.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println("Not connected " + e);
		}
		System.out.println("User " + t.getId() + " " + t.getFullName() + " " + t.getUsername());
		return t;
	}
	
}
