import java.util.*;

public class RankByGenre implements Comparator<Song> {

	@Override
	public int compare(Song a, Song b) {

		return Integer.compare(a.getRating(), b.getRating());
	}

}
