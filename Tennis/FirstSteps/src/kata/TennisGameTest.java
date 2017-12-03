package kata;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TennisGameTest {
	private TennisGame tennisGame;

	@Before
	public void setup() {
		tennisGame = new TennisGame();
	}

	@Test
	public void shouldCallLoveAll() {
		assertAnnouncement("Love all");
	}

	@Test
	public void firstPointForPlayer1() {
		tennisGame.player1Scores();
		assertAnnouncement("Fifteen Love");
	}

	@Test
	public void firstPointForPlayer2() {
		tennisGame.player2Scores();
		assertAnnouncement("Love Fifteen");
	}
	
	private void assertAnnouncement(String expected) {
		assertEquals(expected, tennisGame.getAnnouncement());
	}
}
