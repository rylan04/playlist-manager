
// User class: Hold's user's information and subjective ratings

public class User {

	// name of user
	private String name;
	// playlist filename of user
	private String fileName;
	// user's hip hop rank
	private int hiphopRanking;
	// user's rap rank
	private int rapRanking;
	// user's pop rank
	private int popRanking;
	// user's rock rank
	private int rockRanking;
	// user's country rank
	private int countryRanking;

	// constructor method
	public User(String name, String fileName, int hiphopRanking, int rapRanking, int popRanking, int rockRanking,
			int countryRanking) {

		// user's chosen name
		this.name = name;

		// user's file document name
		this.fileName = fileName;

		// user's hip hop ranking
		this.hiphopRanking = hiphopRanking;

		// user's rap ranking
		this.rapRanking = rapRanking;

		// user's pop ranking
		this.popRanking = popRanking;

		// user's rock ranking
		this.rockRanking = rockRanking;

		// user's country ranking
		this.countryRanking = countryRanking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getHiphopRanking() {
		return hiphopRanking;
	}

	public void setHiphopRanking(int hiphopRanking) {
		this.hiphopRanking = hiphopRanking;
	}

	public int getRapRanking() {
		return rapRanking;
	}

	public void setRapRanking(int rapRanking) {
		this.rapRanking = rapRanking;
	}

	public int getPopRanking() {
		return popRanking;
	}

	public void setPopRanking(int popRanking) {
		this.popRanking = popRanking;
	}

	public int getRockRanking() {
		return rockRanking;
	}

	public void setRockRanking(int rockRanking) {
		this.rockRanking = rockRanking;
	}

	public int getCountryRanking() {
		return countryRanking;
	}

	public void setCountryRanking(int countryRanking) {
		this.countryRanking = countryRanking;
	}

	// toString method
	@Override
	public String toString() {
		return "User [name=" + name + ", fileName=" + fileName + ", hiphopRanking=" + hiphopRanking + ", rapRanking="
				+ rapRanking + ", popRanking=" + popRanking + ", rockRanking=" + rockRanking + ", countryRanking="
				+ countryRanking + "]";
	}

}
