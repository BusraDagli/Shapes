package graphics.shapes.ui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class PasteShapeCommand implements Command  {

	private SCollection scollectionData;
	private ArrayList<Shape> data_shape;
	private Shape sp;
	private SRectangle sr ;
	
	public PasteShapeCommand(SCollection sc ) {
		// TODO Auto-generated constructor stub
		this.scollectionData = sc ; 
		this.data_shape = new ArrayList<>();
		this.sr = new SRectangle();
		
	}

	public String getName() {
		return "shape Pasete";
	}

	
	public void execute() {
		for(Iterator<Shape> it = scollectionData.iterator(); it.hasNext();) {
			Shape s  = (Shape)it.next();
			
			SelectionAttributes selection = (SelectionAttributes)s.getAttributes(SelectionAttributes.ID);
			ColorAttributes color = (ColorAttributes)s.getAttributes(ColorAttributes.ID);
//			this.scollectionData.add(s);
			if(selection.isSelected()) {
				
				try {
					this.sp = (Shape) s.clone();
					this.sp.setLoc(new Point(400,500));
					this.sp.addAttributes(new SelectionAttributes());
					s.addAttributes(new SelectionAttributes());
//					this.sp.addAttributes(new ColorAttributes(true, false, Color.RED, Color.gray));
					
					//sp.addAttributes(color);
					this.data_shape.add(this.sp);
					//System.out.println(s.hashCode());
					System.out.println(sp.hashCode());
				
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("cant");
				}
				
			}
		}
//		this.sp.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
		
		
		this.scollectionData.add(sp);
//		this.scollectionData.add(sr);
		for(Shape s : scollectionData.getArray())
		{
			System.out.println(s.getClass().getSimpleName());
		}
			
	}

	public void undo() {
		
		this.scollectionData.delete(sp);
	}

	public void redo() {
		
		this.scollectionData.add(sp);
	}


}
