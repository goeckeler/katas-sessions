package hexagonal;


public class Registry {
	private static Handler birthdayHandler = new BirthdayCommandHandler();
	
	public Handler handlerFor(Command command) {
		return birthdayHandler;
	}
}
