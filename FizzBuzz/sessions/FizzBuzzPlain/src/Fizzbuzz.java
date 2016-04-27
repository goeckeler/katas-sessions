public class Fizzbuzz {
	public static final String FIZZ = "fizz";
	public static final String BUZZ = "buzz";
	public static final String FIZZBUZZ = FIZZ + BUZZ;

	public String shout(final long number) {
		// be silent if number is invalid or round has not started yet
		if (number <= 0)
			return "";

		boolean isFizz = (number % 3 == 0);
		boolean isBuzz = (number % 5 == 0);

		if (isFizz && isBuzz)
			return FIZZBUZZ;
		if (isFizz)
			return FIZZ;
		if (isBuzz)
			return BUZZ;
		return "" + number;
	}
}
