package beans;

public class ThermReads {
	String dateTime;
	double temp;
	
	public ThermReads(String dateTime, double temp) {
		this.dateTime = dateTime;
		this.temp = temp;
	}
	
	public String getDateTime() {
		return this.dateTime;
	}
	
	public double getTemp() {
		return this.temp;
	}
}
