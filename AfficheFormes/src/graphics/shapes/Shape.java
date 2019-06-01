package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.ui.ShapeDraftman;

public abstract class Shape implements Cloneable, Serializable{

	private Map attributes;
	
	
	public Shape()
	{
		this.attributes = new TreeMap();
	}
	
	public void addAttributes(Attributes attr)
	{
		this.attributes.put(attr.getId(), attr);
	}
	
	public Attributes getAttributes(String id)
	{
		return (Attributes) this.attributes.get(id);
		
	}
	
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	public abstract Rectangle getBounds();
	
	public abstract Point getLoc();
	public abstract void setLoc(Point pnt);
	public abstract void translate(int dx, int dy);
	public abstract void setBounds(int dx,int dy);
	public abstract void accept(ShapeVisitor sv);
}
