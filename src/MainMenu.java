import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// MainMenu class: Main menu gui, where the user can select their existing playlist or create a new one

public class MainMenu extends JFrame implements ActionListener {

	private ArrayList<User> userList;
	private ArrayList<Artist> artistList;
	private ArrayList<Song> songList;

	// begins genre selection process
	private JButton begin = new JButton("BEGIN");

	// allows user to pick another user
	private JButton user = new JButton("USER");

	// title of program
	private JLabel title = new JLabel();

	// background image
	private JLabel background = new JLabel();

	// constructor method
	public MainMenu(ArrayList<User> userList, ArrayList<Artist> artistList, ArrayList<Song> songList) {

		this.userList = userList;
		this.artistList = artistList;
		this.songList = songList;

		// initializes gui
		setSize(1000, 750);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		showLabels();
	}

	// displays labels and buttons on frame
	public void showLabels() {

		// adds begin button to frame
		add(begin);
		// listens to if the user clicks being button
		begin.addActionListener(this);
		// adds user button to frame
		add(user);
		// listen to if the user clicks user button
		user.addActionListener(this);

		// adds title to frame
		add(title);
		// custom font for title
		title.setFont(new Font("Helvetica", Font.BOLD, 85));
		title.setText("Song Genius");
		// positions title
		title.setBounds(230, 80, 800, 100);

		// positions user button
		user.setBounds(290, 510, 400, 150);

		// custom font for user button text
		user.setFont(new Font("Helvetica", Font.BOLD, 25));
		// positions begin button
		begin.setBounds(290, 300, 400, 150);
		// custom font for begin button test
		begin.setFont(new Font("Helvetica", Font.BOLD, 25));

		// sets buttons to white
		begin.setBackground(Color.WHITE);
		user.setBackground(Color.WHITE);

		// adds background image
		add(background);
		// gets background image from res
		background.setIcon(new ImageIcon("res/pics/gradient.jpg"));
		background.setBounds(0, 0, 1000, 750);
	}

	/*
	 * listens for button clicks(non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == begin) {

			// hides screen
			setVisible(false);

			// opens gui for genre selection process
			new GenreSelection(userList, artistList, songList);

		} else if (e.getSource() == user) {

			if (userList.size() == 0) {

				// error message
				JOptionPane.showMessageDialog(background, "There are no existing users");
			} else {

				// hides frame
				setVisible(false);
				// opens gui to allow the user to pick a user
				new PickUser(userList, songList, artistList);
			}
		}
	}

}
