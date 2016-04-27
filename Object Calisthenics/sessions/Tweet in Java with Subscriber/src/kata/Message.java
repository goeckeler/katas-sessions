package kata;

public class Message
{
  private String text;
  private User postedBy;
  
  public static Message message(String text) {
    return new Message(text);
  }
  
  public Message(String text) {
    this.text = text;
  }
  
  public Message postedBy(User poster) {
    this.postedBy = poster;
    return this;
  }
  
  @Override
  public String toString() {
    return (postedBy == null ? "" : "[" + postedBy + "] ") + text;
  }
}
