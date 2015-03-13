package hexagonal;

public class Controller {
	private CommandBus commandBus = new CommandBus();
	
   public void greet(final String name) {
	 commandBus.send(new BirthdayCommand(name));
   }
   
   public static void main(String[] args) {
	   System.out.println("Happy Birthday!");
	   
	   Controller controller = new Controller();
	   controller.greet("klaus");
	   controller.greet("marcus");
	   controller.greet("oliver");
	   controller.greet("thorsten");
   }
}
