package hexagonal;

public class PersonBirthdayNotifier implements Notifier<PersonEvent> {
	@Override
	public void notify(PersonEvent event) {
      System.out.println("NOTIFICATION: " + event.getPerson() + ", you've got mail!");
	}
}
