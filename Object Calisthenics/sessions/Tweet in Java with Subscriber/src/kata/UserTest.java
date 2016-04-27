package kata;

import static kata.Message.message;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest
{
  private User alice = new User("Alice");

  @Test
  public void aliceShouldBeAbleToPost() {
    alice.post(message("hello"));
    alice.viewPosts(new Subscriber()
    {
      @Override
      public void notify(Message message) {
        assertEquals("[Alice] hello", message.toString());
      }
    });
  }
  
  @Test
  public void aliceShouldHaveTimeline()
  {
    alice.post(message("hello"));
    alice.post(message("dolly"));
    alice.viewPosts(new Subscriber()
    {
      @Override
      public void notify(Message message) {
        assertTrue("[Alice] hello [Alice] dolly".contains(message.toString()));
      }
    });
  }
}
