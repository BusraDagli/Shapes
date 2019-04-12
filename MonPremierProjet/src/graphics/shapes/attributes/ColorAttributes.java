package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static final String id = "color";
	public boolean filled;
	public boolean stroked;
	public Color filledColor;
	public Color strokedColor;

	public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor) {
		this.filledColor = filledColor;
		this.strokedColor = strokedColor;
		this.stroked = stroked;
		this.filled = filled;
	}

	public String getId() {
		return id;
	}
	
	
}
