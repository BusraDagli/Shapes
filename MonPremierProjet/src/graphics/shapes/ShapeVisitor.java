package graphics.shapes;

public interface ShapeVisitor {

	
	public void visitRectangle(SRectangle r);
	public void visitCircle(SCircle c);
	public void visitText(SText t);
	public void visitCollection(SCollection co);
	public void visitTriangle(STriangle tri);
}
