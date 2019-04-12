package counter;

import processor.Processor;

public class CInr extends CommandCounter {

	
	public CInr() {
		super("increment");
	}

	public void execute(Processor p) {
		this.counter(p).increment();
	}
}
