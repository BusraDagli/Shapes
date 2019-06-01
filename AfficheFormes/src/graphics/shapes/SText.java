package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {

	public String text = new String();
	public Point loc= new Point();

	
	public SText(Point point, String text) {
		this.setText(text);
		this.setLoc(point);
	}

	public String getText()
	{
		return this.text;
	}
	
	public void setText(String newtxt)
	{
		this.text=newtxt;
	}
	
	public Point getLoc()
	{
		return this.loc;
	}
	
	public void setLoc(Point newloc)
	{
		this.loc=newloc;
	}
	
	public void translate(int dx,int dy)
	{
		this.loc.x = this.loc.x + dx;
		this.loc.y = this.loc.y + dy;
	}
	
	public Rectangle getBounds()
	{
		FontAttributes attr = (FontAttributes) this.getAttributes("font");
		Rectangle rect = attr.getBounds(this.text);
		//return new Rectangle(50,50,50,50);
		return new Rectangle(loc.x, loc.y, rect.width, rect.height);
	}
	
	public void setBounds(int dx, int dy)
	{
		FontAttributes attr = (FontAttributes) this.getAttributes("font");
		attr.setBounds(dx,dy);
	}
	
	public void accept(ShapeVisitor sp)
	{
		sp.visitText(this);
	}
}
