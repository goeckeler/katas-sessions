package kata;

import static kata.Loader.readFromFile;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

public class AnagramLargeTest
{
  private static Anagram anagram;

  @Rule
  public TestRule timeout = new Timeout(200);

  @BeforeClass
  public static void setupClass()
    throws IOException
  {
    anagram = new Anagram(readFromFile("src/kata", "english.txt"));
  }

  @Test(timeout = 20)
  public void shouldFindAnagrams() {
    assertThat(anagram.anagramsFor("arrest"), hasItems("arrest", "rarest", "raster"));
    assertThat(anagram.getAnagramsFor("arrest"), containsString("rarest"));
  }

  @Test(timeout = 20)
  public void shouldFindMultipleAnagrams() {
    // also checks for sorting!
    assertThat(anagram.getAnagramsFor("listen"), equalTo("enlist, inlets, listen, silent, tinsel"));
  }

  @Test
  @Ignore
  public void shouldPrintAllAnagrams() {
    System.out.println(anagram.toString());
  }
}
