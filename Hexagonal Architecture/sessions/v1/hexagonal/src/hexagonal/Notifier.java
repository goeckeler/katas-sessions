package hexagonal;

public interface Notifier<E extends Event> {
  public void notify(E event);
}
