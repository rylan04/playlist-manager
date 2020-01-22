import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// ArtistsSelection class: User gives feedback on artists

public class ArtistsSelection extends JFrame implements ActionListener {

	private ArrayList<User> userList;
	private ArrayList<Artist> artistList;
	private ArrayList<Song> songList;

	// button if the user likes the current artist
	private JButton[] like = new JButton[3];
	// button if the user dislikes the current artist
	private JButton[] dislike = new JButton[3];

	// displays current artists
	private JLabel[] currentArtistLabels = new JLabel[3];

	// keeps track of current artists
	private Artist[] currentArtists = new Artist[3];

	// holds users' favourite genres
	private String[] favouriteGenres = new String[3];

	// holds users' liked artists
	private ArrayList<Artist> likedArtists = new ArrayList<Artist>();

	// holds users' disliked artists
	private ArrayList<Artist> dislikedArtists = new ArrayList<Artist>();

	// holds artists that user has given feedback to
	private ArrayList<Artist> seenArtists = new ArrayList<Artist>();

	// allows user to finish the selection process
	private JButton finished = new JButton("Generate Playlist");

	// background image
	private JLabel background = new JLabel();

	// title label
	private JLabel title = new JLabel("Who are your favourite music artists?");

	// displays the column where artists will be displayed
	private JLabel artistNameLabel = new JLabel("<HTML><U>Artist name</U></HTML>");

	// displays the column where the like and dislike buttons will be displayed
	private JLabel likeOrDislike = new JLabel("<HTML><U>Like / dislike ?</U></HTML>");

	// constructor method
	public ArtistsSelection(ArrayList<User> userList, ArrayList<Artist> artistList, ArrayList<Song> songList) {

		this.userList = userList;
		this.artistList = artistList;
		this.songList = songList;

		// initializes gui
		setSize(1000, 750);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		prioritizeArtists();
		showLabels();

	}

	// displays labels on frame
	public void showLabels() {

		for (int i = 0; i < currentArtistLabels.length; i++) {

			// creates labels
			currentArtistLabels[i] = new JLabel();
			// sets custom font
			currentArtistLabels[i].setFont(new Font("Helvetica", Font.BOLD, 26));
			// makes text white
			currentArtistLabels[i].setForeground(Color.WHITE);

			// adds labels to frame
			add(currentArtistLabels[i]);

			// set artists' names
			currentArtistLabels[i].setText(currentArtists[i].getName());

			// displays current artists' names
			currentArtistLabels[i].setBounds(100, 150 * (i + 1), 650, 200);

		}

		Font likeordislikeFont = new Font("Helvetica", Font.BOLD, 17);

		for (int i = 0; i < like.length; i++) {

			// creates buttons
			like[i] = new JButton();

			// adds buttons to frame
			add(like[i]);

			// makes buttons white
			like[i].setBackground(Color.WHITE);

			like[i].setFont(likeordislikeFont);

			// creates buttons
			dislike[i] = new JButton();

			// makes buttons white
			dislike[i].setBackground(Color.WHITE);

			dislike[i].setFont(likeordislikeFont);

			// adds buttons to frame
			add(dislike[i]);

			// displays buttons
			like[i].setBounds(700, 60 + (i + 1) * 150, 100, 100);

			// allows the user to understand what button does
			like[i].setText("Like");

			// allows button to be listened for clicks
			like[i].addActionListener(this);

			// displays buttons
			dislike[i].setBounds(820, 60 + (i + 1) * 150, 100, 100);

			// allows the user to understand what button does
			dislike[i].setText("Dislike");

			// allows button to be listened for clicks
			dislike[i].addActionListener(this);

		}

		// font for miscellaneous text labels
		Font miscFont = new Font("Helvetica", Font.BOLD, 30);

		// add title text
		add(title);
		// sets custom font
		title.setFont(new Font("Helvetica", Font.BOLD, 40));
		// positions title text
		title.setBounds(150, 30, 1000, 60);

		// adds artist column text
		add(artistNameLabel);
		// sets font
		artistNameLabel.setFont(miscFont);
		// positions artist column text
		artistNameLabel.setBounds(100, 110, 200, 65);

		// adds like and dislike button column text
		add(likeOrDislike);
		// sets font
		likeOrDislike.setFont(miscFont);
		// positions like and dislike button column text
		likeOrDislike.setBounds(710, 110, 300, 65);

		// adds buttons to frame
		add(finished);

		// allows button to be listened for clicks
		finished.addActionListener(this);

		// displays finished button
		finished.setBounds(380, 630, 250, 70);
		// makes finished button white
		finished.setBackground(Color.WHITE);
		// sets custom font for finished button
		finished.setFont(new Font("Helvetica", Font.BOLD, 20));

		// adds background label
		add(background);
		// sets background labels to the correct picture
		background.setIcon(new ImageIcon("res/pics/gradient.jpg"));
		// positions background label
		background.setBounds(0, 0, 1000, 750);
	}

