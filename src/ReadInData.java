import java.io.*;
import java.util.*;

// ReadInData class: Reads in song, artists, users, from text files

public class ReadInData {

	// constructor method
	public ReadInData(ArrayList<Artist> artistList, ArrayList<Song> songList, ArrayList<User> userList) {

		readInArtists(artistList);
		readInSongs(songList);
		readInUsers(userList);
	}

	// Reads in artists from text file
	public void readInArtists(ArrayList<Artist> artistList) {

		// try catch to catch errors
		try {

			// splits strings into tokens
			StringTokenizer tokenizer;
			// reads in from text file
			BufferedReader tsvFile = new BufferedReader(new FileReader("res/database/artists.tsv"));
			String dataRow = tsvFile.readLine();

			// while there is more data
			while (dataRow != null) {

				// seperate string with tabs
				tokenizer = new StringTokenizer(dataRow, "\t");

				// hold strings in kist
				List<String> dataArray = new ArrayList<String>();

				// while there is more data
				while (tokenizer.hasMoreElements()) {
					// adds string to array
					dataArray.add(tokenizer.nextElement().toString());
				}

				// makes artist with given data
				Artist currentArtist = new Artist(dataArray.get(0), Integer.parseInt(dataArray.get(1)),
						Integer.parseInt(dataArray.get(2)), Integer.parseInt(dataArray.get(3)),
						Integer.parseInt(dataArray.get(4)), Integer.parseInt(dataArray.get(5)));

				// adds artist to arraylist of artists
				artistList.add(currentArtist);

				dataRow = tsvFile.readLine();
			}

			// stops reading
			tsvFile.close();

			// if file is not found
		} catch (FileNotFoundException e) {

			System.out.println("File not found, artists");
			e.printStackTrace();

		} catch (IOException e) {

			System.out.println("IOException error, artists");
			e.printStackTrace();

		}

	}

	// reads in songs from text files, similar process to reading in artists
	public void readInSongs(ArrayList<Song> songList) {

		// reads in each text file
		for (int i = 0; i < 5; i++) {

			try {

				StringTokenizer tokenizer;
				BufferedReader tsvFile = new BufferedReader(new FileReader("res/database/" + i + ".tsv"));
				String dataRow = tsvFile.readLine();

				while (dataRow != null) {

					tokenizer = new StringTokenizer(dataRow, "\t");

					List<String> dataArray = new ArrayList<String>();

					while (tokenizer.hasMoreElements()) {
						dataArray.add(tokenizer.nextElement().toString());
					}

					Song currentSong = new Song(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3),
							Double.parseDouble(dataArray.get(4)), dataArray.get(5), Integer.parseInt(dataArray.get(6)));

					songList.add(currentSong);

					dataRow = tsvFile.readLine();
				}

				tsvFile.close();

			} catch (FileNotFoundException e) {

				System.out.println("File not found, songs");
				e.printStackTrace();

			} catch (IOException e) {

				System.out.println("IOException error, songs");
				e.printStackTrace();

			}

		}

	}

	// reads in users from text file
	public void readInUsers(ArrayList<User> userList) {

		try {

			// reads data using scanner
			Scanner read = new Scanner(new File("res/database/users.txt"));

			// seperates data with commas
			read.useDelimiter(",");

			int index = 0;

			// while there is more information
			while (read.hasNext()) {

				String name = read.next();
				String fileName = read.next();
				int countryRating = read.nextInt();
				int hiphopRating = read.nextInt();
				int rockRating = read.nextInt();
				int popRating = read.nextInt();
				int rapRating = read.nextInt();

				// add user and their data
				userList.add(new User(name, fileName, hiphopRating, rapRating, popRating, rockRating, countryRating));

				System.out.println(userList.get(index).toString());

				// increments index
				index++;
			}

			// stops reading file
			read.close();

			// if file is not found
		} catch (FileNotFoundException error) {

			// error message
			System.out.println("users.txt not found");
		}
	}
}
