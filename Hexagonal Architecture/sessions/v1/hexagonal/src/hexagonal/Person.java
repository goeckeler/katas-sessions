package hexagonal;

import java.time.Duration;
import java.time.MonthDay;
import java.time.Year;

public class Person {
	private String name;
	private MonthDay birthday;

	public Person(String name, MonthDay birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	public boolean hasBirthday() {
		MonthDay monthDay = MonthDay.now();
		return monthDay.compareTo(birthday) == 0;
	}

	public long daysToNextBirthday() {
		Year year = Year.now();
		MonthDay monthDay = MonthDay.now();
		boolean nextYear = monthDay.isAfter(birthday);
		Duration duration = Duration.between(monthDay.atYear(year.getValue())
				.atStartOfDay(),
				birthday.atYear(year.getValue() + (nextYear ? 1 : 0))
						.atStartOfDay());
		return duration.toDays();
	}

	public MonthDay getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return name;
	}
}
