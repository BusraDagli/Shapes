package basic_interpret;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import counter.Counter;

public class Interpret {
	
	private boolean exit = false;
	private String prompt;
	private InputStream in;
	private PrintStream out;
	private Counter counter;
	private final String command[] = {"menu", "icr", "dcr", "minus", "plus", "value", "exit"};
	BufferedReader br;
	
	
	
	public Interpret() {
		this(System.in, System.out);
		this.counter = new Counter("DEFAULT");
	}

	public Interpret(InputStream in, PrintStream out) {
		this.in = in;
		this.out = out;
		this.br = new BufferedReader(new InputStreamReader(this.in));
	
	}

	public boolean exit() {
		return exit;		
	}
	
	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public void printPrompt() {

		out.println("->");
		
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String fetch() throws IOException {
		return br.readLine();
		
	}

	public void execute(String command) throws NumberFormatException, IOException, CommandException {
	
		if(command.equals("menu")) this.doMenu();
		else if(command.equals("plus")) this.doAdd();
		else if(command.equals("minus")) this.doMinus();
		else if(command.equals("value")) this.doValue();
		else if(command.equals("exit")) this.setExit(true);
		else if(command.equals("icr")) this.doIcr();
		else if(command.equals("dcr")) this.doDcr();
		else{
			throw new CommandException(command);
		};
	}
	
	private void doValue() {
		out.println(this.counter.getCount());
		
	}

	public void doMenu() {
		for(String item : command) {
			out.println(item);
		}
	}
	
	public void doAdd() throws NumberFormatException, IOException {
		out.println("Entrez le chiffre à additionner :");
		int value = Integer.parseInt(this.fetch());
		this.counter.setCount(this.counter.getCount() + value);
	}
	
	public void doMinus() throws NumberFormatException, IOException {
		out.println("Entrez le chiffre à soustraire :");
		int value = Integer.parseInt(this.fetch());
		this.counter.setCount(this.counter.getCount() - value);
	}
	
	public void doDcr() {
		this.counter.decrement();
		
	}
	
	public void doIcr() {
		this.counter.increment();
	}


	
	

}
