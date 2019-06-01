package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class AddShapeCommand implements Command{

	private SCollection scollectionData;
	private Shape shapeToAdd;
	private String shapeName;
	
	public AddShapeCommand(String shapeName, SCollection sc) {
		// TODO Auto-generated constructor stub
		this.scollectionData = sc;
		this.shapeName = shapeName;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return "Shape has been added";
	}

	public void execute() {
		// TODO Auto-generated method stub
		switch (shapeName) {
		case "rectangle":
			this.shapeToAdd = new SRectangle(new Point(600,400), 100,60);
			break;
		case "circle":
			this.shapeToAdd = new SCircle(new Point(400,500), 100);
			break;
		case "triangle":
			this.shapeToAdd = new STriangle(new Point(400,500), new Point(400,550), new Point(460,500), 3);
		default:
			break;
		}
		this.shapeToAdd.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.BLUE));
		this.shapeToAdd.addAttributes(new SelectionAttributes());
		this.scollectionData.add(this.shapeToAdd);
	}

	public void undo() {
		// TODO Auto-generated method stub
		this.scollectionData.delete(shapeToAdd);
	}

	public void redo() {
		// TODO Auto-generated method stub
		execute();
	}

}
