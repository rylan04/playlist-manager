
// Song class: class to hold information on specific songs

public class Song {

	// spotify track id, used to open in a link
	private String trackID;
	// name of song
	private String name;
	// name of artist(s) who made song
	private String artist;
	// name of album song comes from
	private String album;
	// track length
	private double trackLength;
	// genre of song
	private String genre;
	// overall rating
	private int rating;

	// constructor method
	public Song(String trackID, String name, String artist, String album, double trackLength, String genre,
			int rating) {

		this.trackID = trackID;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.trackLength = trackLength;
		this.genre = genre;
		this.rating = rating;
	}

	// getters and setters
	public String getTrackID() {
		return trackID;
	}

	public void setTrackID(String trackID) {
		this.trackID = trackID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public double getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(double trackLength) {
		this.trackLength = trackLength;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	// toString method
	@Override
	public String toString() {
		return "Song [trackID=" + trackID + ", name=" + name + ", artist=" + artist + ", album=" + album
				+ ", trackLength=" + trackLength + ", genre=" + genre + ", rating=" + rating + "]";
	}

}
