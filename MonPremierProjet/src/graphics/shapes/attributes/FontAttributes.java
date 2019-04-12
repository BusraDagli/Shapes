package graphics.shapes.attributes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontAttributes extends Attributes {

	public Font font;
	public Color fontColor;
	public static final String id = "fontattributes";
	public static Graphics g;
	
	public FontAttributes(Font font, Color fontColor) {
		this.font = font;
		this.fontColor = fontColor;
	}
	
	public FontAttributes() {
		this.font = new Font("Arial", font.PLAIN, 35);
		this.fontColor = Color.BLACK;
	}
	public String getId() {
		return id;
	}
	
	public Rectangle getBounds(String gb) {
		FontRenderContext context = new FontRenderContext(new AffineTransform(),true,true);
//		return this.getBounds(gb);
		return this.font.getStringBounds(gb, context).getBounds();
		
	}
}
