package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View{
	
	
	private ShapesController sc;
	private ShapesDraftman sd;

	public ShapesView(Shape model) {
		super(model);
		this.sc = new ShapesController(model);
		this.sc.setView(this);
		this.sd = new ShapesDraftman(this);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		this.sd = new ShapesDraftman();
		this.sd.g = g;
		Shape model = (Shape) this.getModel();
		model.accept(this.sd);
		
	}
	
	public Controller defaultController(Object model) {
		return new ShapesController((Shape) model);
		
	}
}
