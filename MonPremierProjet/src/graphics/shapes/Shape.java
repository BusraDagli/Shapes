package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;

import graphics.shapes.attributes.Attributes;

public abstract class Shape {

	private Map<String, Attributes> atb = new TreeMap<String, Attributes>();
	
	public void addAttributes(Attributes a) {
		atb.put(a.getId(), a);
	}
	
	public Attributes getAttributes(String at) {
		return this.atb.get(at);
		
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point p);
	
	public abstract void translate(int i, int j);
	
	public abstract Rectangle getBounds();
	
	public abstract void accept(ShapeVisitor sv);
}
