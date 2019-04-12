package processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import basic_interpret.CommandException;
import counter.Counter;

public class Processor {
	
	private boolean terminated;
	private InputStream in;
	private PrintStream out;
	private Scanner sc;
	private Object system;
	private Map<String, Command> commands;
	private BufferedReader bf;
	
	public Processor(Counter counter) { 
		this.terminated = false;
		this.commands = new TreeMap<String, Command>();
		this.in = System.in;
		this.out = System.out;
		this.setIn(in);
		this.system = counter;
		this.addCde(new CommandMenu());
		this.addCde(new CommandQuit());
		
		
	}
	
	public void addCde(Command command) {
		this.commands.put(command.getName(), command);
		
	}

	public String fetch() throws IOException {
		this.bf = new BufferedReader(new InputStreamReader(in()));
		return bf.readLine();
	}
	
	public Command decode(String fetch) throws ProcessorException {
		Command c = this.commands.get(fetch);
		if(c == null){
			throw new ProcessorException(fetch);
		}
		return c;
	}
	
	public void execute(Command decode) {
		decode.execute(this);
		
	}

	public boolean isTerminated() {
		return terminated;
	}
	
	public void terminate() {
		this.terminated = true;
	}
	
	public PrintStream out() {
		return this.out;
		
	}
	
	public InputStream in() {
		return this.in;
		
	}
	
	public void setIn(InputStream in) {
		this.in = in;
		this.sc = new Scanner(in);
	}
	
	public void setOut(PrintStream out) {
		this.out = out;
	}
	
	public Scanner scanner() {
		return this.sc;
		
	}
	
	public String toString() {
		String str = "";
		for(Iterator<Command> it = commands.values().iterator();it.hasNext();) {
			str += it.next().getName()+"\n";
		}
		return str;
	}

	public Object getSystem() {
		return system;
	}

	
	
}
