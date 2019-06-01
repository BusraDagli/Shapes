package graphics.shapes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import graphics.shapes.SCollection;

public class PasteShapeAction implements ActionListener {

	private SCollection collectionData;
	private UndoManager undoManager;
	private Editor editor;
	
	public PasteShapeAction(UndoManager undoManger, SCollection collectionData, Editor editor) {
		// TODO Auto-generated constructor stub
		this.undoManager = undoManger;
		this.collectionData = collectionData;
		this.editor = editor;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.undoManager.execute(new PasteShapeCommand(collectionData));
		this.editor.sview.repaint();
		
	}

}
