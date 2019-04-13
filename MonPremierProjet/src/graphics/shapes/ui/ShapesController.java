package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.annotation.Target;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesController extends Controller {
	
	private Object model;
	private View view;
	private Shape s;
	private Point cursor;
	private boolean shiftdown;
	
	public ShapesController(Object newModel) {
		super(newModel);
		// TODO Auto-generated constructor stub
		this.model = getModel();
		this.view = getView();
		
	}


	public Shape getTarget() {
		SCollection col = (SCollection)this.getModel();
		Iterator it = col.iterator();
		while(it.hasNext()) {
			Shape sh = (Shape) it.next();
			if(sh.getBounds().contains(cursor)) {
				return sh;
			}
		}
		
		return null;
	}
	
	public void translateSelected(int dx, int dy) {
		
		this.translate(dx, dy);
		
		
		
	}
	
	public void unselectAll() {
		SCollection col = (SCollection)this.getModel();
		Iterator it = col.iterator();
	
		while(it.hasNext()) {
			Shape sh = (Shape) it.next();
			SelectionAttributes at = (SelectionAttributes) sh.getAttributes("selectionattributes");
			at.unselect();
		}
	}
	
	public void toggleSelection() {
		if(s != null) {
			this.unselectAll();
		}
	}
	
	public void translate(int dx, int dy) {
		this.s.translate(dx, dy);
	}
	
	public boolean shiftDown() {
		return shiftdown;
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shiftdown = true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shiftdown = false;
		}
	}
	
	public boolean isSelected() {
		if(this.s!=null) {
			SelectionAttributes at = (SelectionAttributes) this.s.getAttributes("selectionattributes");
			return at.isSelected();
		}
		return false;
	}
	
	public void mousePressed(MouseEvent e)
	{
		
//		
//		this.cursor = e.getPoint();
//		this.s = this.getTarget();
//		if(this.s!=null) {
//			if(shiftDown()) {
////				SelectionAttributes at = (SelectionAttributes) s.getAttributes("selectionattributes");
////				at.select();
//				
//			}
//			else{
//				SCollection col = (SCollection)this.getModel();
//			
//				Iterator it = col.iterator();
//				while(it.hasNext()) {
//					Shape sh = (Shape) it.next();
//					SelectionAttributes at = (SelectionAttributes) sh.getAttributes("selectionattributes");
//				
//					if(sh.equals(s)) {
//						at.select();
//					}
//					else {
//						at.unselect();
//					
//					}
//				}
//				
//			}
//			
//		}
//		else {
//			this.unselectAll();
//		}
//		this.getView().repaint();
		
		 this.cursor = e.getPoint();
		 this.s = this.getTarget();
		 if(shiftDown()==false) {
			 this.unselectAll();
		 }
		 if(this.s!=null) {
			 SelectionAttributes at = (SelectionAttributes) s.getAttributes("selectionattributes");
			 at.toggleSelection();
		 }
		 this.getView().repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}

	public void mouseClicked(MouseEvent e)
	{
		this.cursor = e.getPoint();
		this.s = this.getTarget();
		
		this.getView().repaint();
	}
	
	public void mouseDragged(MouseEvent evt) 
	{	
		
		if(this.s!=null && isSelected()) {
		this.translateSelected(evt.getX() - this.cursor.x , evt.getY() - this.cursor.y);
		this.cursor = evt.getPoint();
		
		}
		this.getView().repaint();
	}
	
	
}
