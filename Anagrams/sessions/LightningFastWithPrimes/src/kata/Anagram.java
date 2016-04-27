package kata;

import java.text.NumberFormat;
import java.util.*;

public class Anagram
{
  private static final Map<Character, Long> PRIMES = new HashMap<>(41);
  private static final String IGNORE = "'\"";

  static {
    PRIMES.put('a', 2l);
    PRIMES.put('b', 3l);
    PRIMES.put('c', 5l);
    PRIMES.put('d', 7l);
    PRIMES.put('e', 11l);
    PRIMES.put('f', 13l);
    PRIMES.put('g', 17l);
    PRIMES.put('h', 19l);
    PRIMES.put('i', 23l);
    PRIMES.put('j', 29l);
    PRIMES.put('k', 31l);
    PRIMES.put('l', 37l);
    PRIMES.put('m', 41l);
    PRIMES.put('n', 43l);
    PRIMES.put('o', 47l);
    PRIMES.put('p', 53l);
    PRIMES.put('q', 59l);
    PRIMES.put('r', 61l);
    PRIMES.put('s', 67l);
    PRIMES.put('t', 71l);
    PRIMES.put('u', 73l);
    PRIMES.put('v', 79l);
    PRIMES.put('w', 83l);
    PRIMES.put('x', 89l);
    PRIMES.put('y', 97l);
    PRIMES.put('z', 101l);
    PRIMES.put('ö', 103l);
    PRIMES.put('ä', 107l);
    PRIMES.put('ü', 109l);
    PRIMES.put('ß', 113l);
  };

  private Map<Long, Set<String>> dictionary = null;

  public Anagram(final List<String> words) {
    dictionary = new HashMap<>(Math.max(1, words.size()));
    for (String line : words) {
      String word = line.trim();
      long hash = hash(word);
      Set<String> anagrams = dictionary.get(hash);
      if (anagrams == null) {
        anagrams = new TreeSet<>();
        dictionary.put(hash, anagrams);
      }
      anagrams.add(word);
    }
  }

  public List<String> anagramsFor(final String word) {
    List<String> anagrams = new ArrayList<>();
    Set<String> candidates = dictionary.get(hash(word));
    if (candidates != null && candidates.size() > 0) anagrams.addAll(candidates);
    return anagrams;
  }

  public String getAnagramsFor(final String word) {
    StringBuilder string = new StringBuilder();
    for (String anagram : anagramsFor(word)) {
      if (string.length() > 0) {
        string.append(", ");
      }
      string.append(anagram);
    }
    return string.toString();
  }

  public Set<String> findAllWordsIn(final String word) {
    return findAllWordsIn(word, 1);
  }

  public Set<String> findAllWordsIn(final String word, int minimumLength) {
    return findAllWordsIn(word, minimumLength, new TreeSet<String>());
  }

  private Set<String> findAllWordsIn(final String word, final int minimumLength, final Set<String> words) {
    if (word.length() <= minimumLength) return Collections.emptySet();
    words.addAll(anagramsFor(word));

    for (int pos = 0; pos < word.length(); ++pos) {
      String shorterWord = word.substring(0, pos) + word.substring(pos + 1);
      words.addAll(findAllWordsIn(shorterWord, minimumLength, words));
    }

    return words;
  }

  public boolean isAnagram(final String word, final String otherWord) {
    return hash(word) == hash(otherWord);
  }

  public int size() {
    return dictionary.size();
  }

  private long hash(final String word) {
    long hash = 1;
    if (word != null) {
      for (Character character : word.trim().toCharArray()) {
        if (IGNORE.indexOf(character) >= 0) continue;

        Long prime = PRIMES.get(Character.toLowerCase(character));
        if (prime == null) {
          System.err.println("Unknown anagram character '" + character + "' in word '" + word + "'.");
          System.exit(1);
        }
        hash *= prime;
      }
    }
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder string = new StringBuilder("Anagrams\n").append("===================================\n");
    int sets = 0;
    for (Set<String> anagrams : dictionary.values()) {
      if (anagrams.size() > 1) {
        sets++;
        for (String word : anagrams) {
          string.append(word).append(" ");
        }
        string.append("\n");
      }
    }
    string.append("===================================\n");
    string.append(NumberFormat.getIntegerInstance().format(sets)).append(" entries.\n");
    string.append("===================================\n");

    return string.toString();
  }
}
