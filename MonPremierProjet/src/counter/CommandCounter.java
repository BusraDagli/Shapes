package counter;



import processor.Command;
import processor.Processor;

public abstract class CommandCounter extends Command {

	public CommandCounter(String name) {
		super(name);
	}

	public Counter counter(Processor p) {
		return (Counter) p.getSystem(); 
	}
}
