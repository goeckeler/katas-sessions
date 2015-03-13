package hexagonal;

import java.util.LinkedList;
import java.util.List;

public class PersonService {
    private static final PersonRepository personRepository = new PersonRepository();
    
    private List<Notifier<PersonEvent>> notifiers = new LinkedList<>();
    
    public PersonService() {
      notifiers.add(new PersonBirthdayNotifier());	
    }
    
	public void happyBirthdayTo(String name) {
		Person person = personRepository.findByName(name);
		
		if (person == null) {
			System.out.println("Don't know about \"" + name + "\".");
			return;
		}
		
		if (person.hasBirthday()) {
			System.out.println("Happy Birthday, " + person + "!");
			notifiers.forEach(n -> n.notify(new PersonEvent(person)));
		} else {
			System.out.println(String.format("%1s, just wait for %2d days.", person, person.daysToNextBirthday()));
		}
	}
}
