package kata;

public class FizzBuzz
{
  public static String fizzBuzz(int integer) {
    String shout = "";
    if (integer <= 0) return shout;

    if (integer % 3 == 0) shout += "fizz";
    if (integer % 5 == 0) shout += "buzz";
    if (shout.isEmpty()) shout += integer;

    return shout;
  }
}
