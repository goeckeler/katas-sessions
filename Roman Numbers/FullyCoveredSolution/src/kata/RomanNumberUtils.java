package kata;

public class RomanNumberUtils
{
  private static final int DECIMALS[] = {
    1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
  };

  private static final String ROMANS[] = {
    "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
  };

  public static String toRoman(int decimal) {
    checkForRange(decimal);
    return toRoman(decimal, 0);
  }

  public static int toDecimal(String roman) {
    checkForRomanLetters(roman);
    return toDecimal(roman, 0);
  }

  private static String toRoman(int decimal, int index) {
    if (decimal == 0) { return ""; }
    if (decimal >= DECIMALS[index]) { return ROMANS[index] + toRoman(decimal - DECIMALS[index], index); }
    return toRoman(decimal, ++index);
  }

  private static int toDecimal(String roman, int index) {
    if (roman.isEmpty()) { return 0; }
    if (roman.startsWith(ROMANS[index])) {
      //@formatter:off
      return DECIMALS[index] + toDecimal(roman.substring(ROMANS[index].length()), index); 
      //@formatter:on
    }
    return toDecimal(roman, ++index);
  }

  private static void checkForRange(int decimal) {
    if (decimal == 0) { throw new RuntimeException("Romans did not consider zero a number."); }
    if (decimal < 0) { throw new RuntimeException("Romans did not use negative numbers."); }
    if (decimal >= 4000) { throw new RuntimeException("Highest standardized roman number is 3999."); }
  }

  private static void checkForRomanLetters(String roman) {
    if (!roman.matches("[MDCLXVI]+")) { throw new RuntimeException(
        String.format("%1s contains invalid letters for a roman number.", roman)); }
  }
}
