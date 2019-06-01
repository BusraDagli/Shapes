package graphics.shapes.ui;

public interface Command {
	String getName();

	void execute();

	void undo();

	void redo();
}
