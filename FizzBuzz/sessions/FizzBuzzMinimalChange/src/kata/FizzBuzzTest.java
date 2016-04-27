package kata;

import static kata.FizzBuzz.BUZZ;
import static kata.FizzBuzz.FIZZ;
import static kata.FizzBuzz.WHIZZ;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzTest
{
  // declared here as the user story tells us to use these constants
  private static final int THREE = 3;
  private static final int FIVE = 5;
  private static final int SEVEN = 7;

  @Test
  public void shouldSayNumber() {
    assertEquals("1", FizzBuzz.shout(1));
    assertEquals("11", FizzBuzz.shout(11));
  }

  @Test
  public void shouldSayFizz() {
    assertEquals(FIZZ, FizzBuzz.shout(THREE));
    assertEquals(FIZZ, FizzBuzz.shout(THREE * THREE));
  }

  @Test
  public void shouldSayBuzz() {
    assertEquals(BUZZ, FizzBuzz.shout(FIVE));
    assertEquals(BUZZ, FizzBuzz.shout(FIVE * FIVE));
  }

  @Test
  public void shouldSayWhizz() {
    assertEquals(WHIZZ, FizzBuzz.shout(SEVEN));
    assertEquals(WHIZZ, FizzBuzz.shout(SEVEN * SEVEN));
  }

  @Test
  public void shouldSayFizzBuzz() {
    final String fizzBuzz = FIZZ + BUZZ;

    assertEquals(fizzBuzz, FizzBuzz.shout(THREE * FIVE));
    assertEquals(fizzBuzz, FizzBuzz.shout(THREE * FIVE * 2));
  }

  @Test
  public void shouldSayFizzWhizz() {
    final String fizzWhizz = FIZZ + WHIZZ;

    assertEquals(fizzWhizz, FizzBuzz.shout(THREE * SEVEN));
    assertEquals(fizzWhizz, FizzBuzz.shout(THREE * SEVEN * 2));
  }

  @Test
  public void shouldSayBuzzWhizz() {
    final String buzzWhizz = BUZZ + WHIZZ;

    assertEquals(buzzWhizz, FizzBuzz.shout(FIVE * SEVEN));
    assertEquals(buzzWhizz, FizzBuzz.shout(FIVE * SEVEN * 2));
  }

  @Test
  public void shouldSayFizzBuzzWhizz() {
    final String fizzBuzzWhizz = FIZZ + BUZZ + WHIZZ;

    assertEquals(fizzBuzzWhizz, FizzBuzz.shout(THREE * FIVE * SEVEN));
  }

  @Test
  public void shouldPlayCorrectlyUntil15() {
    StringBuilder output = new StringBuilder();
    for (int number = 1; number <= 15; ++number) {
      output.append(FizzBuzz.shout(number)).append('.');
    }

    assertEquals("1.2.fizz.4.buzz.fizz.whizz.8.fizz.buzz.11.fizz.13.whizz.fizzbuzz.", output.toString());
  }
}
