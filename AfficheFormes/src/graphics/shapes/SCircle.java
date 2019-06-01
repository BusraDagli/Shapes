package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape {
	
	int radius;
	float radiusF;
	Point loc;
	
	public SCircle(Point loc,int radius)
	{
		this.loc=loc;
		this.radius=radius;
		this.radiusF= (float) radius;
	}
	
	public int getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(int radius)
	{
		this.radius=radius;
	}
	
	public Point getLoc()
	{
		return this.loc;
	}
	
	public void setLoc(Point pnt)
	{
		this.loc=pnt;
	}
	
	public void translate(int dx,int dy)
	{
		this.loc.x = this.loc.x + dx;
		this.loc.y = this.loc.y + dy;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(loc.x, loc.y, 2*this.radius,2*this.radius);
	}
	
	public void setBounds(int dx, int dy)
	{
		this.radiusF=this.radiusF+(dx+dy)/2;
		this.radius=(int) this.radiusF;
	}
	
	public void accept(ShapeVisitor sp)
	{
		sp.visitCircle(this);
	}

}
