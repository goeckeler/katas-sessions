/*
 * LAMBDA PROGRAMMING LABORATORY
 *
 * For each exercise, develop a solution using Java SE 8 Lambda/Streams
 * and remove the @Ignore tag. Then run the tests.
 *
 * Several of the exercises read data from a text file. The field named
 * "reader" is a BufferedReader which will be opened for you on the text file.
 * In any exercise that refers to reading from the text file, you can simply
 * use the "reader" variable without worry about opening or closing it.
 * This is setup by JUnit using the @Before and @After methods at the bottom of
 * this file. The text file is "SonnetI.txt" (Shakespeare's first sonnet) which
 * is located at the root of this NetBeans project.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Exercises {

    // ========================================================
    // DEFAULT METHODS
    // ========================================================

    /**
     * Create a string that consists of the first letters of each
     * word in the input list.
     */
    @Test
    public void ex01_accumulateFirstLetters() {
        List<String> input = Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot");
        String result = input.stream().map(x -> x.substring(0, 1)).collect(Collectors.joining());
        assertEquals("abcdef", result);
    }

    /**
     * Remove the words that have odd lengths from the list.
     */
    @Test
    public void ex02_removeOddLengthWords() {
        List<String> list = new ArrayList<>(Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));
        // list = list.stream().filter(s -> s.length() % 2 == 0).collect(Collectors.toList());
        list.removeIf(s -> s.length() % 2 == 1);
        assertEquals("[alfa, echo]", list.toString());
    }

    /**
     * Replace every word in the list with its upper case equivalent.
     */
    @Test
    public void ex03_upcaseAllWords() {
        List<String> list = new ArrayList<>(Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));
        list = list.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
        assertEquals("[ALFA, BRAVO, CHARLIE, DELTA, ECHO, FOXTROT]", list.toString());
    }

    /**
     * Convert every key-value pair of a map into a string and append them all
     * into a single string, in iteration order.
     */
    @Test
    public void ex04_stringifyMap() {
        Map<String, Integer> input = new TreeMap<>();
        input.put("c", 3);
        input.put("b", 2);
        input.put("a", 1);

        String result = input.entrySet().stream().map(x -> x.getKey() + "" + x.getValue())
                .collect(Collectors.joining());

        assertEquals("a1b2c3", result);
    }

    /**
     * Given a list of words, create a map whose keys are the first letters of
     * each words, and whose values are the sum of the lengths of those words.
     */
    @Test
    public void ex05_mapOfStringLengths() {
        List<String> list = Arrays
                .asList("aardvark", "bison", "capybara", "alligator", "bushbaby", "chimpanzee", "avocet", "bustard",
                        "capuchin");
        Map<String, Integer> result = new TreeMap<>();

        list.forEach(s -> result.merge(s.substring(0, 1), s.length(), Integer::sum));

        assertEquals("{a=23, b=20, c=26}", result.toString());
    }
    // Hint:
    // Use Map.merge() within Iterable.forEach().

    // ========================================================
    // SIMPLE STREAM PIPELINES
    // ========================================================

    /**
     * Given a list of words, create an output list that contains
     * only the odd-length words, converted to upper case.
     */
    @Test
    public void ex06_upcaseOddLengthWords() {
        List<String> input = new ArrayList<>(Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        List<String> result = input.stream()
                                   .filter(s -> s.length() % 2 == 1)
                                   .map(s -> s.toUpperCase())
                                   .collect(Collectors.toList());

        assertEquals("[BRAVO, CHARLIE, DELTA, FOXTROT]", result.toString());
    }
    // Hint 1:
    // Use filter() and map().

    // Hint 2:
    // Use collect() to create the result list.

    /**
     * Join the second letters of words 1 through 4 of the list (inclusive,
     * counting from zero), separated by commas, into a single string.
     */
    @Test
    public void ex07_joinStreamRange() {
        List<String> input = new ArrayList<>(Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot"));

        String result = input.stream()
                             .skip(1)
                             .limit(4)
                             .map(s -> s.substring(1,2))
                             .collect(Collectors.joining(","));

        assertEquals("r,h,e,c", result);
    }
    // Hint 1:
    // Use Stream.skip() and Stream.limit().

    // Hint 2:
    // Use Collectors.joining().

    /**
     * Count the number of lines in the text file. (Remember to
     * use the BufferedReader named "reader" that has already been
     * opened for you.)
     *
     * @throws IOException
     */
    @Test
    public void ex08_countLinesInFile() throws IOException {
        long count = reader.lines().count();

        assertEquals(14, count);
    }
    // Hint 1:
    // Use BufferedReader.lines() to get a stream of lines.

    // Hint 2:
    // Use Stream.count().

    /**
     * Find the length of the longest line in the text file.
     *
     * @throws IOException
     */
    @Test
    public void ex09_findLengthOfLongestLine() throws IOException {
        int longestLength = reader.lines().mapToInt(String::length).max().getAsInt();

        assertEquals(53, longestLength);
    }
    // Hint 1:
    // Use Stream.mapToInt() to convert to IntStream.

    // Hint 2:
    // Look at java.util.OptionalInt to get the result.

    /**
     * Find the longest line in the text file.
     *
     * @throws IOException
     */
    @Test
    public void ex10_findLongestLine() throws IOException {
        String longest = reader.lines().max(Comparator.comparingInt(String::length)).get();

        assertEquals("Feed'st thy light's flame with self-substantial fuel,", longest);
    }
    // Hint 1:
    // Use Stream.max() with a Comparator.

    // Hint 2:
    // Use static methods on Comparator to help create a Comparator instance.

    /**
     * Select the set of words from the input list whose length is greater than
     * to the word's position (starting from zero) in the list.
     */
    @Test
    public void ex11_selectByLengthAndPosition() {
        List<String> input = new ArrayList<>(
                Arrays.asList("alfa", "bravo", "charlie", "delta", "echo", "foxtrot", "golf", "hotel"));

        List<String> result = IntStream.range(0, input.size())
                                       .filter(i -> input.get(i).length() > i)
                                       .mapToObj(i -> input.get(i))
                                       .collect(Collectors.toList());

        assertEquals("[alfa, bravo, charlie, delta, foxtrot]", result.toString());
    }
    // Hint:
    // Instead of a stream of words (Strings), run an IntStream of positions.

    /**
     * Given two lists of Integer, compute a third list where each element is the
     * difference between the corresponding elements of the two input lists
     * (first minus second).
     */
    @Test
    public void ex12_listDifference() {
        List<Integer> one = Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5, 3);
        List<Integer> two = Arrays.asList(2, 7, 1, 8, 2, 8, 1, 8, 2, 8);

        List<Integer> result = IntStream.range(0, one.size())
                                        .mapToObj(i -> one.get(i) - two.get(i))
                                        .collect(Collectors.toList());

        assertEquals("[1, -6, 3, -7, 3, 1, 1, -2, 3, -5]", result.toString());
    }
    // Hint 1:
    // Run an IntStream of list positions (indexes).

    // Hint 2:
    // Deal with boxed Integers either by casting or by using mapToObj().

    // ========================================================
    // INTERMEDIATE STREAM PIPELINES
    // ========================================================

    /**
     * Convert a list of strings into a list of characters.
     */
    @Test
    @Ignore
    public void ex13_stringsToCharacters() {
        List<String> input = Arrays.asList("alfa", "bravo", "charlie");

        List<Character> result = input.stream()
                                      .flatMap(string -> string.chars().mapToObj(c -> (char) c))
                                      .collect(Collectors.toList());

        assertEquals("[a, l, f, a, b, r, a, v, o, c, h, a, r, l, i, e]", result.toString());
        assertTrue(result.stream().allMatch(x -> x instanceof Character));
    }
    // Hint 1:
    // Use Stream.flatMap().

    // Hint 2:
    // Pay attention to the return type of String.chars() and boxing conversion.

    /**
     * Collect all the words from the text file into a list.
     * Use String.split(REGEXP) to split a string into words.
     * REGEXP is defined at the bottom of this file.
     *
     * @throws IOException
     */
    @Test
    public void ex14_listOfAllWords() throws IOException {
        List<String> output = reader.lines()
                                    .flatMap(l -> Arrays.stream(l.split(REGEXP)))
                                    .collect(Collectors.toList());

        assertEquals(
                Arrays.asList("From", "fairest", "creatures", "we", "desire", "increase", "That", "thereby", "beauty's",
                        "rose", "might", "never", "die", "But", "as", "the", "riper", "should", "by", "time", "decease",
                        "His", "tender", "heir", "might", "bear", "his", "memory", "But", "thou", "contracted", "to",
                        "thine", "own", "bright", "eyes", "Feed'st", "thy", "light's", "flame", "with", "self",
                        "substantial", "fuel", "Making", "a", "famine", "where", "abundance", "lies", "Thy", "self",
                        "thy", "foe", "to", "thy", "sweet", "self", "too", "cruel", "Thou", "that", "art", "now", "the",
                        "world's", "fresh", "ornament", "And", "only", "herald", "to", "the", "gaudy", "spring",
                        "Within", "thine", "own", "bud", "buriest", "thy", "content", "And", "tender", "churl",
                        "mak'st", "waste", "in", "niggarding", "Pity", "the", "world", "or", "else", "this", "glutton",
                        "be", "To", "eat", "the", "world's", "due", "by", "the", "grave", "and", "thee"), output);
    }
    // Hint:
    // Use Stream.flatMap().

    /**
     * Read the words from the text file, and create a list containing the words
     * of length 8 or longer, converted to lower case, and sorted alphabetically.
     *
     * @throws IOException
     */
    @Test
    public void ex15_longLowerCaseSortedWords() throws IOException {
        List<String> output = reader.lines()
                                    .flatMap(l -> Arrays.stream(l.split(REGEXP)))
                                    .filter(s -> s.length() >= 8)
                                    .map(s -> s.toLowerCase())
                                    .sorted()
                                    .collect(Collectors.toList());

        assertEquals(
                Arrays.asList("abundance", "beauty's", "contracted", "creatures", "increase", "niggarding", "ornament",
                        "substantial"), output);
    }
    // Hint:
    // Use Stream.sorted().

    /**
     * Read the words from the text file, and create a list containing the words
     * of length 8 or longer, converted to lower case, and sorted reverse alphabetically.
     * (Same as above except for reversed sort order.)
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex16_longLowerCaseReverseSortedWords() throws IOException {
        List<String> result = null; // TODO

        assertEquals(Arrays.asList("substantial", "ornament", "niggarding", "increase", "creatures", "contracted",
                        "beauty's", "abundance"), result);
    }
    // Hint:
    // Use Comparator.reverseOrder().

    /**
     * Read words from the text file, and sort unique, lower-cased words by length,
     * then alphabetically within length, and place the result into an output list.
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex17_sortedLowerCaseDistinctByLengthThenAlphabetically() throws IOException {
        List<String> result = null; // TODO

        assertEquals(
                Arrays.asList("a", "as", "be", "by", "in", "or", "to", "we", "and", "art", "bud", "but", "die", "due",
                        "eat", "foe", "his", "now", "own", "the", "thy", "too", "bear", "else", "eyes", "from", "fuel",
                        "heir", "lies", "only", "pity", "rose", "self", "that", "thee", "this", "thou", "time", "with",
                        "churl", "cruel", "flame", "fresh", "gaudy", "grave", "might", "never", "riper", "sweet",
                        "thine", "waste", "where", "world", "bright", "desire", "famine", "herald", "mak'st", "making",
                        "memory", "should", "spring", "tender", "within", "buriest", "content", "decease", "fairest",
                        "feed'st", "glutton", "light's", "thereby", "world's", "beauty's", "increase", "ornament",
                        "abundance", "creatures", "contracted", "niggarding", "substantial"), result);
    }
    // Hint:
    // Use Stream.distinct().

    // Hint:
    // Use Comparator.theComparing().

    /**
     * Count the total number of words and the number of distinct, lower case
     * words in the text file, in one pass.
     */
    @Test
    @Ignore
    public void ex18_countTotalAndDistinctWords() {
        long distinctCount = 0; // TODO
        long totalCount = 0; // TODO

        assertEquals("distinct count", 81, distinctCount);
        assertEquals("total count", 107, totalCount);
    }
    // Hint 1:
    // Use Stream.peek().

    // Hint 2:
    // Use LongAdder or AtomicLong/AtomicInteger to allow peek() to have side effects.

    // ========================================================
    // ADVANCED STREAMS: REDUCTION, COLLECTORS, AND GROUPING
    // ========================================================

    /**
     * Compute the value of 21!, that is, 21 factorial. This value is larger than
     * Long.MAX_VALUE, so you must use BigInteger.
     */
    @Test
    @Ignore
    public void ex19_bigFactorial() {
        BigInteger result = BigInteger.ONE; // TODO

        assertEquals(new BigInteger("51090942171709440000"), result);
    }
    // Hint:
    // Use LongStream and reduction.

    /**
     * Get the last word in the text file.
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex20_getLastWord() throws IOException {
        List<String> result = null; // TODO

        assertEquals("thee", result);
    }
    // Hint:
    // Use Stream.reduce().

    /**
     * Categorize the words from the text file into a map, where the map's key
     * is the length of each word, and the value corresponding to a key is a
     * list of words of that length. Don't bother with uniqueness or lower-
     * casing the words.
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex21_mapLengthToWordList() throws IOException {
        Map<Integer, List<String>> result = null; // TODO

        assertEquals(10, result.get(7).size());
        assertEquals(new HashSet<>(Arrays.asList("beauty's", "increase", "ornament")), new HashSet<>(result.get(8)));
        assertEquals(new HashSet<>(Arrays.asList("abundance", "creatures")), new HashSet<>(result.get(9)));
        assertEquals(new HashSet<>(Arrays.asList("contracted", "niggarding")), new HashSet<>(result.get(10)));
        assertEquals(Arrays.asList("substantial"), result.get(11));
        assertFalse(result.containsKey(12));
    }
    // Hint:
    // Use Collectors.groupingBy().

    /**
     * Categorize the words from the text file into a map, where the map's key
     * is the length of each word, and the value corresponding to a key is a
     * count of words of that length. Don't bother with uniqueness or lower-
     * casing the words. This is the same as the previous exercise except
     * the map values are the count of words instead of a list of words.
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex22_mapLengthToWordCount() throws IOException {
        Map<Integer, Long> result = null; // TODO

        assertEquals(1L, (long) result.get(1));
        assertEquals(11L, (long) result.get(2));
        assertEquals(28L, (long) result.get(3));
        assertEquals(21L, (long) result.get(4));
        assertEquals(16L, (long) result.get(5));
        assertEquals(12L, (long) result.get(6));
        assertEquals(10L, (long) result.get(7));
        assertEquals(3L, (long) result.get(8));
        assertEquals(2L, (long) result.get(9));
        assertEquals(2L, (long) result.get(10));
        assertEquals(1L, (long) result.get(11));

        IntSummaryStatistics stats = result.keySet().stream().mapToInt(i -> i).summaryStatistics();
        assertEquals("min key", 1, stats.getMin());
        assertEquals("max key", 11, stats.getMax());
    }
    // Hint 1:
    // Use the "downstream" overload of Collectors.groupingBy().

    // Hint 2:
    // Use Collectors.counting().

    /**
     * Gather the words from the text file into a map, accumulating a count of
     * the number of occurrences of each word. Don't worry about upper case and
     * lower case. Extra challenge: implement two solutions, one that uses
     * groupingBy() and the other that uses toMap().
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex23_wordFrequencies() throws IOException {
        Map<Integer, Long> result = null; // TODO

        assertEquals(2L, (long) result.get("tender"));
        assertEquals(6L, (long) result.get("the"));
        assertEquals(1L, (long) result.get("churl"));
        assertEquals(2L, (long) result.get("thine"));
        assertEquals(1L, (long) result.get("world"));
        assertEquals(4L, (long) result.get("thy"));
        assertEquals(3L, (long) result.get("self"));
        assertFalse(result.containsKey("lambda"));
    }
    // Hint 1:
    // For Collectors.groupingBy(), consider that each word needs to
    // be categorized by itself.

    // Hint 2:
    // For Collectors.toMap(), the first occurrence of a word should be mapped to 1.

    /**
     * From the words in the text file, create nested maps, where the outer map is a
     * map from the first letter of the word to an inner map. (Use a string of length
     * one as the key.) The inner map, in turn, is a mapping from the length of the
     * word to a list of words with that length. Don't bother with any lowercasing
     * or uniquifying of the words.
     * <p>
     * For example, given the words "foo bar baz bazz foo" the string
     * representation of the result would be:
     * {b={3=[bar, baz], 4=[bazz]}, f={3=[foo, foo]}}
     *
     * @throws IOException
     */
    @Test
    @Ignore
    public void ex24_nestedMaps() throws IOException {
        Map<String, Map<Integer, List<String>>> result = null; // TODO

        assertEquals("[abundance]", result.get("a").get(9).toString());
        assertEquals("[by, be, by]", result.get("b").get(2).toString());
        assertEquals("[flame, fresh]", result.get("f").get(5).toString());
        assertEquals("[gaudy, grave]", result.get("g").get(5).toString());
        assertEquals("[should, spring]", result.get("s").get(6).toString());
        assertEquals("[substantial]", result.get("s").get(11).toString());
        assertEquals("[the, thy, thy, thy, too, the, the, thy, the, the, the]", result.get("t").get(3).toString());
        assertEquals("[where, waste, world]", result.get("w").get(5).toString());
    }
    // Hint 1:
    // The nested map structure that's the desired is the result of applying
    // a "downstream" collector that's the same operation as the first-level collector.

    // Hint 2:
    // Both collection operations are Collectors.groupingBy().

    /**
     * Given a stream of strings, accumulate (collect) them into the result string
     * by inserting the input string at both the beginning and end. For example, given
     * input strings "x" and "y" the result should be "yxxy". Note: the input stream
     * is a parallel stream, so you MUST write a proper combiner function to get the
     * correct result.
     */
    @Test
    @Ignore
    public void ex25_insertBeginningAndEnd() {
        Stream<String> input = Arrays
                .asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                        "t").parallelStream();

        String result = input.collect(null, null, null); // TODO

        assertEquals("tsrqponmlkjihgfedcbaabcdefghijklmnopqrst", result);
    }
    // Hint 1:
    // The combiner function must take its second argument and merge
    // it into the first argument, mutating the first argument.

    // Hint 2:
    // The second argument to the combiner function happens AFTER the first
    // argument in encounter order, so the second argument needs to be split
    // in half and prepended/appended to the first argument.

    // ========================================================
    // ADVANCED STREAMS: POTPOURRI
    // ========================================================

    /**
     * Denormalize this map. The input is a map whose keys are the number of legs of an animal
     * and whose values are lists of names of animals. Run through the map and generate a
     * "denormalized" list of Animal objects using the provided Animal class, where
     * each Animal instance contains the name of the animal and the number of legs.
     */
    @Test
    @Ignore
    public void ex26_denormalizeMap() {
        Map<Integer, List<String>> input = new HashMap<>();
        input.put(4, Arrays.asList("ibex", "hedgehog", "wombat"));
        input.put(6, Arrays.asList("ant", "beetle", "cricket"));
        input.put(8, Arrays.asList("octopus", "spider", "squid"));
        input.put(10, Arrays.asList("crab", "lobster", "scorpion"));
        input.put(750, Arrays.asList("millipede"));

        class Animal {
            final String name;
            final int legs;

            Animal(String s, int i) {
                name = s;
                this.legs = i;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Animal))
                    return false;
                Animal other = (Animal) obj;
                return this.name.equals(other.name) && this.legs == other.legs;
            }

            @Override
            public int hashCode() {
                return name.hashCode() ^ legs;
            }

            @Override
            public String toString() {
                return String.format("(%s,%d)", name, legs);
            }
        }

        List<Animal> result = null; // TODO

        assertEquals(13, result.size());
        assertTrue(result.contains(new Animal("ibex", 4)));
        assertTrue(result.contains(new Animal("hedgehog", 4)));
        assertTrue(result.contains(new Animal("wombat", 4)));
        assertTrue(result.contains(new Animal("ant", 6)));
        assertTrue(result.contains(new Animal("beetle", 6)));
        assertTrue(result.contains(new Animal("cricket", 6)));
        assertTrue(result.contains(new Animal("octopus", 8)));
        assertTrue(result.contains(new Animal("spider", 8)));
        assertTrue(result.contains(new Animal("squid", 8)));
        assertTrue(result.contains(new Animal("crab", 10)));
        assertTrue(result.contains(new Animal("lobster", 10)));
        assertTrue(result.contains(new Animal("scorpion", 10)));
        assertTrue(result.contains(new Animal("millipede", 750)));
    }
    // Hint 1:
    // There are several ways to approach this. You could use a stream of map keys,
    // a stream of map entries, or nested forEach() methods.

    // Hint 2:
    // If you use streams, consider using Stream.flatMap().

    /**
     * Provide lambda expressions for the peek() operations that enable you to detect
     * whether the stream is running in parallel, and using this information, provide
     * expressions for the stream1isParallel and stream2isParallel booleans to make
     * the assertions correct. You may also provide additional declarations
     * and statements anywhere before assertions. (There are an open-ended number of
     * solutions for this; the solutions file contains only one example.) Race conditions
     * will be tolerated if you're clever.
     */
    @Test
    @Ignore
    public void ex27_parallelVsSequential() {
        IntConsumer ic1 = i -> {
        }; // TODO
        IntConsumer ic2 = i -> {
        }; // TODO

        List<Integer> result1 = IntStream.range(0, 100).peek(ic1).boxed().collect(Collectors.toList());

        List<Integer> result2 = IntStream.range(0, 100).parallel().peek(ic2).boxed().collect(Collectors.toList());

        boolean stream1isParallel = false; // TODO
        boolean stream2isParallel = false; // TODO

        assertEquals(result1, result2);
        assertFalse(stream1isParallel);
        assertTrue(stream2isParallel);
    }
    // Hint 1:
    // By its very nature, you need to do something with side-effects within Stream.peek().

    // Hint 2:
    // The sequential and parallel streams have the same contents, but they will
    // probably end up processing the elements in a different order, even though
    // the output list is collected in the proper order (encounter order).

    // Hint 3:
    // Consider a thread-safe side-effect-supporting structure such as LongAdder.
    // Note that LongAdder's accumulation function must be order-dependent for it
    // to detect parallelism.

    // ========================================================
    // END OF EXERCISES -- CONGRATULATIONS!
    // TEST INFRASTRUCTURE IS BELOW
    // ========================================================

    static final String REGEXP = "[- .:,]+"; // for splitting into words

    private BufferedReader reader;

    @Before
    public void z_setUpBufferedReader() throws IOException {
        reader = Files.newBufferedReader(Paths.get("SonnetI.txt"), StandardCharsets.UTF_8);
    }

    @After
    public void z_closeBufferedReader() throws IOException {
        reader.close();
    }
}

