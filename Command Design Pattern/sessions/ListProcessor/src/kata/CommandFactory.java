package kata;

public class CommandFactory
{
  public static Command commandFor(final String input) {
    if (input == null || input.isEmpty()) { return new VoidCommand(); }

    Command command = null;
    switch (input.toLowerCase().split("\\s")[0]) {
      case "add" :
        command = new AddCommand();
        break;
      case "list" :
        command = new ListCommand();
        break;
      default :
        return null;
    }

    int parameterIndex = Math.min(1 + command.getName().length(), input.length());
    command.parse(input.substring(parameterIndex));
    return command;
  }
}
