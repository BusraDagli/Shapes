package processor;

import java.io.IOException;
import java.util.InputMismatchException;

import basic_interpret.CommandException;
import counter.CDcr;
import counter.CInr;
import counter.CMinus;
import counter.CPlus;
import counter.CValue;
import counter.Counter;

public class Test2
{
	public static void main(String args[]) throws IOException, ProcessorException, CommandException
	{
		Processor p = new Processor(new Counter("DEFAULT", 0));
		p.addCde(new CDcr());
		p.addCde(new CInr());
		p.addCde(new CPlus());
		p.addCde(new CMinus());
		p.addCde(new CValue());
		
		while (!p.isTerminated())
		{
			System.out.println("-> ");
			try
			{
				p.execute(p.decode(p.fetch()));
			}
			catch (InputMismatchException e)
			{
				e.printStackTrace();
			}
		}
	}
}

