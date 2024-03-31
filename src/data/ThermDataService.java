package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import beans.Thermometer;

public class ThermDataService implements DataAccessInterface<Thermometer> {

	private Connection con;
	
	public ThermDataService() {
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
	public Thermometer create(Thermometer o) throws SQLException {
		int id = 0;
		//try {
			String fields = o.getUserId() + ", " + o.isActive() + ", '" + o.getName() + "', '" + o.getScale() + "'";
			Statement s = con.createStatement();
			id = s.executeUpdate("INSERT INTO thermometer (userId, active, name, scale) VALUES(" + fields + ")", Statement.RETURN_GENERATED_KEYS);
			o.setId(id);
		//} catch (SQLException e) {
			//System.out.println("Not connected");
		//}
		return o;
	}

	@Override
	public Thermometer update(Thermometer o) {
		return null;
	}

	@Override
	public void delete(Thermometer o) {
		
	}

	@Override
	public Thermometer get(Thermometer o) {
		Thermometer t = new Thermometer();
		try {
			if (o.getId() != 0) {
				Statement s = con.createStatement();
				ResultSet r = s.executeQuery("SELECT thermometerId, name, active, scale, userId FROM thermometer WHERE thermometerId = " + o.getId());
				while (r.next()) {
					t.setId(r.getInt("thermometerId"));
					t.setName(r.getString("name"));
					t.setActive(r.getBoolean("active"));
					t.setScale(r.getString("scale"));
					t.setUserId(r.getInt("userId"));
				}
			}
		} catch (SQLException e) {
			System.out.println("Not connected");
		}
		return t;
	}

	@Override
	public List<Thermometer> getAll() {
		return null;
	}

}
