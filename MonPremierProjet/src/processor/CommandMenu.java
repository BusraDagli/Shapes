package processor;

public class CommandMenu extends Command {

	public CommandMenu() {
		super("menu");
		
	}

	public void execute(Processor p) {
		p.out().println(p);
		
	}
}
