package hexagonal;

public class PersonEvent implements Event {
	private Person person;
	
	public PersonEvent(final Person person) {
	  this.person = person;	
	}

	public Person getPerson() {
		return person;
	}
}
