import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

// DisplayPlaylists class: Displays user's playlist

public class DisplayPlaylists extends JFrame implements ActionListener {

	private ArrayList<User> userList;
	private ArrayList<Artist> artistList;
	private ArrayList<Song> songList;
	private Playlist currentPlaylist;

	// holds the index of the user in the userList array list
	private int findUser = -1;

	// holds artists that user has added
	private ArrayList<String> addedArtists = new ArrayList<String>();

	// holds artists that user has removed
	private ArrayList<String> removedArtists = new ArrayList<String>();

	// list of songs from users playlist
	private ArrayList<JLabel> songLabels = new ArrayList<JLabel>();

	// list of albums from users playlist
	private ArrayList<JLabel> albumLabels = new ArrayList<JLabel>();

	// list of artists from users playlist
	private ArrayList<JLabel> artistLabels = new ArrayList<JLabel>();

	// buttons so the user can listen to any song on their playlist
	private ArrayList<JButton> links = new ArrayList<JButton>();

	// panel that playlist is on
	private JPanel viewPlaylist;

	// allows scrolling through playlist
	private JScrollPane playlistPane;

	// allows user to add a song
	private JButton add = new JButton("Add Song");

	// allows user to remove a song
	private JButton remove = new JButton("Remove Song");

	// allows user to save playlist
	private JButton save = new JButton("Save Playlist");

	// button that gives user recommended songs when pressed
	private JButton recommendations = new JButton("Recommendations");

	// main title text
	private JLabel title = new JLabel();

	// background image
	private JLabel background = new JLabel();

	// constructor method for a new user
	public DisplayPlaylists(ArrayList<User> userList, ArrayList<Artist> artistList, ArrayList<Song> songList,
			ArrayList<Artist> likedArtists, ArrayList<Artist> dislikedArtists, String[] favouriteGenres) {

		this.userList = userList;
		this.artistList = artistList;
		this.songList = songList;

		// adds title to screen and displays playlist with name
		add(title);
		title.setText(userList.get(userList.size() - 1).getName() + " 's Playlist");

		// makes their playlist based on ratings and feedback
		currentPlaylist = curatePlaylist(likedArtists, favouriteGenres);

		// displays the playlist
		displayPlaylist(currentPlaylist);

		// displays labels and initalizes main gui
		displayLabels();

	}

	// constructor method for an existing user
	public DisplayPlaylists(int findUser, ArrayList<User> userList, ArrayList<Song> songList,
			ArrayList<Artist> artistList) {

		this.findUser = findUser;
		this.userList = userList;
		this.songList = songList;
		this.artistList = artistList;

		// adds title text to frame
		add(title);
		// sets title to show user's specific playlist name
		title.setText(userList.get(findUser).getName() + " 's Playlist");

		// sets the current playlist to the user's existing playlist
		currentPlaylist = new Playlist(userList.get(findUser).getFileName());

		// reads in the user's playlist
		readPlaylist(findUser);

		// displays labels and initalizes main frame
		displayLabels();

	}

	// displays labels and initalizes main frame
	public void displayLabels() {

		// initalizes frame
		setSize(1000, 750);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		// adds the add song button to main frame
		add(add);
		add.setBounds(500, 650, 120, 50);
		// makes button white
		add.setBackground(Color.WHITE);
		// listens to if mouse clicks
		add.addActionListener(this);

		// adds remove song button to main frame
		add(remove);
		remove.setBounds(300, 650, 120, 50);
		// makes button white
		remove.setBackground(Color.WHITE);
		// listens to if button is clicked
		remove.addActionListener(this);

		// adds recommendations button to main frame
		add(recommendations);
		recommendations.setBounds(830, 20, 150, 70);
		// makes button white
		recommendations.setBackground(Color.WHITE);
		// listens to if button is clicked
		recommendations.addActionListener(this);

		// adds save button to main frame
		add(save);
		save.setBounds(20, 20, 150, 70);
		// makes button white
		save.setBackground(Color.WHITE);
		// listens if button is clicked
		save.addActionListener(this);

		// sets custom font for title
		title.setFont(new Font("Helvetica", Font.BOLD, 45));
		// positions title text
		title.setBounds(355, 0, 900, 100);

		// adds background image to main frame
		add(background);
		background.setIcon(new ImageIcon("res/pics/gradient.jpg"));
		background.setBounds(0, 0, 1000, 750);

	}

