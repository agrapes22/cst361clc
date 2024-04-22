package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		Thermometer t = new Thermometer();
		String fields = o.getUserId() + ", " + o.isActive() + ", '" + o.getName() + "', '" + o.getScale() + "', " + o.getSetTemp() + ", SYSDATE()";
		Statement s = con.createStatement();
		s.executeUpdate("INSERT INTO thermometer (userId, active, name, scale, setTemp, lastRead) VALUES(" + fields + ")");
		t = getUserThermometer(o.getUserId());
		putThermReads(o);
		return t;
	}
	
	public void putThermReads(Thermometer o) {
		
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

	public Thermometer getUserThermometer(int userId) {
		Thermometer t = new Thermometer();
		try {
			Statement s = con.createStatement();
			String sql = "SELECT thermometerId, name, active, scale, userId, lastRead, setTemp FROM thermometer WHERE userId = " + userId;
			ResultSet r = s.executeQuery(sql);
			while (r.next()) {
				t.setId(r.getInt("thermometerId"));
				t.setName(r.getString("name"));
				t.setActive(r.getBoolean("active"));
				t.setScale(r.getString("scale"));
				t.setUserId(r.getInt("userId"));
				t.setLastRead(r.getTimestamp("lastRead").toString());
				t.setSetTemp(r.getInt("setTemp"));
			}
		} catch (SQLException e) {
			System.out.println("Not connected");
		}
		
		if (t.getId() >= 1) {
			getThermReads(t);
		}
		
		return t;
	}
	
	private void getThermReads(Thermometer t) {
		try {
			Statement s = con.createStatement();
			String sql = "SELECT readDate, temperature FROM thermometer_read WHERE thermometerId = " + t.getId();
			ResultSet r = s.executeQuery(sql);
			while (r.next()) {
				String dateTime = r.getTimestamp("readDate").toString();
				double temp = r.getDouble("temperature");
				t.putRead(dateTime, temp);
			}
		} catch (SQLException e) {
			System.out.println("Not connected");
		}
	}

}
