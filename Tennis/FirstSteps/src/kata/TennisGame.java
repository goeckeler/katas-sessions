package kata;

public class TennisGame {
	
	private String announcement = "Love all";

	public String getAnnouncement(){
		return this.announcement;
	}

	public void player1Scores() {
		announcement = "Fifteen Love";
	}

	public void player2Scores() {
		announcement = "Love Fifteen";
	}
}
