package dojo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FizzBuzzTest
{
  private static final String FIZZ = "fizz";
  private static final String BUZZ = "buzz";
  private static final String WHIZZ = "whizz";
  private static final String BURNOUT = "burnout";

  private static final String FIZZBUZZ = FIZZ + BUZZ;

  private static final int THREE = 3;
  private static final int FIVE = 5;
  private static final int SEVEN = 7;
  private static final int THIRTEEN = 13;

  private static final Map<Integer, String> DIVISORS = new HashMap<>();

  static {
    DIVISORS.put(THREE, FIZZ);
    DIVISORS.put(FIVE, BUZZ);
    DIVISORS.put(SEVEN, WHIZZ);
    DIVISORS.put(THIRTEEN, BURNOUT);
  }

  @Test
  public void shouldSayNumber() {
    assertEquals("1", shout(1));
    assertEquals("11", shout(11));
  }

  @Test
  public void shouldSayFizz() {
    assertEquals(FIZZ, shout(THREE));
    assertEquals(FIZZ, shout(THREE * THREE));
  }

  @Test
  public void shouldSayBuzz() {
    assertEquals(BUZZ, shout(FIVE));
    assertEquals(BUZZ, shout(FIVE * FIVE));
  }

  @Test
  public void shouldSayWhizz() {
    assertEquals(WHIZZ, shout(SEVEN));
    assertEquals(WHIZZ, shout(SEVEN * SEVEN));
  }

  @Test
  public void shouldSayFizzBuzz() {
    assertEquals(FIZZBUZZ, shout(THREE * FIVE));
    assertEquals(FIZZBUZZ, shout(THREE * FIVE * 2));
  }

  @Test
  public void shouldSayFizzWhizz() {
    assertEquals(FIZZ + WHIZZ, shout(THREE * SEVEN));
    assertEquals(FIZZ + WHIZZ, shout(THREE * SEVEN * 2));
  }

  @Test
  public void shouldSayBuzzWhizz() {
    assertEquals(BUZZ + WHIZZ, shout(FIVE * SEVEN));
    assertEquals(BUZZ + WHIZZ, shout(FIVE * SEVEN * 2));
  }

  @Test
  public void shouldSayFizzBuzzWhizz() {
    assertEquals(FIZZ + BUZZ + WHIZZ, shout(THREE * FIVE * SEVEN));
  }

  @Test
  public void shouldSayFizzBuzzWhizzBurnout() {
    assertEquals(FIZZ + BUZZ + WHIZZ + BURNOUT, shout(THREE * FIVE * SEVEN * THIRTEEN));
  }

  @Test
  public void shouldAlsoSayFizzBuzzWhizzBurnout() {
    assertEquals(FIZZ + BUZZ + WHIZZ + BURNOUT,
        shoutWithCoolMapThatIsNotSoCoolInTheEnd(THREE * FIVE * SEVEN * THIRTEEN));
  }

  public String shout(int number) {
    // solution with stringbuilder
    StringBuilder text = new StringBuilder();

    boolean sayFizz = number % THREE == 0;
    boolean sayBuzz = number % FIVE == 0;
    boolean sayWhizz = number % SEVEN == 0;
    boolean sayBurnout = number % THIRTEEN == 0;

    if (sayFizz) {
      text.append(FIZZ);
    }
    if (sayBuzz) {
      text.append(BUZZ);
    }
    if (sayWhizz) {
      text.append(WHIZZ);
    }
    if (sayBurnout) {
      text.append(BURNOUT);
    }

    if (text.length() == 0) text.append(number);
    return text.toString();
  }

  public String shoutWithCoolMapThatIsNotSoCoolInTheEnd(int number) {
    StringBuilder text = new StringBuilder();

    // the lowest number is always first
    for (Map.Entry<Integer, String> divisor : DIVISORS.entrySet()) {
      int dividend = divisor.getKey();
      String shout = divisor.getValue();

      if (number % dividend == 0) {
        text.append(shout);
      }
    }

    if (text.length() == 0) text.append(number);
    return text.toString();
  }
}
