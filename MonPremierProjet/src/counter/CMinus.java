package counter;

import java.util.Scanner;

import processor.Processor;

public class CMinus extends CommandCounter{

	
	public CMinus() {
		super("minus");
	}

	public void execute(Processor p) {
		
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		this.counter(p).setCount(this.counter(p).getCount() - i);
	}
}
