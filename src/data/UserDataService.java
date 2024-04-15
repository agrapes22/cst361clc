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
	public User create(User o) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User o) {
		// TODO Auto-generated method stub
		
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
				}
			}
		} catch (SQLException e) {
			System.out.println("Not connected");
		}
		return t;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
