package beans;

public class Track {
	String title;
	int number;
	
	public Track() {
		this.title = "";
		this.number = 0;
	}
	
	public Track(String title, int number) {
		this.title = title;
		this.number = 0;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
}
