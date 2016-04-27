package kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FizzBuzz
{
  // declared package private so that only the test can see it
  static final String FIZZ = "fizz";
  static final String BUZZ = "buzz";
  static final String WHIZZ = "whizz";

  // sequence in which a word shall be shouted
  private static final List<Shouter> specialShouters = new ArrayList<>();
  static {
    specialShouters.add(new FizzShouter());
    specialShouters.add(new BuzzShouter());
    specialShouters.add(new WhizzShouter());
  }

  public static String shout(int number) {
    // visit special shouters and add their output to the stream
    String shout = specialShouters.stream().map(x -> x.shout(number)).collect(Collectors.joining());

    // there is also the classic way of doing this:
    //
    // @formatter:off
    // StringBuilder string = new StringBuilder();
    // for (Shouter shouter : specialShouters) {
    //   string.append(shouter.shout(number));
    // }
    // String shout = string.toString();
    // @formatter:on

    // if no one shouted something special we shall certainly call the number
    return (shout.isEmpty() ? "" + number : shout);
  }
}

@FunctionalInterface
interface Shouter
{
  String shout(int number);
}

abstract class AbstractShouter
implements Shouter
{
  @Override
  public final String shout(int number) {
    if (shoutOn(number)) { return what(); }
    return "";
  }

  /** when to shout for the given number */
  protected abstract boolean shoutOn(int number);

  /** what to shout if applicable */
  protected abstract String what();
}

class FizzShouter
extends AbstractShouter
{
  @Override
  protected boolean shoutOn(int number) {
    return number % 3 == 0;
  }

  @Override
  protected String what() {
    return FizzBuzz.FIZZ;
  }
}

class BuzzShouter
extends AbstractShouter
{
  @Override
  protected boolean shoutOn(int number) {
    return number % 5 == 0;
  }

  @Override
  protected String what() {
    return FizzBuzz.BUZZ;
  }
}

class WhizzShouter
extends AbstractShouter
{
  @Override
  protected boolean shoutOn(int number) {
    return number % 7 == 0;
  }

  @Override
  protected String what() {
    return FizzBuzz.WHIZZ;
  }
}
