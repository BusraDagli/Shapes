package graphics.shapes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class SRectangle extends Shape {

	private Rectangle rect;
	
	public SRectangle(Point point, int width , int height) {
		this.rect = new Rectangle(point, new Dimension((int)width,(int)height));
		
	}
	
	public SRectangle() {
		this.rect = new Rectangle(new Point(), new Dimension(30,10));
	}
	public Rectangle getRect() {
		return this.rect;
	}
	
	public Point getLoc() {
		return rect.getLocation();
	}

	public void setLoc(Point p) {
		this.rect.setLocation(p);
		
	}

	public void translate(int dx, int dy) {
		this.rect.setLocation(this.rect.getLocation().x + dx, this.rect.getLocation().y + dy);
	}

	@Override
	public Rectangle getBounds() {
		return this.rect.getBounds();
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitRectangle(this);
		
	}

	
}
