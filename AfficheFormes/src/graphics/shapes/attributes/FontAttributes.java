package graphics.shapes.attributes;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.ui.View;

public class FontAttributes extends Attributes{

	public static final String ID = "font";
	public Font font;
	public Color fontColor;
	
	public FontAttributes()
	{
		BufferedImage bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		this.font = ((Graphics2D) bi.getGraphics()).getFont();
		this.fontColor = Color.BLACK;
	}
	
	public FontAttributes(Color fontColor)
	{
		BufferedImage bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		this.font = ((Graphics2D) bi.getGraphics()).getFont();
		this.fontColor = fontColor;
	}
	
	public FontAttributes(Font font,Color fontColor)
	{
		BufferedImage bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		this.font = font;
		this.fontColor = fontColor;
	}
	
	public String getId()
	{
		return this.ID;
	}
	
	public Rectangle getBounds(String str)
	{
		
		BufferedImage bi = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB) ;
		FontRenderContext frc= ((Graphics2D) bi.getGraphics()).getFontRenderContext();
		Font ft = this.font;
		
		return (Rectangle) ft.getStringBounds(str, frc).getBounds();
	}
	
	public void setBounds(int dx, int dy)
	{
		this.font = new Font(this.font.getName(),this.font.getStyle(),this.font.getSize()+(dx+dy));
	}
}
