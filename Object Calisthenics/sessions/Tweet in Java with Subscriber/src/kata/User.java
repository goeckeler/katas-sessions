package kata;

import java.util.LinkedList;
import java.util.List;

public class User
{
  private String name;
  private List<Message> postings = new LinkedList<>();
  private List<Subscriber> subscribers = new LinkedList<>();

  public User(final String name) {
    this.name = name;
  }

  public void post(Message message) {
    message.postedBy(this);
    postings.add(message);
    for (Subscriber subscriber : subscribers) {
      subscriber.notify(message);
    }
  }

  public void viewPosts(Subscriber subscriber) {
    for (Message message : postings) {
      subscriber.notify(message);
    }
  }

  public void subscribe(Subscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void unsubscribe(Subscriber subscriber) {
    subscribers.remove(subscriber);
  }

  public String toString() {
    return name;
  }
}
