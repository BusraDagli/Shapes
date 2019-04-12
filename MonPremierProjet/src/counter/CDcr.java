package counter;

import processor.Processor;

public class CDcr extends CommandCounter {

	
	public CDcr() {
		super("decrement");
	}

	public void execute(Processor p) {
		this.counter(p).decrement();
	}
}
