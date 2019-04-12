package processor;

public class CommandQuit extends Command{

	
	public CommandQuit() {
		super("quit");

	}

	public void execute(Processor p) {
		p.out().println("by by ... ;)");
		p.terminate();
	}
}
