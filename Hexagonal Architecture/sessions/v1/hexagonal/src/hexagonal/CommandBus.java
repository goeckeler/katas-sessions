package hexagonal;

public class CommandBus {
	private static Registry registry = new Registry();
	
	public void send(Command command) {
		if (command == null) {
			return;
		}

		registry.handlerFor(command).handle(command);
	}
}
