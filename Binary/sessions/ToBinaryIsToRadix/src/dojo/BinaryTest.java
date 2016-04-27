package dojo;

import static dojo.Binary.toBinary;
import static dojo.Binary.toRadix;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BinaryTest
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
  public void shouldReturn1000() {
    assertThat(toBinary(16), equalTo("10000"));
  }

  @Test
  public void shouldReturn1010() {
    assertThat(toBinary(10), equalTo("1010"));
  }

  @Test
  public void shouldConvertToOctal() {
    assertThat(toRadix(8, 8), equalTo("10"));
    assertThat(toRadix(4, 8), equalTo("4"));
    assertThat(toRadix(27, 8), equalTo("33"));
  }
}
