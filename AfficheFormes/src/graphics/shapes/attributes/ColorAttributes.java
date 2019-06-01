package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes{

	public static final String ID = "color";
	
	public boolean filled;
	public boolean stroked;
	public Color filledColor;
	public Color strokedColor;
	
	public ColorAttributes()
	{
		this(true, true, Color.BLUE, Color.RED);
	}
	
	public ColorAttributes(boolean fld, boolean stk,Color cf, Color cs)
	{
		this.filled = fld;
		this.stroked = stk;
		this.filledColor = cf;
		this.strokedColor = cs;		
	}

	public String getId()
	{
		System.out.println(this.filledColor);
		return ID;
	}
}
