package kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProcessorTest
{
  private Processor processor;

  @Before
  public void setup() {
    processor = new Processor();
    Output.output().clear();
    Model.model().clear();
  }

  @Test
  public void shouldIgnoreEmptyLines() {
    String[] commands = new String[] {
      "", "add Hugo", "", "list"
    };

    assertTrue("A valid command was not interpreted as such.", processor.execute(commands));
    assertEquals("Hugo\n", Output.output().toString());
  }

  @Test
  public void shouldListItem() {
    String[] commands = new String[] {
      "add Thorsten", "list"
    };

    assertTrue("A valid command was not interpreted as such.", processor.execute(commands));
    assertEquals("Thorsten\n", Output.output().toString());
  }

  @Test
  public void shouldListItems() {
    String[] commands = new String[] {
      "add Thorsten", "add Wolfgang", "list"
    };

    assertTrue("A valid command was not interpreted as such.", processor.execute(commands));
    assertEquals("Thorsten\nWolfgang\n", Output.output().toString());
  }
}
