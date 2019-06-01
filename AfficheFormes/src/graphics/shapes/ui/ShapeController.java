package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapeController extends Controller {
	
	private int coordX;
	private int coordY;
	private boolean shiftdown;
	public static Shape shape;
	private String action;

	public Shape shapeToBeCopied;
	
	
	
	public ShapeController(Object newModel) {
		super(newModel);
		this.action="";
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		/*
		System.out.println(e);
		Shape s = this.getTarget(e.getPoint());
		System.out.println(s);
		coordX = e.getX();
		coordY = e.getY();*/
		coordX = e.getX();
		coordY = e.getY();
		Point pnt=e.getPoint();
		this.shape = this.getTarget(pnt);
		Shape lt = this.getLTBox(pnt);
		Shape rb = this.getRBBox(pnt);
		if (lt!=null)
		{
			this.shape=lt;
			this.action="lt";
		}
		else if (rb!=null)
		{
			this.shape=rb;
			this.action="rb";
		}
		else
		{
			this.action="";
			if(shiftDown()==false) 
			{
				 this.unselectAll();
			}
			if(this.shape!=null) {
				 SelectionAttributes at = (SelectionAttributes) this.shape.getAttributes("select");
				 at.toggleSelection();
			 }
			
			
		}
		
		 this.getView().repaint();
		//System.out.println(s);
	}

	public void mouseReleased(MouseEvent e)
	{
		Point pnt=new Point(-10,-10);
		this.shape=this.getTarget(pnt);
		this.action="";
	}

	public void mouseClicked(MouseEvent e)
	{
		coordX = e.getX();
		coordY = e.getY();
		Point p = new Point(coordX,coordY);
		Shape s = this.getTarget(e.getPoint());
		this.shapeToBeCopied = this.getTarget(p);
		if (s == null)
		{
			this.unselectAll();
			((SelectionAttributes) s.getAttributes("select")).toggleSelection();
		}
		
		System.out.println("j'agrippe :");	
		System.out.println(s);	
		this.action="";
		/*if (!shiftDown())
		{
			for(Iterator it = ((SCollection) model).iterator(); it.hasNext();)
			{
				((SelectionAttributes) ((Shape) it.next()).getAttributes("select")).selected = false;
			}
		}
		else
		{
			((SelectionAttributes) target.getAttributes("select")).selected = true;
		}
		
		if (target != null)
		{
			for(Iterator it = ((SCollection) model).iterator(); it.hasNext();)
			{
				((SelectionAttributes) ((Shape) it.next()).getAttributes("select")).toggleSelection();
			}
		}
		*/
		getView().repaint();
	}
	
	public void mouseEntered(MouseEvent e)
	{
		//System.out.println(e.getPoint());
		//Quant la souris entre dans la fenêtre d'affichage
	}

	public void mouseExited(MouseEvent e)
	{
		//Quant la souris sort de la fenêtre
	}
	
	public void mouseMoved(MouseEvent evt)
	{
	}

	private void translateSelected(int dx,int dy)
	{
		SCollection model = (SCollection) this.getModel();
		
		for(Iterator i = model.iterator(); i.hasNext();)
		{
			Shape shape = (Shape) i.next();
			if (((SelectionAttributes) (shape).getAttributes("select")).isSelected())
			{
				shape.translate(dx-coordX,dy-coordY);
				System.out.println("Je déplace :");	
				System.out.println(shape);
				
			}
		}
	}

	public void mouseDragged(MouseEvent evt)
	{
		Point pnt =evt.getPoint();
		
		if (this.action=="lt" && ((SelectionAttributes) this.shape.getAttributes("select")).isSelected())
		{
			System.out.println("Je change la taille");
			this.shape.setBounds(coordX-pnt.x,coordY-pnt.y);
			this.shape.translate(pnt.x-coordX,pnt.y-coordY);
		}
		 
		else if (this.action=="rb" && ((SelectionAttributes) this.shape.getAttributes("select")).isSelected())
		{
			System.out.println("Je change la taille");
			this.shape.setBounds(pnt.x-coordX,pnt.y-coordY);
		}
		else
		{
			if (this.shape!=null)
			{
				this.translateSelected(pnt.x,pnt.y);
			}
		}

		
		//view.ShapesView(model);
		this.getView().refresh();
		coordX = evt.getX();
		coordY = evt.getY();
	
	}
	
	public boolean shiftDown() 

	{
		return shiftdown;
	}
	
	
	public void keyTyped(KeyEvent evt)
	{
		System.out.println(evt.getKeyChar());
	}
	
	public void keyPressed(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shiftdown = true;
		}
		
	}

	public void keyReleased(KeyEvent evt)
	{
		if(evt.getKeyCode() == KeyEvent.VK_SHIFT) {
			this.shiftdown = false;
		}
	}
	
	
	private Shape getTarget(Point pnt)
	{
		SCollection model = (SCollection) this.getModel();
		for(Iterator it = model.iterator(); it.hasNext();)
		{
			Shape shape = (Shape) it.next();
			if(shape.getBounds().contains(pnt))
			{
				getView().repaint();
				return  shape;
			}
		}
		return null;
	}
	
	private Shape getLTBox(Point pnt)
	{
		SCollection model = (SCollection) this.getModel();
		Rectangle box;
		Rectangle bounds;
		Point loc;
		for(Iterator it = model.iterator(); it.hasNext();)
		{
			Shape shape = (Shape) it.next();
			bounds = shape.getBounds();
			loc= shape.getLoc();
			box = new Rectangle(loc.x-6,loc.y-6,6,6);
			System.out.println("Bonjour");
			if(box.contains(pnt))
			{
				System.out.println(shape);
				System.out.println("trouvé");
				return shape;
			}
		}
		return null;
	}
	
	private Shape getRBBox(Point pnt)
	{
		SCollection model = (SCollection) this.getModel();
		Rectangle box;
		Rectangle bounds;
		Point loc;
		for(Iterator it = model.iterator(); it.hasNext();)
		{
			Shape shape = (Shape) it.next();
			bounds = shape.getBounds();
			loc= shape.getLoc();
			box = new Rectangle(loc.x+bounds.width,loc.y+bounds.height,6,6);
			System.out.println("Bijour");
			if(box.contains(pnt))
			{
				System.out.println("J'ai trouvé");
				return shape;
			}
		}
		return null;
	}
	
	private void unselectAll()
	{
		Shape s=null;
		SCollection model = (SCollection) this.getModel();
		for(Iterator i = model.iterator(); i.hasNext();)
		{
			s = (Shape) i.next();
			((SelectionAttributes) s.getAttributes("select")).unselect();
		}
	}

	
}
