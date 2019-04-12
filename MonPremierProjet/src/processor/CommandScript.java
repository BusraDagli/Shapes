package processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class CommandScript extends Command{

//	private InputStream in;
//	private PrintStream out;
//	
//	public CommandScript(InputStream in, PrintStream out) {
//		this.in = in;
//		this.out = out;
//	}
	
	public CommandScript(String name) {
		super("script");
		// TODO Auto-generated constructor stub
	}

	public void execute(Processor p) {
		try {
			
			p.execute(p.decode(p.fetch()));
			
			}
		catch (FileNotFoundException e)
		{
		e.printStackTrace();
		}
		catch (IOException e)
		{
		e.printStackTrace();
		} catch (ProcessorException e) {
			e.printStackTrace();
		}
	}
	
}
