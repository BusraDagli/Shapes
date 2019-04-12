package graphics.shapes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {

	public int radius;
	private Point loc;
	
	public SCircle(Point point, int radius) {
		this.radius = radius;
		loc = new Point();
		this.setLoc(point);
		
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public Point getLoc() {
		return this.loc.getLocation();
	}

	@Override
	public void setLoc(Point loc) {
		this.loc.setLocation(loc);
		
	}

	@Override
	public void translate(int dx, int dy) {
		this.loc.setLocation(this.loc.getLocation().x + dx, this.loc.getLocation().y + dy);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.loc, new Dimension((int)radius*2,(int)radius*2)).getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitCircle(this);
		
	}

	

}
