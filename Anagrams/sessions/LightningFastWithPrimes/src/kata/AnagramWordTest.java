package kata;

import static kata.Loader.readFromFile;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class AnagramWordTest
{
  private static Anagram anagram;
  private static List<String> dictionary;

  @BeforeClass
  public static void setup()
    throws IOException
  {
    dictionary = readFromFile("src/kata", "english.txt");
    anagram = new Anagram(dictionary);
  }

  @Test
  public void shouldFindWordsWithSameLetters() {
    final String word = "arrest";
    final int minimumLetters = 3;

    Set<String> words = anagram.findAllWordsIn(word, minimumLetters);
    assertNotNull(words);
    assertTrue(words.size() > 1);
    System.out.println("Found " + words.size() + " words in " + word + ": " + words);
    for (String containedWord : words) {
      assertTrue(containedWord.length() >= minimumLetters);
      assertThat(dictionary, hasItems(containedWord));
    }
  }
}
