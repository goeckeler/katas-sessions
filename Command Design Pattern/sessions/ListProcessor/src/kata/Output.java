package kata;

public class Output
{
  private static final Output instance = new Output();

  private Output() {
  }

  public static final Output output() {
    return instance;
  }

  private StringBuffer builder = null;

  public void write(String string) {
    text().append(string);
  }

  public void writeln(String string) {
    text().append(string).append('\n');
  }

  public void clear() {
    builder = null;
  }

  private StringBuffer text() {
    if (builder == null) {
      builder = new StringBuffer();
    }

    return builder;
  }

  @Override
  public String toString() {
    return text().toString();
  }
}
