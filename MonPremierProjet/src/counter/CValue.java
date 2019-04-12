package counter;

import processor.Processor;

public class CValue extends CommandCounter{

	
	public CValue() {
		super("value");
	}

	public void execute(Processor p) {
		p.out().println(counter(p).getCount());
	}
}