	// makes a playlist based on user's liked artists and favourite genres

	public Playlist curatePlaylist(ArrayList<Artist> likedArtists, String[] favouriteGenres) {

		System.out.println("curate playlist method");

		// TODO make title of playlist the user's number
		Playlist currentPlaylist = new Playlist("temp title");

		// holds how many songs from each artist can be on the playlist
		double rapCount = 0;
		double popCount = 0;
		double rockCount = 0;
		double countryCount = 0;
		double hiphopCount = 0;

		// find how many artists are liked in each genre
		for (int i = 0; i < likedArtists.size(); i++) {

			if (likedArtists.get(i).getRapRating() > 0)
				rapCount++;
			else if (likedArtists.get(i).getPopRating() > 0)
				popCount++;
			else if (likedArtists.get(i).getRockRating() > 0)
				rockCount++;
			else if (likedArtists.get(i).getCountryRating() > 0)
				countryCount++;
			else if (likedArtists.get(i).getHiphopRating() > 0)
				hiphopCount++;
		}

		// calculate how many artists' songs can have a slot on the playlist
		int rapMax = 0;
		int popMax = 0;
		int rockMax = 0;
		int countryMax = 0;
		int hiphopMax = 0;

		// if artist can have less than one song
		if (Math.round(25 / rapCount) == 0)
			// round up to one song
			rapMax = 1;
		else
			rapMax = (int) Math.round(25 / rapCount);

		if (Math.round(25 / popCount) == 0)
			popMax = 1;
		else
			popMax = (int) Math.round(25 / popCount);

		if (Math.round(25 / rockCount) == 0)
			rockMax = 1;
		else
			rockMax = (int) Math.round(25 / rockCount);

		if (Math.round(25 / countryCount) == 0)
			countryMax = 1;
		else
			countryMax = (int) Math.round(25 / countryCount);

		if (Math.round(25 / hiphopCount) == 0)
			hiphopMax = 1;
		else
			hiphopMax = (int) Math.round(25 / hiphopCount);

		// holds the count of how any songs each artists have on the playlist
		int[] artistSongsNum = new int[artistList.size()];

		// cycles through all songs
		for (int i = 0; i < songList.size(); i++) {

			// cycles through favourite genres
			genresLoop: for (int k = 0; k < favouriteGenres.length; k++) {

				// if song matches user's favourite genre
				if (favouriteGenres[k].equals(songList.get(i).getGenre())) {

					// cycles through user's liked artists
					for (int j = 0; j < likedArtists.size(); j++) {

						// if the song has the same artist as the liked artist
						if (likedArtists.get(j).getName().contains(songList.get(i).getArtist())) {

							// if song is a specific genre
							if (songList.get(i).getGenre().equals("Rap")) {

								// attempt to add to playlist
								checkIfValidSong("Rap", artistSongsNum, rapMax, popMax, countryMax, hiphopMax, rockMax,
										currentPlaylist, i);
								// stop looping through genres
								break genresLoop;
							}

							else if (songList.get(i).getGenre().equals("Pop")) {

								checkIfValidSong("Pop", artistSongsNum, rapMax, popMax, countryMax, hiphopMax, rockMax,
										currentPlaylist, i);
								break genresLoop;

							} else if (songList.get(i).getGenre().equals("Rock")) {

								checkIfValidSong("Rock", artistSongsNum, rapMax, popMax, countryMax, hiphopMax, rockMax,
										currentPlaylist, i);
								break genresLoop;

							} else if (songList.get(i).getGenre().equals("Hip hop")) {

								checkIfValidSong("Hip hop", artistSongsNum, rapMax, popMax, countryMax, hiphopMax,
										rockMax, currentPlaylist, i);
								break genresLoop;

							} else if (songList.get(i).getGenre().equals("Country")) {

								checkIfValidSong("Country", artistSongsNum, rapMax, popMax, countryMax, hiphopMax,
										rockMax, currentPlaylist, i);
								break genresLoop;

							}

						}

					}

				}

			}

		}
		return currentPlaylist;
	}

