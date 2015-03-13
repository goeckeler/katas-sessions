package hexagonal;

public class BirthdayCommand implements Command {
	
	private final String name;

	public BirthdayCommand(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
