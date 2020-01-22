import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// GenreSelection class: User enters their personal rankings for different genres

public class GenreSelection extends JFrame implements ActionListener {

	private ArrayList<User> userList;
	private ArrayList<Artist> artistList;
	private ArrayList<Song> songList;

	// clipart labels that represent each music genre
	private JLabel hiphopClipart = new JLabel();
	private JLabel rapClipart = new JLabel();
	private JLabel rockClipart = new JLabel();
	private JLabel countryClipart = new JLabel();
	private JLabel popClipart = new JLabel();

	// displays text to where user should input their ranking for each genre
	private JLabel hiphop = new JLabel("Hip hop rating:");
	private JLabel rap = new JLabel("Rap rating:");
	private JLabel rock = new JLabel("Rock rating:");
	private JLabel country = new JLabel("Country rating:");
	private JLabel pop = new JLabel("Pop rating:");

	// allows user to rank genres from favourite (1) to least favourite (5)
	private JComboBox<Integer> hiphopRank = new JComboBox<Integer>();
	private JComboBox<Integer> rapRank = new JComboBox<Integer>();
	private JComboBox<Integer> rockRank = new JComboBox<Integer>();
	private JComboBox<Integer> countryRank = new JComboBox<Integer>();
	private JComboBox<Integer> popRank = new JComboBox<Integer>();

	// displays where the user will type in their name
	private JLabel nameLabel = new JLabel("Enter your name:");

	// title of the gui
	private JLabel title = new JLabel("Rank your favourite music genres from favourite (1) to least favourite (5)");

	// finishes the genre selection process
	private JButton finished = new JButton("Finished");

	// allows the user to type in their name
	private JTextField name = new JTextField();

	// background image
	private JLabel background = new JLabel();

	// font the subtitles uses
	private Font subTitleFont = new Font("Helvetica", Font.BOLD, 25);

	// font the title uses
	private Font titleFont = new Font("Helvetica", Font.BOLD, 27);

	// font other text areas use
	private Font miscFont = new Font("Helvetica", Font.BOLD, 30);

	// constructor
	public GenreSelection(ArrayList<User> userList, ArrayList<Artist> artistList, ArrayList<Song> songList) {

		this.userList = userList;
		this.artistList = artistList;
		this.songList = songList;

		// Initializes gui
		setSize(1000, 750);
		setResizable(false);
		setLayout(null);
		setVisible(true);

		showLabels();

	}

	// creates labels to display on frame
	public void showLabels() {

		// adds labels and buttons to frame
		add(hiphop);
		add(rock);
		add(country);
		add(rap);
		add(pop);
		add(hiphopClipart);
		add(rockClipart);
		add(countryClipart);
		add(rapClipart);
		add(popClipart);
		add(hiphopRank);
		add(rockRank);
		add(countryRank);
		add(rapRank);
		add(popRank);
		add(name);
		add(nameLabel);
		add(finished);

		// adds selections to rating boxes
		for (int i = 1; i <= 5; i++) {
			hiphopRank.addItem(i);
			rockRank.addItem(i);
			countryRank.addItem(i);
			rapRank.addItem(i);
			popRank.addItem(i);
		}

		// adds title to frame
		add(title);
		// sets custom font for title
		title.setFont(titleFont);
		// positions title
		title.setBounds(20, 0, 1000, 100);

		// displays labels and buttons in proper positions
		hiphopRank.setBounds(580, 280, 55, 55);
		rockRank.setBounds(200, 600, 55, 55);
		countryRank.setBounds(580, 600, 55, 55);
		rapRank.setBounds(200, 280, 55, 55);
		popRank.setBounds(860, 280, 55, 55);

		// sets icon for each clipart image, positions ratings text and sets the correct
		// font
		hiphopClipart
				.setIcon(new ImageIcon(new ImageIcon("res/pics/HipHop.png").getImage().getScaledInstance(140, 140, 0)));
		hiphopClipart.setBounds(440, 140, 140, 140);
		hiphop.setBounds(390, 280, 180, 50);
		hiphop.setFont(subTitleFont);

		rapClipart.setIcon(new ImageIcon(new ImageIcon("res/pics/Rap.png").getImage().getScaledInstance(120, 120, 0)));
		rapClipart.setBounds(100, 150, 120, 120);
		rap.setBounds(55, 280, 150, 50);
		rap.setFont(subTitleFont);

		popClipart.setIcon(new ImageIcon(new ImageIcon("res/pics/Pop.png").getImage().getScaledInstance(120, 120, 0)));
		popClipart.setBounds(750, 140, 120, 120);
		pop.setBounds(720, 280, 150, 50);
		pop.setFont(subTitleFont);

		countryClipart.setIcon(
				new ImageIcon(new ImageIcon("res/pics/Country.png").getImage().getScaledInstance(120, 120, 0)));
		countryClipart.setBounds(440, 450, 120, 120);
		country.setFont(subTitleFont);
		country.setForeground(Color.WHITE);
		country.setBounds(390, 600, 180, 50);

		rockClipart
				.setIcon(new ImageIcon(new ImageIcon("res/pics/Rock.png").getImage().getScaledInstance(120, 120, 0)));
		rockClipart.setBounds(100, 450, 120, 120);
		rock.setBounds(50, 600, 150, 50);
		rock.setFont(subTitleFont);
		rock.setForeground(Color.WHITE);

		// positions name box and sets proper font
		name.setBounds(750, 570, 160, 60);
		name.setFont(miscFont);

		// positions finished button
		finished.setBounds(750, 650, 160, 40);
		// make button white
		finished.setBackground(Color.WHITE);
		// custom font
		finished.setFont(new Font("Helvetica", Font.BOLD, 20));
		// listens for clicks
		finished.addActionListener(this);

		// sets font and positions name label
		nameLabel.setFont(miscFont);
		nameLabel.setBounds(715, 500, 250, 60);

		// display background image
		add(background);
		background.setIcon(new ImageIcon("res/pics/gradient.jpg"));
		background.setBounds(0, 0, 1000, 750);

	}

