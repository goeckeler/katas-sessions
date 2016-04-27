package kata;

public class ListCommand
  implements Command
{
  @Override
  public String getName() {
    return "list";
  }

  @Override
  public boolean isQuery() {
    return true;
  }

  @Override
  public boolean isMeta() {
    return false;
  }

  @Override
  public void execute() {
    for (String item : Model.model().list()) {
      Output.output().writeln(item);
    }
  }

  @Override
  public void undo() {
  }

  @Override
  public boolean parse(String parameters) {
    return true;
  }
}
