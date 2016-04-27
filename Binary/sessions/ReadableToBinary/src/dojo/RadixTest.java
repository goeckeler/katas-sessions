package dojo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RadixTest
{

  @Test
  public void shouldReturn0() {
    assertThat(toBinary(0), equalTo("0"));
  }

  @Test
  public void shouldReturn1() {
    assertThat(toBinary(1), equalTo("1"));
  }

  @Test
  public void shouldReturn10() {
    assertThat(toBinary(2), equalTo("10"));
  }

  @Test
  public void shouldReturn10000() {
    assertThat(toBinary(16), equalTo("10000"));
  }

  @Test
  public void shouldReturn1010() {
    assertEquals("1010", toBinary(10));
  }

  public String toBinary(long number) {
    if (number < 2) return "" + number;

    long tail = number % 2;
    long head = number / 2;

    return toBinary(head) + tail;
  }
}