	// checks if a song can be added to the user's playlist
	public void checkIfValidSong(String genre, int[] artistSongsNum, int rapMax, int popMax, int countryMax,
			int hiphopMax, int rockMax, Playlist currentPlaylist, int index) {

		// holds the genre that the specific song is and how many songs of the genre can
		// be added to the playlist
		int genreMax = 0;

		// checks which genre the song is
		if (genre.equals("Rap"))
			// when it's found the specific genre, get how many songs of that genre can be
			// added to playlist
			genreMax = rapMax;
		else if (genre.equals("Pop"))
			genreMax = popMax;
		else if (genre.equals("Hip hop"))
			genreMax = hiphopMax;
		else if (genre.equals("Country"))
			genreMax = countryMax;
		else if (genre.equals("Rock"))
			genreMax = rockMax;

		// if adding the song will still be within the max amount of songs
		if (artistSongsNum[findArtist(songList.get(index).getArtist())] + 1 <= genreMax) {

			// increment artist's count for number of songs
			artistSongsNum[findArtist(
					songList.get(index).getArtist())] = artistSongsNum[findArtist(songList.get(index).getArtist())] + 1;

			// add song to playlist
			currentPlaylist.setPlaylist(songList.get(index));

			System.out.println("added country song");

		}
	}

	// finds the index at where a specific artist is in the artist list array list
	public int findArtist(String name) {

		// holds artist index
		int artistPos = 0;

		// loops through artist list
		for (int i = 0; i < artistList.size(); i++) {
			// if artist list matches given artist (name)
			if (artistList.get(i).getName().equals(name)) {
				// set index to for loop index
				artistPos = i;
				// stop looping
				break;
			}
		}

		// return artist index
		return artistPos;
	}

