package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;


public class SCollection extends Shape{
	
	private ArrayList<Shape> shapes;
	private Point loc;
	
	public SCollection() {
		this.shapes = new ArrayList<Shape>();
		
	}
	public Iterator<Shape> iterator() {
		return shapes.listIterator();
	}

	public void add(Shape s) {
		this.shapes.add(s);
		
	}

	@Override
	public Point getLoc() {
		return this.getBounds().getLocation();
	}

	@Override
	public void setLoc(Point p) {
		Iterator<Shape> it = iterator();
		while(it.hasNext()) {
//			this.shapes.iterator().next();
			it.next().setLoc(p);
		}
		
	}

	@Override
	public void translate(int dx, int dy) {
		Iterator<Shape> it = iterator();
		while(it.hasNext()) {
			it.next().translate(dx, dy);
		}
		
		
	}

	@Override
	public Rectangle getBounds() {
		Rectangle rect = shapes.get(0).getBounds();
		Iterator<Shape> it = shapes.iterator();
		while(it.hasNext()) {
//		for(Iterator<Shape> iterator = shapes.iterator(); iterator.hasNext(); ) {
			rect = rect.union(it.next().getBounds());
		}
		return rect;
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCollection(this);
		
	}

}
