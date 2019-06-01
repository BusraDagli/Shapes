package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View{
	private ShapeDraftman draftman;
	
	public ShapesView(Object model) {
		super(model);
		this.draftman = new ShapeDraftman();
	}

	@Override
	public Controller defaultController(Object model) {
		// TODO Auto-generated method stub
		return new ShapeController(model);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		//this.draftman.setGraphics(graphics);
		this.draftman.graphics = graphics;
		Shape model = (Shape) this.getModel();
		
		if(model==null) return;
		
		model.accept(this.draftman);	
	}
	
	
}
