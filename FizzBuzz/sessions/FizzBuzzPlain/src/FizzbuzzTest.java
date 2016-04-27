import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FizzbuzzTest {
	private Fizzbuzz fizzbuzz = new Fizzbuzz();

	@Test
	public void shouldBeSilent() {
		assertNotNull(fizzbuzz.shout(0));
		assertNotNull(fizzbuzz.shout(-3));

		assertEquals("", fizzbuzz.shout(0));
		assertEquals("", fizzbuzz.shout(-3));
	}

	@Test(timeout=50)
	public void shouldSayNumber() {
		assertEquals("1", fizzbuzz.shout(1));
		assertEquals("2", fizzbuzz.shout(2));
		// in between number
		assertEquals("4", fizzbuzz.shout(4));
		assertEquals("901", fizzbuzz.shout(901));
	}

	@Test
	public void shouldSayFizz() {
		assertEquals(Fizzbuzz.FIZZ, fizzbuzz.shout(3));
		assertEquals(Fizzbuzz.FIZZ, fizzbuzz.shout(6));
		assertEquals(Fizzbuzz.FIZZ, fizzbuzz.shout(21));
	}

	@Test
	public void shouldSayBuzz() {
		assertEquals(Fizzbuzz.BUZZ, fizzbuzz.shout(5));
		assertEquals(Fizzbuzz.BUZZ, fizzbuzz.shout(20));
	}

	@Test
	public void shouldSayFizzBuzz() {
		assertEquals(Fizzbuzz.FIZZBUZZ, fizzbuzz.shout(15));
		assertEquals(Fizzbuzz.FIZZBUZZ, fizzbuzz.shout(30));
		assertEquals(Fizzbuzz.FIZZBUZZ, fizzbuzz.shout(60));
	}
}
