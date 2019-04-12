package graphics.shapes;


import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape {
	
	private String text;
	private Point loc;
	public Graphics g;
	
	public SText(Point loc, String text) {
		this.loc = loc;
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Point getLoc() {
		return this.loc;
	}
	
	public void setLoc(Point loc) {
		this.loc.setLocation(loc);
	}
	
	
	@Override
	public void translate(int dx, int dy) {
		this.loc.setLocation(this.loc.getLocation().x + dx, this.loc.getLocation().y + dy);
		
	}

	@Override
	public Rectangle getBounds() {
		
//		BufferedImage img = new BufferedImage(100, 100,BufferedImage.TYPE_3BYTE_BGR);
//		this.g = img.getGraphics();
//		
//		FontMetrics metrics = g.getFontMetrics(((FontAttributes)this.getAttributes(FontAttributes.id)).font);
//		
//		int width = metrics.stringWidth(text);
//		int height = metrics.getHeight();
//		
//		return new Rectangle((int)this.loc.getX(),(int)this.loc.getY()- height, width, height);
		
		Rectangle bounds = ((FontAttributes)this.getAttributes(FontAttributes.id)).getBounds(this.getText());
		bounds.setLocation(loc);
		
		return bounds;
	
	}

	@Override
	public void accept(ShapeVisitor sv) {
		sv.visitText(this);
		
	}



}
