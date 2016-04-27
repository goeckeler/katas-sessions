package kata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzTest
{
  private static final String FIZZ = "fizz";

  @Test
  public void shouldShoutNumbers() {
    assertEquals("1", shout(1));
  }

  @Test
  public void shouldShoutFizz() {
    assertEquals(FIZZ, shout(3));
    assertEquals(FIZZ, shout(9));
  }

  private String shout(int number) {
    assert number >= 0;

    if (number % 3 == 0) return FIZZ;
    return "1";
  }
}
