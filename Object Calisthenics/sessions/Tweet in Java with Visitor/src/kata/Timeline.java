package kata;

public class Timeline
{
  private User owner;
  private Messages messages = new Messages();

  public Timeline(User owner) {
    this.owner = owner;
  }

  public boolean isOwnedBy(User user) {
    return owner.equals(user);
  }

  public void publish(Message message) {
    messages.add(message);
  }

  public void accept(Visitor visitor) {
    messages.accept(visitor);
  }

  @Override
  public String toString() {
    return messages.toString();
  }

  public Messages viewTimeline() {
    return messages;
  }
}
