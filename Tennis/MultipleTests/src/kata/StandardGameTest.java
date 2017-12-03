package kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class StandardGameTest {

	@Test
	public void testEquals() {
		assertEquals("Love all", announce(0, 0));
		assertEquals("Fifteen all", announce(1, 1));
		assertEquals("Thirty all", announce(2, 2));
	}

	private String announce(int points1, int points2) {
		if (points1 == 0) {
			return "Love all";
		}
		if (points1 == 2) {
			return "Thirty all";
		}
		return "Fifteen all";
	}

}
