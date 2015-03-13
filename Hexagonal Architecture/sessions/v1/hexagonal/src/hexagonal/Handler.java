package hexagonal;

public interface Handler {
  void handle(Command command);
}
