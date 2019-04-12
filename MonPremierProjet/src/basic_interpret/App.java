package basic_interpret;

import java.io.IOException;

import counter.Counter;


public class App {

	public static void main(String[] args) throws CommandException, IOException {
		
		Interpret interpret = new Interpret();
		
		while(!interpret.exit()) {
			
			try {
				interpret.printPrompt();
				interpret.execute(interpret.fetch());
				}
				
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		

	}

}
