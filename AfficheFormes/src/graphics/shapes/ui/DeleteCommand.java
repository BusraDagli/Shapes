package graphics.shapes.ui;

import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;

public class DeleteCommand implements Command{

	private SCollection scollectionData;
	private ArrayList<Shape> data_shape;
	
	public DeleteCommand(SCollection sc , Shape shape) {
		// TODO Auto-generated constructor stub
		this.scollectionData = sc ; 
		this.data_shape = new ArrayList<>();
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "return Shape has been deleted";
	}

	
	public void execute() {
		// TODO Auto-generated method stubth
		for(Iterator<Shape> it = scollectionData.iterator(); it.hasNext();) {
			Shape s  = (Shape)it.next();
	
			SelectionAttributes selection = (SelectionAttributes)s.getAttributes(SelectionAttributes.ID);
			if(selection.isSelected()) {
				this.data_shape.add(s);
				it.remove();
			}
		}
	
	}
	public void undo() {
		// TODO Auto-generated method stub
		for(Shape s : this.data_shape)
			this.scollectionData.add(s);
	}
	public void redo() {
		// TODO Auto-generated method stub
		for(Shape s : this.data_shape)
			this.scollectionData.delete(s);
	}
}
