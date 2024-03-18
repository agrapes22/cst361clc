package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.*;

import util.TracksNotFoundException;
import beans.Album;
import beans.Track;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class MusicManager implements MusicManagerInterface {

	HashMap<String, List> trackData;
	
	public MusicManager() {
		this.trackData = new HashMap<String, List>();
		
		Track t1 = new Track("Views From The Sun", 1);
		Track t2 = new Track("Nurture", 2);
		Track t3 = new Track("The Haze", 3);
		Track t4 = new Track("Red Summer", 4);
		Track t5 = new Track("In My Skin", 5);
		
		List<Track> newList = new ArrayList<Track>();
		newList.add(t1);
		newList.add(t2);
		newList.add(t3);
		newList.add(t4);
		newList.add(t5);
		
		trackData.put("Thornhill_2019_The Dark Pool", newList);
	}
	
	@Override
	public Album addAlbum(Album model) {
		List<Track> tracks = new ArrayList<Track>();
		try {
			tracks = getTracks(model);
			model.setTracks(tracks);
			for (Track t : tracks) {
				System.out.println(t.getTitle());
			}
		}
		catch (TracksNotFoundException e) {
			System.out.println("Tracks not found");
		}
		
		return model;
	}
	
	private List getTracks(Album album) throws TracksNotFoundException {
		List<Track> tracks = new ArrayList<Track>();
		String key = album.getArtist() + "_" + album.getYear() + "_" + album.getTitle();
		tracks = trackData.get(key);
		if (tracks.isEmpty())
		{
			throw new TracksNotFoundException();
		}
		else
			return tracks;
	}

}
