import java.util.*;

// Playlist class: Holds an arraylist of songs

public class Playlist {

	// name of the text file of the playlist
	private String owner;
	// holds arraylist of song objects
	private ArrayList<Song> playlist = new ArrayList<Song>();

	// constructor method
	public Playlist(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	// get a specific song in the playlist
	public Song getPlaylist(int index) {
		return playlist.get(index);
	}

	// add a song to the playlist
	public void setPlaylist(Song song) {
		playlist.add(song);
	}

	// return the entire playlist object
	public ArrayList<Song> getEntirePlaylist() {
		return playlist;
	}

	// toString method
	@Override
	public String toString() {
		return "Playlist [owner=" + owner + ", playlist=" + playlist + "]";
	}

}
