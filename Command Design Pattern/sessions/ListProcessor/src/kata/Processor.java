package kata;

public class Processor
{
  public static void main(String[] args) {
    new Processor().execute(args);
  }

  public boolean execute(String[] input) {
    System.out.println("Running command script ...");
    for (String line : input) {
      System.out.println(line);
      Command command = CommandFactory.commandFor(line);
      if (command == null) {
        System.err.println("Invalid command in \"" + line + "\".");
        return false;
      }
      command.execute();
      if (!command.isMeta()) {
        System.out.print(Output.output().toString());
      }
    }
    System.out.println("Done.");
    return true;
  }
}
