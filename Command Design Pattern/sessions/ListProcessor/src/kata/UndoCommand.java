package kata;

public class UndoCommand
  implements Command
{
  @Override
  public String getName() {
    return "undo";
  }

  @Override
  public boolean isQuery() {
    return false;
  }

  @Override
  public boolean isMeta() {
    return true;
  }

  @Override
  public void execute() {
    // revoke the last command from the command history
  }

  @Override
  public void undo() {
    // meta commands cannot be undone
  }

  @Override
  public boolean parse(String parameters) {
    return true;
  }
}
