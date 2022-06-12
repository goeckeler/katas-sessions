package kata;

import static kata.FizzBuzz.fizzBuzz;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Demonstrates alternative approach to equivalence test in JUnit5.
 * 
 * Alternative to parameterised tests that is.
 */
class FizzBuzzEquivalenceTest
{
  /**
   * Simply use two asserts if you only want to safeguard the first test.
   */
  @Test
  void shoutNumber() {
    assertEquals("1", fizzBuzz(1));
    assertEquals("4", fizzBuzz(4));
  }

  /**
   * Use <code>assertAll()</code> to test all asserts.
   */
  @Test
  void staySilentIfNotNatural() {
    // @formatter:off
    assertAll(
      () -> assertEquals("", fizzBuzz(0)), 
      () -> assertEquals("", fizzBuzz(-3))
    );
    // @formatter:on
  }

  /**
   * Also a parameterised test will do the trick. Is it really more readable?
   * 
   * @param number a natural number so that the caller shouts "fizz"
   */
  @ParameterizedTest
  @CsvSource({
    "3",
    "9",
    "33"
  })
  void shoutFizz(int number) {
    assertEquals("fizz", fizzBuzz(number));
  }

  /**
   * For simple tests a stream might be helpful as well.
   */
  @Test
  void shoutBuzz() {
    IntStream.of(5, 10, 25).forEach(number -> assertEquals("buzz", fizzBuzz(number)));
  }

  /**
   * Sometimes an own assertion might improve readability enormously.
   */
  @Test
  void shoutFizzBuzz() {
    assertShouts("fizzbuzz", 15, 30, 60);
  }

  private static void assertShouts(String string, int... numbers) {
    for (int number : numbers) {
      assertEquals(string, fizzBuzz(number));
    }
  }
}
