package processor;

public abstract class Command {

	private String name;
	
	public abstract void execute(Processor p);
	
	public String getName() {
		return name;
	}
	
	public Command(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
