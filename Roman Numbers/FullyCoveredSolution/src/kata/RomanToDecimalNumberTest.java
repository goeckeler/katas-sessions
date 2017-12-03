package kata;

import static kata.RomanNumberUtils.toDecimal;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RomanToDecimalNumberTest {

	@Test
	public void shouldConvertToDecimals() {
		assertThat(toDecimal("I"), equalTo(1));
		assertThat(toDecimal("XCIX"), equalTo(99));
		assertThat(toDecimal("DCXXXIX"), equalTo(639));
		assertThat(toDecimal("CDXLIV"), equalTo(444));
		assertThat(toDecimal("MCMXIII"), equalTo(1913));
		assertThat(toDecimal("MMDCCLXVIII"), equalTo(2768));
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldFailOnInvalidLetters() {
		toDecimal("MCAX");
	}
}
