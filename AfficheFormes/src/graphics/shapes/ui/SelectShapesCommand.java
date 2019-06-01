package graphics.shapes.ui;

import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.SelectionAttributes;

public class SelectShapesCommand implements Command {
	private SCollection scollectionData;
	private ArrayList<Shape> data_shape;
	public SelectShapesCommand(SCollection sc) {
		// TODO Auto-generated constructor stub
		this.scollectionData = sc ; 
		this.data_shape = new ArrayList<>();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "shapes Selected";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
//		for(Shape shape : this.scollectionData.getArray())
//		{
//			shape.addAttributes(new SelectionAttributes());
//			SelectionAttributes selection = (SelectionAttributes)shape.getAttributes(SelectionAttributes.id);
//			selection.select();
//		}
		for(Iterator<Shape> it = scollectionData.iterator(); it.hasNext();) {
			Shape s  = (Shape)it.next();
	
			SelectionAttributes selection = (SelectionAttributes)s.getAttributes(SelectionAttributes.ID);
			if(!selection.isSelected()) {
				selection.select();
			}
		}
	

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for(Shape shape : this.scollectionData.getArray())
		{
			SelectionAttributes selection = (SelectionAttributes)shape.getAttributes(SelectionAttributes.ID);
			selection.unselect();
		}

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		this.execute();
	}
}
