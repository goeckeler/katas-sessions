package kata;

import static kata.Model.model;

public class AddCommand
  implements Command
{
  private String item;

  @Override
  public String getName() {
    return "add";
  }

  @Override
  public boolean isQuery() {
    return false;
  }

  @Override
  public boolean isMeta() {
    return false;
  }

  @Override
  public void execute() {
    model().add(item);
  }

  @Override
  public void undo() {
    model().remove(item);
  }

  @Override
  public boolean parse(final String parameters) {
    if (parameters == null || parameters.isEmpty()) return false;
    item = parameters;
    return true;
  }
}