	// ranks the users' favourite genres
	public void prioritizeArtists() {

		// loop through favourite, second favourite, and third favourite genres
		for (int i = 1; i <= 3; i++) {

			// if user ranks a genre first, second, or third
			if (userList.get(userList.size() - 1).getHiphopRanking() == i)

				// set genre to corresponding rank
				favouriteGenres[i - 1] = "Hip hop";

			else if (userList.get(userList.size() - 1).getRapRanking() == i)
				favouriteGenres[i - 1] = "Rap";
			else if (userList.get(userList.size() - 1).getPopRanking() == i)
				favouriteGenres[i - 1] = "Pop";
			else if (userList.get(userList.size() - 1).getRockRanking() == i)
				favouriteGenres[i - 1] = "Rock";
			else if (userList.get(userList.size() - 1).getCountryRanking() == i)
				favouriteGenres[i - 1] = "Country";

		}

		displayArtists();
	}

	// sets the label's texts to correspond with artist's names
	public void displayArtists() {

		System.out.println("display artists method");

		// loops through each current artist label
		for (int j = 0; j < currentArtistLabels.length; j++) {

			// loops through all artists
			for (int i = 0; i < artistList.size(); i++) {

				// if user picked a genre in their top 3 favourite genres
				if (favouriteGenres[j].contains("Rap")) {

					// if artist has genre rating greater than 0
					if (artistList.get(i).getRapRating() > 0) {
						// add to artists that user will give feedback on
						addCurrentArtists(i, j);
					}

				} else if (favouriteGenres[j].contains("Hip hop")) {
					if (artistList.get(i).getHiphopRating() > 0) {
						addCurrentArtists(i, j);
					}
				} else if (favouriteGenres[j].contains("Pop")) {
					if (artistList.get(i).getPopRating() > 0) {
						addCurrentArtists(i, j);
					}
				} else if (favouriteGenres[j].contains("Rock")) {
					if (artistList.get(i).getRockRating() > 0) {
						addCurrentArtists(i, j);
					}
				} else if (favouriteGenres[j].contains("Country")) {
					if (artistList.get(i).getCountryRating() > 0) {
						addCurrentArtists(i, j);
					}
				}
			}
		}
	}

	// adds a specific artist to the current artists for user to give their feedback
	// on
	public void addCurrentArtists(int artistIndex, int genreIndex) {

		// checks if user has any disliked artists
		boolean dislikedArtistsListEmpty = false;

		// if the user has given no feedback
		if (seenArtists.size() == 0) {
			dislikedArtistsListEmpty = true;
		}

		// if the user has given no feedback
		if (dislikedArtistsListEmpty == true) {

			// add artist to current artists to be reviewed by user
			currentArtists[genreIndex] = artistList.get(artistIndex);

			// else if user has given feedback
		} else if (dislikedArtistsListEmpty == false) {

			// temporarily holds artist's name
			String tempArtistName = artistList.get(artistIndex).getName();

			// check if the artist has been disliked by user
			boolean checkIfDisliked = false;

			// loops through disliked artists
			for (int k = 0; k < seenArtists.size(); k++) {

				// if user has disliked current artists
				if (tempArtistName.equals(seenArtists.get(k).getName()))

					// set to true
					checkIfDisliked = true;

			}

			// if user hasn't disliked artist
			if (checkIfDisliked == false) {

				// set artist to current artist
				currentArtists[genreIndex] = artistList.get(artistIndex);

			}

		}

	}

	// updates the names of artists displayed on screen
	public void updateArtistsLabels() {

		// loops through labels
		for (int i = 0; i < currentArtistLabels.length; i++) {

			// sets names to updated artist
			currentArtistLabels[i].setText(currentArtists[i].getName());

		}
	}

	// takes in user selections and changes the artists, changes their rankings
	public void updateArtists(int choice, int artist) {

		// if user dislikes an artist
		if (choice == 1) {

			System.out.println("disliked artist: " + currentArtists[artist]);

			// add artist to an array of disliked artists
			dislikedArtists.add(currentArtists[artist]);
			// add it to an array of artists the user has already given feedback on
			seenArtists.add(currentArtists[artist]);

			displayArtists();
			updateArtistsLabels();

			// if user liked an artist
		} else if (choice == 0) {

			System.out.println("liked artist: " + currentArtists[artist]);

			// add artist to an array of liked artists
			likedArtists.add(currentArtists[artist]);
			// add artist to an array of artists the user has already given feedback on
			seenArtists.add(currentArtists[artist]);

			displayArtists();
			updateArtistsLabels();

		}

	}

	/*
	 * listens for button clicks (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// if user likes an artist
		for (int i = 0; i < like.length; i++)
			if (e.getSource() == like[i])
				updateArtists(0, i);

		// if user dislikes an artist
		for (int i = 0; i < dislike.length; i++)
			if (e.getSource() == dislike[i])
				updateArtists(1, i);

		// if user clicks finished button
		if (e.getSource() == finished) {

			// hides frame
			setVisible(false);

			// create gui for user to display and edit their playlist
			new DisplayPlaylists(userList, artistList, songList, likedArtists, dislikedArtists, favouriteGenres);
		}

	}
}
