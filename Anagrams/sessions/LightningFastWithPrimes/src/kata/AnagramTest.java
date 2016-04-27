package kata;

import static kata.Loader.readFromFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class AnagramTest
{
  private static Anagram anagram;

  @BeforeClass
  public static void setup()
    throws IOException
  {
    anagram = new Anagram(readFromFile("src/kata", "short-list.txt"));
  }

  @Test
  public void shouldBeAnagrams() {
    assertTrue(anagram.isAnagram("arrest", "rarest"));
    assertTrue(anagram.isAnagram("kinship", "pinkish"));
  }

  @Test
  public void shouldNotBeAnagrams() {
    assertFalse(anagram.isAnagram("arrest", "barest"));
  }

  @Test
  public void shouldFindAnagrams() {
    assertThat(anagram.anagramsFor("arrest"), hasItems("arrest", "rarest"));
    assertThat(anagram.getAnagramsFor("arrest"), equalTo("arrest, rarest"));
  }

  @Test
  public void shouldFindMultipleAnagrams() {
    // also checks for sorting!
    assertThat(anagram.getAnagramsFor("listen"), equalTo("enlist, inlets, listen, silent"));
  }
}
