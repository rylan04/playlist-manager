import java.util.*;

/* Main Header
 * Name: Rylan Sykes
 * 
 * Date of submission: January 18, 2019
 * 
 * Course Code: ICS4U1-01
 * 
 * Title: title of project; music advisor, title of product; Music Genius
 * 
 * Description: Music Genius is a program that allows you to input your music tastes and curates a playlist based on given input. 
 * You can then view and edit your playlist, and Music Genius will give you recommendations for additional songs based on your interests.
 * 
 * Features: 
 * Program will take specific input from genres and artists, and seamlessly make a playlist that should match the input given. 
 * User can edit their playlist; add and remove songs. Playlists can be infinitely long (as much as memory can hold)
 * Program will give recommendations to songs that aren't on their playlist already, based on which songs the user is adding and removing
 * User can give a name to their account and save their input, as well as their playlist for later use
 * Users can come back to their playlist at any time and edit it, as it is accessed and edited more often, the program will give better results for recommendations
 * Users can click on any song in their playlist and a web browser will open to a link where the user can listen to the selected song
 * 
 * Major skills:
 * Object Oriented Programming:
 * A variety of data is stored using objects that are essential to keep organized for this program to run efficiently.
 * The artist class is an object that stores an artists' name, and rankings and is used extensively throughout the program
 * in order to determine what songs are best for the user based on their input. The multiple getRatings methods are used to compare 
 * artists to see which one best suits a specific genre. The getName method is used for tracking which tracks the artists the user is adding and/or 
 * removing from their playlist and is the basis of how the program recommends new songs for the user.
 * The Song class is used to store data on specific songs. This is essential to the program as the song objects hold track ID's, which 
 * allows the user to listen to the songs. Song artist names are used to discover new songs from the same artist. Song genres in order to determine which songs
 * to recommend after the user has given feedback. The Song objects are used in the DisplayPlaylists class to grab the information of the 
 * user's playlist and display it in an organized manner.
 * The User class stores the user's data. This allows the user to save their rankings for genres, as well as their playlist. Each playlist can only
 * be accessed through a specific user and the name of the playlist is held in the getFileName method in the user class. The User class also
 * holds the user's ranking for each genre which the program uses to determine which genres would best suit the user.
 * The Playlist class is a simple class but is still important to the organization of the data within this program. The Playlist class holds an
 * arraylist of songs which represents the user's playlist. Each playlist is specific to a user which is why they have a name that can be used
 * in order to access the file in which the program contains a user's playlist.
 * 
 * Data types and structures:
 * Multiple ArrayLists to hold user, sons, and artist objects
 * JLabel arrays when there are multiple labels that need to be added to a frame/panel: DisplayPlaylists class includes 3 ArrayLists to hold
 * song names, artists, and albums.
 * JButton arrays within the DisplayPlaylists class to allow the user to listen to a selected song.
 * JComboBox's of integers and strings to hold user selections for favourite music genres, as well as within the addSong and removeSong methods in
 * the DisplayPlaylists class.
 * TreeSet used in the songRecommendations method in the DisplayPlaylists class, to copy artists onto a data structure that does not take duplicates,
 * and to be able to easily sort lexicographically.
 * Integer values such as findUser to hold the index of a specific user in the arraylist of users.
 * 
 * Use of methods:
 * Method addCurrentArtists in ArtistsSelection class helps the otherwise repetitive process of changing an artist but only if the artist
 * is within the constraints of the user.
 * Method checkIfValidSong in DisplayPlaylists helps the program in a similar process to addCurrentArtists in a way that the program
 * must run a single method that takes in specific parameters instead of repeatedly running the same code within a different method but with
 * those same parameters
 * 
 * Control Structures:
 * For loops to iterate through songList, artistList, and userList are used extensively throughout the program.
 * For loops to iterate through arrays of labels and buttons are used to quicken the process of settings bounds, settings
 * fonts, adding to frame, etc.
 * 
 * GUI:
 * GUI is simple and easy to follow
 * 
 * Areas of Concern:
 * 1. Adding and removing songs doesn't fully work:
 * When a user adds or removes a song from their playlist through the DisplyPlaylists GUI, the song is automatically added/removed
 * from the playlist, but when the user attempts to scroll through the playlist again, the song seems to disappear. The song is not 
 * completely gone from the playlist as if the user were to save the playlist, re open the program and use the User GUI to open their playlist 
 * again, the song(s) that are added will be on their playlist, and song(s) that are removed will not be on their playlist.
 * 2. A user's added / removed artists will not be saved in a text file and will only be held in a single session. This means if a user
 * gets a set of songs the program recommends, and closes then re-opens the program, the user will not longer have those recommended songs
 * and must input more songs for the program to recognize any patterns in their interest
 * 
 * Folders needed: res folder, database folder, pics folder, userPlaylists folder
 * Files needed: 0.tsv, 1.tsv, 2.tsv, 3.tsv, 4.tsv, artists.tsv, users.txt, Country.png, gradient.jpg, HipHop.png, Pop.png, Rap.png, Rock.png
 */

public class MusicAdvisorTest {

	// main method
	public static void main(String[] args) {

		// userList - array list of user objects
		ArrayList<User> userList = new ArrayList<User>();

		// artistList - array list of artist objects
		ArrayList<Artist> artistList = new ArrayList<Artist>();

		// songList - array list of song objects
		ArrayList<Song> songList = new ArrayList<Song>();

		// reads in songs and artists from text files
		new ReadInData(artistList, songList, userList);

		// creates new main menu gui
		new MainMenu(userList, artistList, songList);

	}

}
