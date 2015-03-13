package hexagonal;

public class BirthdayCommandHandler implements Handler {
	private static PersonService personService = new PersonService();

	@Override
	public void handle(Command command) {
		if (!(command instanceof BirthdayCommand)) {
			throw new UnsupportedOperationException();
		}

		BirthdayCommand birthdayCommand = (BirthdayCommand) command;
		personService.happyBirthdayTo(birthdayCommand.getName());
	}
}
