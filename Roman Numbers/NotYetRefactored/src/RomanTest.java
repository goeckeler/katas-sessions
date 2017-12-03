import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class RomanTest {

	@Test
	public void given_1_return_I() {
		assertEquals("I", toRoman(1));
	}

	@Test
	public void given_2_return_II() {
		assertEquals("II", toRoman(2));
	}

	@Test
	public void given_3_return_III() {
		assertEquals("III", toRoman(3));
	}

	@Test
	public void given_4_return_IV() {
		assertEquals("IV", toRoman(4));
	}

	@Test
	public void given_7_return_VII() {
		assertEquals("VII", toRoman(7));
	}

	@Test
	public void given_39_return_XXXIX() {
		assertEquals("XXXIX", toRoman(39));
	}

	@Test
	public void given_50_return_L() {
		assertEquals("L", toRoman(50));
	}

	@Test
	public void given_100_return_L() {
		assertEquals("C", toRoman(100));
	}
	
	@Test
	@Ignore
	public void given_99_return_L() {
		assertEquals("C", toRoman(99));
	}

	public String toRoman(int decimal) {
		StringBuilder roman = new StringBuilder();


		if (decimal >= 100) {
			return "C" + toRoman(decimal - 100);
		}
		
		if (decimal >= 50) {
			return "L" + toRoman(decimal - 50);
		}

		if (decimal >= 10) {
			return "X" + toRoman(decimal - 10);
		}

		if (decimal == 9) {
			return "IX";
		}

		if (decimal >= 5) {
			return "V" + toRoman(decimal - 5);
		}

		if (decimal == 4) {
			return "IV";
		}

		for (int i = 0; i < decimal; i++) {
			roman.append("I");
		}
		return roman.toString();
	}
}
