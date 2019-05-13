package graphics.shapes;


import java.awt.Point;
import java.awt.Rectangle;

public class STriangle extends Shape {

	private Point loc;
	private Point p1;
	private Point p2;
	private Point p3;
	private int minx,miny,maxx,maxy;
	
	public STriangle(Point p1, Point p2, Point p3, int nbofpts) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		loc = new Point(p1);
		
	}

	@Override
	public Point getLoc() {
		// TODO Auto-generated method stub
		return this.loc.getLocation();
	}

	@Override
	public void setLoc(Point p) {
		// TODO Auto-generated method stub
		this.loc.setLocation(p);
		
	}

	@Override
	public void translate(int dx, int dy) {
		this.p1.translate(dx, dy);
		this.p2.translate(dx, dy);
		this.p3.translate(dx,dy);
		this.loc.translate(dx, dy);
		
	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
//		System.out.println(minx);
//		System.out.println(maxx);
//
		minx(p1,p2,p3);
		maxx(p1,p2,p3);
		miny(p1,p2,p3);
		maxy(p1,p2,p3);
//		System.out.println(minx);
//		System.out.println(miny);
//		System.out.println(maxx);
//		System.out.println(maxy);
		
//		System.out.println( "max y - min y is:");
//		System.out.println(maxy-miny);
//		System.out.println(maxx-minx);
		
		return new Rectangle(minx,miny,maxx-minx,maxy-miny).getBounds();
		
	}

	@Override
	public void accept(ShapeVisitor sv) {
		// TODO Auto-generated method stub
		sv.visitTriangle(this);
	}
	
	public Point getP1() {
		return p1;
	}
	public Point getP2() {
		return p2;
	}
	public Point getP3() {
		return p3;
	}

	public int minx(Point p1,Point p2, Point p3) {
		
		this.minx = Math.min(p1.x,p2.x);
		
		this.minx = Math.min(minx, p3.x);
		
		return minx;
		
	}
	
	public int maxx(Point p1,Point p2, Point p3) { 
		
		this.maxx = Math.max(p1.x,p2.x);
		this.maxx = Math.max(maxx, p3.x);
		
		return maxx;
		
	}
	public int miny(Point p1,Point p2, Point p3) {
	
		this.miny = Math.min(p1.y,p2.y);
		this.miny = Math.min(miny, p3.y);
	
		return miny;
	
	}	
	public int maxy(Point p1,Point p2, Point p3) {
	
		this.maxy = Math.max(p1.y,p2.y);
		this.maxy = Math.max(maxy, p3.y);
	
		return maxy;
	
	}
}
