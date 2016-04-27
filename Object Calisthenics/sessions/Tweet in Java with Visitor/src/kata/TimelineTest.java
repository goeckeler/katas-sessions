package kata;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimelineTest
{
  private User alice;
  private Timeline aliceTimeline;

  @Before
  public void setup() {
    alice = new User("Alice");
    aliceTimeline = new Timeline(alice);
  }

  @Test
  public void aliceShouldPostMessage() {
    Message message = new Message("a");
    aliceTimeline.publish(message);
    ContainsVisitor visitor = new ContainsVisitor(message);
    aliceTimeline.accept(visitor);
    assertTrue(visitor.contains());
  }

  @Test
  public void aliceCanReadTimelineEasily() {
    Messages postings = aliceTimeline.viewTimeline();
    assertNotNull(postings);
  }
}

class ContainsVisitor
  implements Visitor
{
  private Message containingMessage;
  private boolean contains = false;

  public ContainsVisitor(Message containingMessage) {
    this.containingMessage = containingMessage;
  }

  @Override
  public void visit(Message message) {
    contains |= containingMessage.equals(message);
  }

  public boolean contains() {
    return contains;
  }

  public void reset() {
    contains = false;
  }
}
