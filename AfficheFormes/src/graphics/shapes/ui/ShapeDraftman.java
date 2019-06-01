package graphics.shapes.ui;

import java.awt.Color;


import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapeDraftman implements ShapeVisitor {

	Graphics graphics;
	

	//ColorAttributes DEFAULTCOLORATTIBUTES = ;
	public void visitRectangle(SRectangle rect)
	{		
		//System.out.println("I am drawing a rectangle");
		Rectangle r = rect.getBounds();
		Point coord = rect.getLoc();
		Attributes color = rect.getAttributes("color");
		SelectionAttributes selectAttr = (SelectionAttributes) rect.getAttributes("select");
		ColorAttributes colorAttr = ((ColorAttributes) color);
		//Color finalcolor = (Color) color.getId();
		
		if (colorAttr.stroked)
		{
			this.graphics.setColor(colorAttr.strokedColor);
			this.graphics.drawRect(coord.x,coord.y,r.width,r.height);
		}
		if (colorAttr.filled)
		{
			this.graphics.setColor(colorAttr.filledColor);
			this.graphics.fillRect(coord.x,coord.y,r.width,r.height);
		}
		
		if(selectAttr.isSelected()) 
		{
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawRect(coord.x-6, coord.y-6, 6, 6);
			this.graphics.drawRect(coord.x+r.width, coord.y + r.height, 6, 6);
		}
	}
	
	public void visitCircle(SCircle circle)
	{
		//System.out.println("I am drawing a circle");
		Rectangle r = circle.getBounds();
		Point coord = circle.getLoc();
		Attributes color = circle.getAttributes("color");
		SelectionAttributes selectAttr = (SelectionAttributes) circle.getAttributes("select");
		ColorAttributes colorAttr = ((ColorAttributes) color);
		
		if (colorAttr.stroked)
		{
			this.graphics.setColor(colorAttr.strokedColor);
			this.graphics.drawOval(coord.x,coord.y,r.width,r.height);
		}
		if (colorAttr.filled)
		{
			this.graphics.setColor(colorAttr.filledColor);
			this.graphics.fillOval(coord.x,coord.y,r.width,r.height);
		}
		
		if(selectAttr.isSelected()) 
		{
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawRect(coord.x-6, coord.y-6, 6, 6);
			this.graphics.drawRect(coord.x+r.width, coord.y + r.height, 6, 6);
		}
	}
	
	public void visitText(SText text)
	{
		System.out.println("I am drawing a text");
		Rectangle r = text.getBounds();
		Point coord = text.getLoc();
		Attributes color = text.getAttributes("color");
		SelectionAttributes selectAttr = (SelectionAttributes) text.getAttributes("select");
		ColorAttributes colorAttr = ((ColorAttributes) color);
		
		
		if (colorAttr.stroked)
		{
			this.graphics.setColor(colorAttr.strokedColor);
			this.graphics.drawRect(coord.x,coord.y,r.width,r.height);
		}
		if (colorAttr.filled)
		{
			this.graphics.setColor(colorAttr.filledColor);
			this.graphics.fillRect(coord.x,coord.y,r.width,r.height);
		}
		
		this.graphics.setColor(((FontAttributes) text.getAttributes("font")).fontColor);
		System.out.println(text.getBounds());
		this.graphics.setFont(((FontAttributes) text.getAttributes("font")).font);
		this.graphics.drawString(text.getText(),coord.x,coord.y+r.height);
		
		
		
		if(selectAttr.isSelected()) 
		{
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawRect(coord.x-6, coord.y-6, 6, 6);
			this.graphics.drawRect(coord.x+r.width, coord.y + r.height, 6, 6);
		}
	}
	
	public void visitCollection(SCollection clt)
	{
		//System.out.println("I am drawing a collection");
		for(Iterator it = clt.iterator(); it.hasNext();)
			((Shape)it.next()).accept(this);
		if(((SelectionAttributes)clt.getAttributes("select")).isSelected()) {
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawRect(clt.getLoc().x-6, clt.getLoc().y-6, 6, 6);
			this.graphics.drawRect(clt.getLoc().x+clt.getBounds().width, clt.getLoc().y + clt.getBounds().height, 6, 6);
		}
		
	}
	
	public void visitTriangle(STriangle tri) {

		ColorAttributes color = (ColorAttributes) tri.getAttributes("color");
		
		if(color.filled == true) {
			this.graphics.setColor(color.filledColor);
			this.graphics.fillPolygon(new int[] {tri.getP1().x, tri.getP2().x, tri.getP3().x}, new int[] {tri.getP1().y, tri.getP2().y, tri.getP3().y}, 3);
		}
		if(color.stroked == true) {
			this.graphics.setColor(color.strokedColor);
			this.graphics.drawPolygon(new int[] {tri.getP1().x, tri.getP2().x, tri.getP3().x}, new int[] {tri.getP1().y, tri.getP2().y, tri.getP3().y}, 3);
		}
		if(((SelectionAttributes)tri.getAttributes("select")).isSelected()) {
			this.graphics.setColor(Color.BLACK);
			this.graphics.drawRect(tri.getBounds().getLocation().x-6, tri.getBounds().getLocation().y-6, 6, 6);
			this.graphics.drawRect(tri.getBounds().getLocation().x + tri.getBounds().width, tri.getBounds().getLocation().y + tri.getBounds().height, 6, 6);
		}

	}

	public void setGraphics(Graphics graphics) 
	{
		this.graphics = graphics;
		
	}
}
