import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// PickUser class: Allows user to go view an already existing playlist

public class PickUser extends JFrame implements ActionListener {

	private ArrayList<User> userList;
	private ArrayList<Song> songList;
	private ArrayList<Artist> artistList;

	// combo box that allows user to select an already existing user
	private JComboBox<String> userSelection = new JComboBox<String>();

	// main title
	private JLabel title = new JLabel();

	// background image
	private JLabel background = new JLabel();

	// button for when the user has selected their user
	JButton selectUser = new JButton("Select User");

	// constructor method
	public PickUser(ArrayList<User> userList, ArrayList<Song> songList, ArrayList<Artist> artistList) {

		this.userList = userList;
		this.songList = songList;
		this.artistList = artistList;

		// initializes gui
		setSize(1000, 750);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		// adds combo box and button to screen
		add(userSelection);
		add(selectUser);

		// positions button
		selectUser.setBounds(350, 500, 300, 100);
		// listens for clicks
		selectUser.addActionListener(this);

		// makes button white
		selectUser.setBackground(Color.WHITE);
		// sets custom font
		selectUser.setFont(new Font("Helvetica", Font.BOLD, 25));

		// positions combo box
		userSelection.setBounds(300, 320, 400, 100);
		// listens for clicks
		userSelection.addActionListener(this);

		// adds title to frame
		add(title);
		// custom font
		title.setFont(new Font("Helvetica", Font.BOLD, 35));
		title.setText("Please select the user you wish to view the playlist of");
		title.setBounds(80, 80, 900, 100);

		// adds background image to frame
		add(background);
		background.setIcon(new ImageIcon("res/pics/gradient.jpg"));
		background.setBounds(0, 0, 1000, 750);

		addUsers();

	}

	// adds users to combo box selections
	public void addUsers() {

		for (int i = 0; i < userList.size(); i++)
			userSelection.addItem(userList.get(i).getName());

	}

	// finds the index in the array of users of the selected user
	public int findUser() {

		System.out.println("finduser method");

		// returns index of arraylist
		int user = 0;

		// cycles through array to find name that matches the current user
		for (int i = 0; i < userList.size(); i++) {
			// if name in arraylist matches selected user
			if (userList.get(i).getName().equals(userSelection.getSelectedItem())) {
				System.out.println("found");
				// sets index
				user = i;
			}
		}
		System.out.println(user);
		// returns index
		return user;
	}

	// listens for clicks
	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO error if no playlist

		// sets current user to user that was picked
		if (e.getSource() == selectUser) {

			// displays playlist
			new DisplayPlaylists(findUser(), userList, songList, artistList);
			// hides frame
			setVisible(false);
		}

	}

}