	// displays playlist on panel
	public void displayPlaylist(Playlist currentPlaylist) {

		System.out.println("display playlist method");

		// clear labels so when playlist is changed, songs aren't duplicated
		songLabels.clear();
		artistLabels.clear();
		albumLabels.clear();
		links.clear();

		// panels the songs will appear on
		viewPlaylist = new JPanel();
		// grid layout for organisation
		viewPlaylist.setLayout(new GridLayout(currentPlaylist.getEntirePlaylist().size(), 0, 5, 5));
		// makes grid white
		viewPlaylist.setBackground(Color.WHITE);
		viewPlaylist.setVisible(true);

		Font songFont = new Font("Helvetica", Font.BOLD, 15);

		// loops through user's playlist
		for (int i = 0; i < currentPlaylist.getEntirePlaylist().size(); i++) {

			// adds song name to song label array
			songLabels.add(new JLabel("Song: " + currentPlaylist.getPlaylist(i).getName()));
			// positions song name in panel
			viewPlaylist.add(songLabels.get(i), i, 0);
			// sets font
			songLabels.get(i).setFont(songFont);

			// adds artist names to artist name label array
			artistLabels.add(new JLabel("Artist: " + currentPlaylist.getPlaylist(i).getArtist()));
			// positions artist name in panel
			viewPlaylist.add(artistLabels.get(i), i, 1);
			// sets font
			artistLabels.get(i).setFont(songFont);

			// adds album name to album name label array
			albumLabels.add(new JLabel("Album: " + currentPlaylist.getPlaylist(i).getAlbum()));
			// positions album name in panel
			viewPlaylist.add(albumLabels.get(i), i, 2);
			// sets font
			albumLabels.get(i).setFont(songFont);

			// adds buttons to panel
			links.add(new JButton("Listen"));
			// positions panels
			viewPlaylist.add(links.get(i), i, 3);
			// makes button text white
			links.get(i).setForeground(Color.WHITE);
			// makes button dark grey
			links.get(i).setBackground(Color.DARK_GRAY);
			// listens to clicks
			links.get(i).addActionListener(this);
		}

		// allows user to scroll through songs
		playlistPane = new JScrollPane(viewPlaylist, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// positions panel
		playlistPane.setBounds(70, 150, 880, 470);
		playlistPane.setPreferredSize(new Dimension(500, 500));
		// adds panel to main frame
		add(playlistPane);

		// updates panel and scollable area
		playlistPane.revalidate();
		viewPlaylist.revalidate();

	}

	// allows user to select an artist and add a specific song to their playlist,
	// tracks which artists the user has added
	public void addSong() {

		// frame for adding songs
		JFrame addSongFrame = new JFrame();

		// initializes frame
		addSongFrame.setSize(500, 500);
		addSongFrame.setResizable(false);
		addSongFrame.setLayout(null);
		addSongFrame.setVisible(true);

		// has all artists and songs in combo boxes
		JComboBox<String> artistSelection = new JComboBox<String>();
		JComboBox<String> songSelection = new JComboBox<String>();

		// button when the user has picked their artist and song
		JButton addSongButton = new JButton("Add Song");

		// adds button to "add song" frame
		addSongFrame.add(addSongButton);
		// makes button white
		addSongButton.setBackground(Color.WHITE);
		// custom font
		addSongButton.setFont(new Font("Helvetica", Font.BOLD, 20));
		// positions button
		addSongButton.setBounds(200, 390, 150, 50);

		// adds artist combo box to "add song" panel
		addSongFrame.add(artistSelection);
		artistSelection.setBounds(180, 10, 200, 100);

		// adds song combo box to "add song" panel
		addSongFrame.add(songSelection);
		songSelection.setBounds(180, 200, 200, 100);

		// adds artists' names to selections
		for (int i = 0; i < artistList.size(); i++) {
			artistSelection.addItem(artistList.get(i).getName());
		}

		// listens to user's clicks
		addSongButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				// if user has selected an artist and song
				if (artistSelection != null && songSelection != null) {

					// cycles through songs
					for (int i = 0; i < songList.size(); i++) {

						// if song name matches song name from selection
						if (songList.get(i).getName().equals(songSelection.getSelectedItem())) {

							// add selected song to playlist
							currentPlaylist.setPlaylist(songList.get(i));
							System.out.println("song successfully added");

							// tracks artists user has added
							trackNewArtists(songList.get(i).getArtist(), 0);
							// updates playlist panel
							displayPlaylist(currentPlaylist);

						}

					}

				}

			}

		});

		// listens to user's selection
		artistSelection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				System.out.println("action performed");

				// removes previous artists' songs
				songSelection.removeAllItems();

				// holds artist index
				int selectedArtist = artistSelection.getSelectedIndex();

				// loops through songs
				for (int i = 0; i < songList.size(); i++) {

					// if song artist matches selected artist's name
					if (songList.get(i).getArtist().equals(artistList.get(selectedArtist).getName())) {

						System.out.println("song found");

						// adds artist's songs to combo box
						songSelection.addItem(songList.get(i).getName());

					}
				}

			}

		});

	}

	// removes songs from playlist, tracks which artist user has removed
	public void removeSong() {

		// frame for removing song
		JFrame removeSongFrame = new JFrame();

		// initalizes frame
		removeSongFrame.setSize(500, 500);
		removeSongFrame.setResizable(false);
		removeSongFrame.setLayout(null);
		removeSongFrame.setVisible(true);

		// selection box for removing song
		JComboBox<String> songSelection = new JComboBox<String>();

		// adds playlist's songs to selection
		for (int i = 0; i < currentPlaylist.getEntirePlaylist().size(); i++) {
			songSelection.addItem(currentPlaylist.getPlaylist(i).getName());
		}

		// button when user has decided the song they wish to remove
		JButton removeSongButton = new JButton("Remove Song");

		// adds button to "remove song" frame
		removeSongFrame.add(removeSongButton);
		removeSongButton.setBounds(130, 400, 200, 50);
		// makes button white
		removeSongButton.setBackground(Color.WHITE);

		// adds selection box to "remove song" frame
		removeSongFrame.add(songSelection);
		songSelection.setBounds(130, 100, 200, 100);

		// listens for clicks
		removeSongButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {

				System.out.println("action performed");

				// if user selected a song
				if (songSelection != null) {

					// loops through user's playlist
					for (int i = 0; i < currentPlaylist.getEntirePlaylist().size(); i++) {

						// if song in playlist matches selected song name
						if (currentPlaylist.getPlaylist(i).getName().equals(songSelection.getSelectedItem())) {

							System.out.println(currentPlaylist.getPlaylist(i).getName());

							// tracks artist user has removed
							trackNewArtists(currentPlaylist.getPlaylist(i).getArtist(), 1);

							// confirms that user has removed song
							JOptionPane.showMessageDialog(removeSongFrame, currentPlaylist.getPlaylist(i).getName()
									+ " by " + currentPlaylist.getPlaylist(i).getArtist() + " Removed");

							// removes song from playlist
							currentPlaylist.getEntirePlaylist().remove(i);

							// displays new playlist
							displayPlaylist(currentPlaylist);

							// stop looping through playlist
							break;
						}

					}

				}

			}

		});

	}

	// tracks if user has added or removed a song
	public void trackNewArtists(String artistName, int decision) {

		// if added artist
		if (decision == 0) {

			System.out.println("added artist: " + artistName);
			// add to an array of artists that user has added
			addedArtists.add(artistName);

			// if removed artist
		} else if (decision == 1) {

			System.out.println("removed artist: " + artistName);
			// add to an array of artists that user has removed
			removedArtists.add(artistName);

		}

	}

	// opens spotify link of song for user to listen to
	public void openBrowser(int index) {

		// if on desktop
		if (Desktop.isDesktopSupported()) {
			try {
				// tries to open link
				Desktop.getDesktop().browse(
						new URI("https://open.spotify.com/track/" + currentPlaylist.getPlaylist(index).getTrackID()));

				// catches potential errors
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException ee) {
				ee.printStackTrace();
			}
		}

	}

	// gives the user recommendations based on songs they've added or removed
	public void songRecommendations() {

		// if user has given no feedback on songs that are excluded from the
		// playlist
		if (addedArtists.size() < 3) {

			// display error message
			JOptionPane.showMessageDialog(background,
					"Please add songs from at least 3 unique artists to your playlist for accurate song recommendations");

			// else give recommendations
		} else {

			/*
			 * find artist that user has added most. rank the songs by the artist's genre.
			 * recommend 2 songs from the same artist, and 1 song that is not from the
			 * artist (highest ranked). check the unique song is not by a removed artist.
			 * allow the user to add those songs
			 */

			System.out.println(Arrays.toString(addedArtists.toArray()));

			// songs that method will recommend based on artists that user has added
			ArrayList<Song> recommendedSongs = new ArrayList<Song>();

			// songs that method will recommend based on the genre of artists that user has
			// been recently adding
			ArrayList<Song> recommendedUniqueSongs = new ArrayList<Song>();

			// holds unique artists lexicographically
			TreeSet<String> uniqueArtists = new TreeSet<String>();

			// copies artists onto unique list of artists
			for (String currentArtist : addedArtists)
				uniqueArtists.add(currentArtist);

			// holds unique artists onto an arraylist to access array through
			// indices
			ArrayList<String> uniqueArtist2 = new ArrayList<String>();

			// copy the unique artists onto array list
			for (String currentArtist : uniqueArtists)
				uniqueArtist2.add(currentArtist);

			// keeps count of how many times an artist is repeated
			int[] artistsCount = new int[uniqueArtists.size()];

			System.out.println(Arrays.toString(artistsCount));

			// if artist is found on playlist, add to their count
			for (int j = 0; j < uniqueArtist2.size(); j++) {
				for (int i = 0; i < addedArtists.size(); i++) {
					if (uniqueArtist2.get(j).equals(addedArtists.get(i)))
						artistsCount[j]++;
				}
			}
			System.out.println(Arrays.toString(artistsCount));

			// find artist with highest count (amount of songs on playlist)
			int highestCountIndex = 0;
			int highestCount = 0;
			for (int i = 0; i < artistsCount.length; i++) {
				if (artistsCount[i] > highestCount) {
					highestCount = artistsCount[i];
					highestCountIndex = i;
				}
			}

			System.out.println(uniqueArtist2.get(highestCountIndex));

			// adds songs from favourite artist
			for (int i = 0; i < songList.size(); i++) {

				if (songList.get(i).getArtist().equals(uniqueArtist2.get(highestCountIndex))) {

					recommendedSongs.add(songList.get(i));

				}
			}

			// ensures that a recommended song isn't one that's on your playlist
			// already
			try {
				// loops through potential recommended songs
				for (int i = 0; i < recommendedSongs.size(); i++) {

					// loops through user's playlist
					for (int j = 0; j < currentPlaylist.getEntirePlaylist().size(); j++) {

						// if recommended song is already on playlist
						if (recommendedSongs.get(i) == currentPlaylist.getPlaylist(j))
							// remove song from recommendations
							recommendedSongs.remove(i);

					}

				}
				// catch error
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.out.println("finished removing songs");
			}

			// rank songs by rating
			RankByGenre rank = new RankByGenre();
			Collections.sort(songList, rank);

			// add a song from same genre and high rating
			// loops through songs
			for (int j = 0; j < songList.size(); j++) {

				// loops through songs
				for (int i = 0; i < artistList.size(); i++) {

					// if song artist matches artist that method recommends
					if (artistList.get(i).getName().equals(uniqueArtist2.get(highestCountIndex))) {

						// if artist has a good ranking in a specific genre
						if (artistList.get(i).getRapRating() > 0) {
							if (songList.get(j).getGenre().equals("Rap"))

								// add it to recommended songs
								recommendedUniqueSongs.add(songList.get(j));

						} else if (artistList.get(i).getCountryRating() > 0) {
							if (songList.get(j).getGenre().equals("Country"))
								recommendedUniqueSongs.add(songList.get(j));

						} else if (artistList.get(i).getPopRating() > 0) {
							if (songList.get(j).getGenre().equals("Pop"))
								recommendedUniqueSongs.add(songList.get(j));

						} else if (artistList.get(i).getRockRating() > 0) {
							if (songList.get(j).getGenre().equals("Rock"))
								recommendedUniqueSongs.add(songList.get(j));

						} else if (artistList.get(i).getHiphopRating() > 0)
							if (songList.get(j).getGenre().equals("Hip hop"))
								recommendedUniqueSongs.add(songList.get(j));

					}
				}
			}

			// loop through recommended songs
			for (int i = 0; i < recommendedUniqueSongs.size(); i++) {

				// loops through current playlist
				for (int j = 0; j < currentPlaylist.getEntirePlaylist().size(); j++) {

					// if a recommended song is already on playlist
					if (recommendedUniqueSongs.get(i) == currentPlaylist.getPlaylist(j))

						// remove song from recommended songs
						recommendedUniqueSongs.remove(i);

				}

			}

			for (int i = 0; i < 2; i++) {
				System.out.println(recommendedSongs.get(i));
			}

			System.out.println(recommendedUniqueSongs.get(0));

			// frame to display recommended songs
			JFrame recommendationsFrame = new JFrame();

			// initializes frame
			recommendationsFrame.setSize(600, 500);
			recommendationsFrame.setLayout(null);
			recommendationsFrame.setResizable(false);
			recommendationsFrame.setVisible(true);

			// arraylist of labels to hold song names and artist names
			JLabel[] songNames = new JLabel[3];
			JLabel[] songArtists = new JLabel[3];

			Font songFont = new Font("Helvetica", Font.BOLD, 18);

			for (int i = 0; i < songNames.length; i++) {

				// creates labels
				songNames[i] = new JLabel();
				songArtists[i] = new JLabel();

				// adds labels to recommendation frame
				recommendationsFrame.add(songNames[i]);
				recommendationsFrame.add(songArtists[i]);

				// positions labels
				songNames[i].setBounds(20, 100 * (i + 1), 200, 100);
				songArtists[i].setBounds(270, 100 * (i + 1), 250, 100);

				songNames[i].setFont(songFont);
				songArtists[i].setFont(songFont);

			}

			// sets first two recommended songs
			for (int i = 0; i <= 1; i++) {
				songNames[i].setText(recommendedSongs.get(i).getName());
				songArtists[i].setText(recommendedSongs.get(i).getArtist());
			}

			// sets third selection
			songNames[2].setText(recommendedUniqueSongs.get(0).getName());
			songArtists[2].setText(recommendedUniqueSongs.get(0).getArtist());

			// buttons so user can add song to playlist
			JButton[] addSongs = new JButton[3];

			for (int i = 0; i < addSongs.length; i++) {

				// creates buttons
				addSongs[i] = new JButton("Add song");
				// adds buttons to recommendations frame
				recommendationsFrame.add(addSongs[i]);

				// makes buttons white
				addSongs[i].setBackground(Color.WHITE);
				// sets custom font
				addSongs[i].setFont(new Font("Helvetica", Font.BOLD, 15));

				// positions buttons
				addSongs[i].setBounds(440, 110 * (i + 1), 140, 100);

			}

			// listens for clicks
			addSongs[0].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {

					// adds song to user's playlist
					currentPlaylist.getEntirePlaylist().add(recommendedSongs.get(0));
					// updates panel
					displayPlaylist(currentPlaylist);
					// confirms song has been added
					JOptionPane.showMessageDialog(recommendationsFrame, "Added " + recommendedSongs.get(0).getName()
							+ " by " + recommendedSongs.get(0).getArtist());
				}

			});

			addSongs[1].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {

					currentPlaylist.getEntirePlaylist().add(recommendedSongs.get(1));
					displayPlaylist(currentPlaylist);
					JOptionPane.showMessageDialog(recommendationsFrame, "Added " + recommendedSongs.get(1).getName()
							+ " by " + recommendedSongs.get(1).getArtist());
				}

			});

			addSongs[2].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {

					currentPlaylist.getEntirePlaylist().add(recommendedUniqueSongs.get(0));
					displayPlaylist(currentPlaylist);
					JOptionPane.showMessageDialog(recommendationsFrame, "Added " + recommendedSongs.get(1).getName()
							+ " by " + recommendedSongs.get(1).getArtist());
				}

			});
		}
	}

	// saves playlist to text file
	public void savePlaylist() {

		try {

			// finds index of user
			int index;
			// if new user
			if (findUser == -1)
				index = userList.size() - 1;
			// else use index provided by previous class
			else
				index = findUser;

			// creates file and names it according to the user
			PrintWriter writer = new PrintWriter(new File("res/userPlaylists/" + userList.get(index).getFileName()));

			// loops through playlist
			for (int i = 0; i < currentPlaylist.getEntirePlaylist().size(); i++) {

				// writes track id
				writer.write(currentPlaylist.getPlaylist(i).getTrackID());

				// if done writing
				if (i == currentPlaylist.getEntirePlaylist().size() - 1) {

					System.out.println("successfully saved playlist");
					// confirm that playlist has been saved
					JOptionPane.showMessageDialog(background, "Playlist saved!");
					// stop writing
					writer.close();

					// else write a tab and continue writing songs
				} else {

					writer.print("\t");
				}

			}

			// catches error if file is not found
		} catch (FileNotFoundException error) {

			error.printStackTrace();
		}

	}

	// reads in playlists from file
	public void readPlaylist(int user) {

		// TODO read in playlist

		System.out.println("user:" + user);
		System.out.println(userList.size());

		try {

			// breaks a string into tokens
			StringTokenizer tokenizer;
			// reads text file from res
			BufferedReader tsvFile = new BufferedReader(
					new FileReader("res/userPlaylists/" + userList.get(user).getFileName()));
			// reads in a line of text
			String dataRow = tsvFile.readLine();

			// while there is still data
			while (dataRow != null) {

				// seperate strings by tabs
				tokenizer = new StringTokenizer(dataRow, "\t");

				// add strings into an array
				List<String> dataArray = new ArrayList<String>();

				// while there are more elements
				while (tokenizer.hasMoreElements()) {
					// continue adding to the array
					dataArray.add(tokenizer.nextElement().toString());
				}

				// loops through strings
				for (int i = 0; i < dataArray.size(); i++) {

					for (int j = 0; j < songList.size(); j++) {

						// if track id matches song track id
						if (dataArray.get(i).equals(songList.get(j).getTrackID())) {

							// add song to playlist
							currentPlaylist.setPlaylist(songList.get(j));

						}

					}

				}

				// reads next line
				dataRow = tsvFile.readLine();
			}

			// closes reader
			tsvFile.close();

			// if file is not found
		} catch (FileNotFoundException e) {

			// display error message
			JOptionPane.showMessageDialog(background, "Playlist is not found for user");

			System.out.println("File not found, songs");
			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("IOException error, songs");
			e.printStackTrace();

		}

		for (int j = 0; j < currentPlaylist.getEntirePlaylist().size(); j++) {

			System.out.println(currentPlaylist.getPlaylist(j));

		}

		// displays playlist
		displayPlaylist(currentPlaylist);

	}

	/*
	 * listens for button clicks(non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// if users clicks add song button
		if (e.getSource() == add) {
			// run add song method
			addSong();
			// else if user clicks remove song button
		} else if (e.getSource() == remove) {
			// run remove song method
			removeSong();
			// else if user clicks recommendations method
		} else if (e.getSource() == recommendations) {
			// run recommendations method
			songRecommendations();
			// else if user clicks save method
		} else if (e.getSource() == save) {
			// save playlist
			savePlaylist();
		}

		for (int i = 0; i < links.size(); i++) {

			// if user clicks any link button
			if (e.getSource() == links.get(i)) {

				// opens browser for specific song
				openBrowser(i);

			}

		}

	}
}
