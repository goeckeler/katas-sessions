package kata;

public class VoidCommand
  implements Command
{
  @Override
  public String getName() {
    return "void";
  }

  @Override
  public boolean isQuery() {
    return true;
  }

  @Override
  public boolean isMeta() {
    return true;
  }

  @Override
  public void execute() {
  }

  @Override
  public void undo() {
  }

  @Override
  public boolean parse(String parameters) {
    return true;
  }
}
