package graphics.shapes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import graphics.shapes.SCollection;
import graphics.shapes.Shape;

public class DeleteAction implements ActionListener {

	private SCollection collectionData;
	private Shape shapeToDelete;
	private UndoManager undoManager;
	private Editor editor;
	
	public DeleteAction(UndoManager undoManager, SCollection collecionData, Shape shapeToDelete, Editor editor) {
		// TODO Auto-generated constructor stub
		this.undoManager = undoManager;
		this.collectionData = collecionData;
		this.shapeToDelete = shapeToDelete;
		this.editor = editor;
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		undoManager.execute( new DeleteCommand(collectionData, shapeToDelete));
		this.editor.sview.repaint();
		
	}
}
