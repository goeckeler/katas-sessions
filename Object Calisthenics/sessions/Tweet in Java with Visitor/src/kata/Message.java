package kata;

public class Message
{
  private String text;
  
  public Message(String text) {
    this.text = text;
  }
  
  @Override
  public String toString() {
    return text;
  }
}
