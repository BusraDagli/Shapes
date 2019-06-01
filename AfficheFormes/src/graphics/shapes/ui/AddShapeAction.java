package graphics.shapes.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import graphics.shapes.SCollection;

public class AddShapeAction implements ActionListener {

	private SCollection collectionData;
	private UndoManager undoManager;
	private String shapeToAdd;
	private Editor editor;
	
	public AddShapeAction(String shapeToAdd , UndoManager undoManger, SCollection collectionData, Editor editor) {
		// TODO Auto-generated constructor stub
		this.shapeToAdd = shapeToAdd;
		this.undoManager = undoManger;
		this.collectionData = collectionData;
		this.editor = editor;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this.shapeToAdd);
		this.undoManager.execute(new AddShapeCommand(shapeToAdd, collectionData));
		
		this.editor.sview.repaint();
		
	}

}
