package dojo;

public class Binary
{
  private static final long BASE_2 = 2;

  public static String toBinary(long number) {
    return toRadix(number, BASE_2);
  }

  public static String toRadix(long number, long radix) {
    if (number < radix) return "" + number;

    long post = number % radix;
    long pre = number / radix;

    return toRadix(pre, radix) + post;
  }
}

/*
 * // 0
 * return Long.toBinaryString(number);
 * // 1
 * return "0";
 * // 2
 * return "" + number;
 * // 3
 * if (number == 2) return "10";
 * return "" + number;
 * // 4
 * if (number == 16) return "1000";
 * if (number == 2) return "10";
 * return "" + number;
 */
