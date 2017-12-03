package kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RomanNumbers {
    private static final String[] ROMANS = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    private static final int[] DECIMALS = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

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
    public void given_5_return_V() {
        assertEquals("V", toRoman(5));
    }

    @Test
    public void given_8_return_VIII() {
        assertEquals("VIII", toRoman(8));
    }

    @Test
    public void given_4_return_IV() {
        assertEquals("IV", toRoman(4));
    }

    @Test
    public void given_3999_return_MMMCMXCIX() {
        assertEquals("MMMCMXCIX", toRoman(3999));
    }

    @Test(expected = IllegalArgumentException.class)
    public void given_negative_number_then_fail() {
        toRoman(-4);
    }

    public static String toRoman(int decimal) {
        if (decimal < 1) {
            throw new IllegalArgumentException(String.format("Romans did not know about '%d'.", decimal));
        }
        return toRoman(decimal, 0);
    }

    private static String toRoman(int decimal, int index) {
        int denomination = DECIMALS[index];

        if (decimal > denomination) {
            return toRoman(denomination) + toRoman(decimal - denomination);
        }

        if (decimal == denomination) {
            return ROMANS[index];
        }

        // next denomination
        return toRoman(decimal, index + 1);
    }

    @Test
    public void given_MCMLXXVI_return_1976() {
        assertEquals(1976, toDecimal("MCMLXXVI"));
    }

    public static int toDecimal(String roman) {
        return toDecimal(roman, 0);
    }

    private static int toDecimal(String roman, int index) {
        if (index >= ROMANS.length) {
            return 0;
        }

        String literal = ROMANS[index];
        if (roman.startsWith(literal)) {
          return DECIMALS[index] + toDecimal(roman.substring(literal.length()), index);
        }

        return toDecimal(roman, index + 1);
    }
}
