
// Artist class: Holds the name and ratings of an artist

public class Artist {

	// artist's name
	private String name;
	// rating for hip hop genre
	private int hiphopRating;
	// rating for rap genre
	private int rapRating;
	// rating for pop genre
	private int popRating;
	// rating for rock genre
	private int rockRating;
	// rating for country genre
	private int countryRating;

	// constructor method
	public Artist(String name, int hiphopRating, int rapRating, int popRating, int rockRating, int countryRating) {

		this.name = name;
		this.hiphopRating = hiphopRating;
		this.rapRating = rapRating;
		this.popRating = popRating;
		this.rockRating = rockRating;
		this.countryRating = countryRating;

	}

	// getters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHiphopRating() {
		return hiphopRating;
	}

	public void setHiphopRating(int hiphopRating) {
		this.hiphopRating = hiphopRating;
	}

	public int getRapRating() {
		return rapRating;
	}

	public void setRapRating(int rapRating) {
		this.rapRating = rapRating;
	}

	public int getPopRating() {
		return popRating;
	}

	public void setPopRating(int popRating) {
		this.popRating = popRating;
	}

	public int getRockRating() {
		return rockRating;
	}

	public void setRockRating(int rockRating) {
		this.rockRating = rockRating;
	}

	public int getCountryRating() {
		return countryRating;
	}

	public void setCountryRating(int countryRating) {
		this.countryRating = countryRating;
	}

	// toString method
	@Override
	public String toString() {
		return "Artist [name=" + name + ", hiphopRating=" + hiphopRating + ", rapRating=" + rapRating + ", popRating="
				+ popRating + ", rockRating=" + rockRating + ", countryRating=" + countryRating + "]";
	}

}