	// set the ranking of the genres for the users selections and creates user
	public void setSelections() {

		// create the user
		userList.add(new User(name.getText(), String.valueOf(userList.size()) + ".tsv",
				Integer.parseInt(String.valueOf(hiphopRank.getSelectedItem())),
				Integer.parseInt(String.valueOf(rapRank.getSelectedItem())),
				Integer.parseInt(String.valueOf(popRank.getSelectedItem())),
				Integer.parseInt(String.valueOf(rockRank.getSelectedItem())),
				Integer.parseInt(String.valueOf(countryRank.getSelectedItem()))));

		writeUsers();

	}

	// writes users into a text file
	public void writeUsers() {

		try {

			// create file in res
			PrintWriter writer = new PrintWriter("res/database/users.txt");

			// repeat process for as many users there are
			for (int i = 0; i < userList.size(); i++) {

				// write name, filename, and rankings into text file
				writer.print(userList.get(i).getName() + ",");
				writer.print(userList.get(i).getFileName() + ",");
				writer.print(userList.get(i).getCountryRanking() + ",");
				writer.print(userList.get(i).getHiphopRanking() + ",");
				writer.print(userList.get(i).getRockRanking() + ",");
				writer.print(userList.get(i).getPopRanking() + ",");
				writer.print(userList.get(i).getRapRanking());

				// if writer is on the last line
				if (i == userList.size() - 1) {
					// stop writing
					writer.close();
				} else {
					// else separate last value with a comma
					writer.print(",");
				}

			}

			// if file is not in res
		} catch (FileNotFoundException error) {

			System.out.println("users.txt could not be written into res");
			error.printStackTrace();
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

		// if user clicks finished button
		if (e.getSource() == finished) {

			// totals all selections
			int total = Integer.parseInt(String.valueOf(hiphopRank.getSelectedItem()))
					+ Integer.parseInt(String.valueOf(rapRank.getSelectedItem()))
					+ Integer.parseInt(String.valueOf(popRank.getSelectedItem()))
					+ Integer.parseInt(String.valueOf(rockRank.getSelectedItem()))
					+ Integer.parseInt(String.valueOf(countryRank.getSelectedItem()));

			// if total is 15 (1+2+3+4+5) and user entered name
			if (total == 15 && !(name.getText().isEmpty()) && !(name.getText().contains(",")) && !(name.getText().contains("\t"))) {

				// set rankings
				setSelections();

				// hide frame
				setVisible(false);

				// create gui for artist selection process
				new ArtistsSelection(userList, artistList, songList);

				// else display error message
			} else {
				JOptionPane.showMessageDialog(background,
						"Please give each genre a seperate rank and enter a valid name.");
			}
		}
	}
}
