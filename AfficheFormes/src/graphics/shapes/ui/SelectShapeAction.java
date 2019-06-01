package graphics.shapes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import graphics.shapes.SCollection;

public class SelectShapeAction implements ActionListener {

	private SCollection collectionData;
	private UndoManager undoManager;
	private Editor editor;
	public SelectShapeAction(UndoManager undoManager, SCollection model, Editor editor) {
		// TODO Auto-generated constructor stub
		this.undoManager  = undoManager;
		this.collectionData = model;
		this.editor = editor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.undoManager.execute((Command) new SelectShapesCommand(collectionData));
		this.editor.sview.repaint();
	}
}
