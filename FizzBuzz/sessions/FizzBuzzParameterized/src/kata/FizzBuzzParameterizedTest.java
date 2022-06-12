package kata;

import static kata.FizzBuzz.fizzBuzz;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Demonstrates JUnit5 Parameterized test.
 * 
 * All test cases in one test, you can always do it this way with a hint. But you loose a lot of expressive power. First
 * of all, the intentions can only be seen if the test is executed, not by just looking at the code.
 *
 * Secondly, what about the corner cases and the happy paths? You cannot see how the class is supposed to be used. You
 * have to read through the execution log. Parameterised tests are good enough if you want to check for equivalences.
 * But please keep those equivalent cases together, and just those.
 */
class FizzBuzzParameterizedTest
{
  @ParameterizedTest(name = "[{index}] On {0} shout \"{1}\" coz {2}.")
  @CsvSource({
    "1, 1, it's just a number",
    "4, 4, it's just another number",
    "3, fizz, it can be divided by 3",
    "9, fizz, it is a multiple of 3",
    "5, buzz, it can be divided by 5",
    "10, buzz, it is a multiple of 5",
    "15, fizzbuzz, it can be divided by 3 and 5",
    "60, fizzbuzz, it can be divided by 3 and 5",
    "0, '', stay silent if it is not a natural number",
    "-3,'', stay silent if it is not a natural number"
  })
  void testCalls(int input, String output, String hint) {
    assertEquals(output, fizzBuzz(input));
  }
}
