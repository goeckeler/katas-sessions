package kata;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BinaryTest
{
  private static final int BASE_2 = 2;

  @Test
  public void shouldReturn0() {
    assertThat(toBinary(0), equalTo("0"));
  }

  @Test
  public void shouldReturn1() {
    assertThat(toBinary(1), equalTo("1"));
  }

  @Test
  public void shouldReturn2() {
    assertThat(toBinary(BASE_2), equalTo("10"));
  }

  @Test
  public void shouldReturn1010() {
    assertEquals("1010", toBinary(10));
  }

  @Test
  public void shouldReturn1111() {
    assertEquals("1111", toBinary(15));
  }

  public String toBinary(long number) {
    if (number < BASE_2) return "" + number;

    long tail = number % BASE_2;
    long head = number / BASE_2;

    return toBinary(head) + tail;
  }
}
