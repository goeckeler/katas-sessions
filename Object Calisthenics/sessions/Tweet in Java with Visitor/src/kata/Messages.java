package kata;

import java.util.LinkedList;
import java.util.List;

public class Messages
{
  private List<Message> messages = new LinkedList<>();
  
  public void add(Message message) {
    messages.add(message);
  }

  public boolean contains(Message message) {
    return messages.contains(message);
  }
  
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();
    for (Message message : messages) {
      string.append(message + "\n");
    }
    return string.toString();
  }

  public void accept(Visitor visitor) {
    for (Message message : messages) {
      visitor.visit(message);
    }
  }
}
