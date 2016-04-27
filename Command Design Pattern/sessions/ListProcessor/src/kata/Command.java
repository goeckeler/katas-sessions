package kata;

public interface Command
{
  String getName();

  boolean isQuery();

  boolean isMeta();

  void execute();

  void undo();

  boolean parse(String parameters);
}
