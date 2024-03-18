package controllers;

import javax.faces.bean.*;

import beans.Album;
import business.MusicManager;

@ManagedBean(name="AlbumController")
@ViewScoped
public class AlbumController {
	private Album album;
	public String onSubmit(Album album) {
		this.album = album;
		MusicManager mg = new MusicManager();
		mg.addAlbum(this.album);
		return "AddAlbumResponse.xhtml";
	}
}