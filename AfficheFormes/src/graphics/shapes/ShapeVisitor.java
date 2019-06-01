package graphics.shapes;

public interface ShapeVisitor{

	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitText(SText text);
	public void visitCollection(SCollection clt);
	public void visitTriangle(STriangle trgl);
	
}
