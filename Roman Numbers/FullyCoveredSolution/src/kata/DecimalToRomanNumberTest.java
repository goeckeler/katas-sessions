package kata;

import static kata.RomanNumberUtils.toRoman;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DecimalToRomanNumberTest {

	@Test
	public void shouldCoverAllLiterals() {
		assertThat(toRoman(1), equalTo("I"));
		assertThat(toRoman(5), equalTo("V"));
		assertThat(toRoman(10), equalTo("X"));
		assertThat(toRoman(50), equalTo("L"));
		assertThat(toRoman(100), equalTo("C"));
		assertThat(toRoman(500), equalTo("D"));
		assertThat(toRoman(1_000), equalTo("M"));
	}

	@Test
	public void shouldCoverAll4x() {
		assertThat(toRoman(4), equalTo("IV"));
		assertThat(toRoman(40), equalTo("XL"));
		assertThat(toRoman(400), equalTo("CD"));
	}

	@Test
	public void shouldCoverAll9x() {
		assertThat(toRoman(9), equalTo("IX"));
		assertThat(toRoman(90), equalTo("XC"));
		assertThat(toRoman(900), equalTo("CM"));
	}

	@Test
	public void shouldCoverComplexNumbers() {
		assertThat(toRoman(99), equalTo("XCIX"));
		assertThat(toRoman(639), equalTo("DCXXXIX"));
		assertThat(toRoman(444), equalTo("CDXLIV"));
		assertThat(toRoman(1913), equalTo("MCMXIII"));
		assertThat(toRoman(2768), equalTo("MMDCCLXVIII"));
	}

	@Test(expected = RuntimeException.class)
	public void shouldFailOnNegatives() {
		toRoman(-4);
	}

	@Test(expected = RuntimeException.class)
	public void shouldFailOnZero() {
		toRoman(0);
	}

	@Test(expected = RuntimeException.class)
	public void shouldFailOnMaximum() {
		toRoman(4000);
	}
}
