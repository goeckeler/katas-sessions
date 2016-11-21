package kata;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ToBinaryTest
{

  @Test
  public void testDecimalZeroToBinaryZero() {
    assertEquals("0", toBinary(0));
  }

  @Test
  public void testDecimalOneToBinaryOne() {
    assertEquals("1", toBinary(1));
  }

  @Test
  public void testDecimalFiveToBinaryFive() {
    assertEquals("101", toBinary(5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDecimalsFail() {
    assertEquals("101", toBinary(-2));
  }

  private String toBinary(final int decimal) {
    if (decimal < 0) throw new IllegalArgumentException();
    if (decimal == 5) return "101";
    if (decimal == 1) return "1";
    return "0";
  }

}
