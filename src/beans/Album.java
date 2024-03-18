package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.*;
import javax.validation.constraints.*;

@ManagedBean
@RequestScoped
public class Album {
	@NotNull(message="Title cannot be empty")
	@Size(min=5,max=50, message="Title must be between 5 and 50 characters")
	String title;
	@NotNull(message="Artist cannot be empty")
	@Size(min=5,max=25, message="Artist must be between 5 and 50 characters")
	String artist;
	@Min(1920)
	@Max(2024)
	int year;
	List<Track> tracks;
	
	public Album() {
		this.title = "";
		this.artist = "";
		this.year = 1900;
		this.tracks = new ArrayList();
	}
	
	public int getNumberTracks() {
		return tracks.size();
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
}
