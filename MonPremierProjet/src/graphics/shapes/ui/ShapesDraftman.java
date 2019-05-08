package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Iterator;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.ShapeVisitor;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

public class ShapesDraftman implements ShapeVisitor {

	
	public ColorAttributes DEFAULTCOLORATTRIBUTES;
//	private Graphics g;
	public static Graphics g;
	private ShapesView shapesView;
	private ShapesController sc;

	
	public ShapesDraftman() {
		
	}
	
	public ShapesDraftman(Graphics g2) {
		this.g = (Graphics2D) g2;
		//this.g = g2;
	}

	public ShapesDraftman(ShapesView shapesView) {
		this.shapesView = shapesView;
	}

	@Override
	public void visitRectangle(SRectangle r) {
		ColorAttributes color = (ColorAttributes) r.getAttributes(ColorAttributes.id);
		
		if(color.filled == true) {
			g.setColor(color.filledColor);
			g.fillRect(r.getRect().x, r.getRect().y, r.getRect().width, r.getRect().height);	
		}
		if(color.stroked == true) {
			g.setColor(color.strokedColor);
			g.drawRect(r.getRect().x, r.getRect().y, r.getRect().width, r.getRect().height);		
		}
		if(((SelectionAttributes)r.getAttributes("selectionattributes")).isSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(r.getLoc().x-3, r.getLoc().y-3, 3, 3);
			g.drawRect(r.getLoc().x+r.getRect().width, r.getLoc().y + r.getRect().height, 3, 3);
		}
		
	}

	@Override
	public void visitCircle(SCircle c) {
		ColorAttributes color = (ColorAttributes) c.getAttributes(ColorAttributes.id);
		
		if(color.filled == true) {
			g.setColor(color.filledColor);
			g.fillOval(c.getLoc().x, c.getLoc().y, 2*c.getRadius(), 2*c.getRadius());
		}
		if(color.stroked == true) {
			g.setColor(color.strokedColor);
			g.drawOval(c.getLoc().x, c.getLoc().y, 2*c.getRadius(), 2*c.getRadius());
		}

		if(((SelectionAttributes)c.getAttributes("selectionattributes")).isSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(c.getLoc().x - 3, c.getLoc().y - 3, 3, 3);
			g.drawRect(c.getLoc().x+c.getBounds().width, c.getLoc().y + c.getBounds().height, 3, 3);
		}
	}

	@Override
	public void visitText(SText t) {
		FontAttributes fa = (FontAttributes) t.getAttributes(FontAttributes.id);
		ColorAttributes color = (ColorAttributes) t.getAttributes(ColorAttributes.id);
		
		this.g.setFont(fa.font);
//		this.g.setPaintMode();
		Rectangle r = t.getBounds();
		
		
		if(color.filled == true) {
			g.setColor(color.filledColor);
			g.fillRect(r.x, r.y, r.width, r.height);
			g.drawString(t.getText(), t.getLoc().x, t.getLoc().y + r.height);
		}
		if(color.stroked == true) {
			g.setColor(color.strokedColor);
			g.drawString(t.getText(), t.getLoc().x, t.getLoc().y+ r.height);
		}
		if(((SelectionAttributes)t.getAttributes("selectionattributes")).isSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(t.getLoc().x -3, t.getLoc().y-3, 3, 3);
			g.drawRect(t.getLoc().x+t.getBounds().width, t.getLoc().y + t.getBounds().height, 3, 3);
		}
		
		
	}

	@Override
	public void visitCollection(SCollection co) {
		for(Iterator<Shape> it = co.iterator(); it.hasNext();) {
			Shape shape = (Shape) it.next();
			shape.accept(this);
		}
		if(((SelectionAttributes)co.getAttributes("selectionattributes")).isSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(co.getLoc().x-3, co.getLoc().y-3, 3, 3);
			g.drawRect(co.getLoc().x+co.getBounds().width, co.getLoc().y + co.getBounds().height, 3, 3);
		}
		
	}
	
	public void visitTriangle(STriangle tri) {

		ColorAttributes color = (ColorAttributes) tri.getAttributes(ColorAttributes.id);
		
		if(color.filled == true) {
			g.setColor(color.filledColor);
			g.fillPolygon(new int[] {tri.getP1().x, tri.getP2().x, tri.getP3().x}, new int[] {tri.getP1().y, tri.getP2().y, tri.getP3().y}, 3);
		}
		if(color.stroked == true) {
			g.setColor(color.strokedColor);
			g.drawPolygon(new int[] {tri.getP1().x, tri.getP2().x, tri.getP3().x}, new int[] {tri.getP1().y, tri.getP2().y, tri.getP3().y}, 3);
		}
		if(((SelectionAttributes)tri.getAttributes("selectionattributes")).isSelected()) {
			g.setColor(Color.BLACK);
			g.drawRect(tri.getLoc().x-3, tri.getLoc().y-3, 3, 3);
			g.drawRect(tri.getLoc().x + tri.getBounds().width, tri.getLoc().y - tri.getBounds().height, 3, 3);
		}

	}
}
