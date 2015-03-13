package hexagonal;

import java.time.Month;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;

public class PersonRepository {
    private static final Map<String, Person> persons = new HashMap<>();
    
    static {
      Person thorsten = new Person("Thorsten", MonthDay.of(Month.NOVEMBER, 4));
      Person klaus = new Person("Klaus", MonthDay.now());
      Person oliver = new Person("Oliver", MonthDay.of(Month.JANUARY, 2));
      
      persons.put(thorsten.toString().toLowerCase(), thorsten);
      persons.put(klaus.toString().toLowerCase(), klaus);
      persons.put(oliver.toString().toLowerCase(), oliver);
    }
    
	public Person findByName(String name) {
		return persons.get(name.toLowerCase());
	}
}
