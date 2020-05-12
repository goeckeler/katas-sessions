package kata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ToBinaryTest
{

  @Test
  void shouldReturnZero() {
    assertEquals("0", toBinary(0));
  }

  @Test
  void shouldReturnOne() {
    assertEquals("1", toBinary(1));
  }

  @Test
  void shouldReturn10() {
    assertEquals("10", toBinary(2));
  }

  @RepeatedTest(20)
  void shouldReturn101() {
    assertEquals("101", toBinary(5));
  }

  @ParameterizedTest
  @CsvSource({
    "  0,       0",
    "  1,       1",
    "  2,      10",
    "  5,     101",
    "127, 1111111"
  })
  void shouldConvertNumbers(final int decimal, final String binary) {
    assertEquals(binary, toBinary(decimal));
  }

  @Test
  void shouldReturn1010() {
    assertEquals("1010", toBinary(10));
  }

  private String toBinary(final int decimal) {
    if (decimal < 2) return Integer.toString(decimal);

    final int tail = decimal % 2;
    final int head = decimal / 2;

    return toBinary(head) + toBinary(tail);
  }
}
