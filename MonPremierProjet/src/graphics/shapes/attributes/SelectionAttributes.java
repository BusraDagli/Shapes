package graphics.shapes.attributes;



public class SelectionAttributes extends Attributes {

	private boolean selected;
	private static final String id = "selectionattributes";
	
	public String getId() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select() {
		this.selected = true;
	}
	
	public void unselect() {
		this.selected = false;
	}
	
	public void toggleSelection() {
		this.selected = !this.selected;
	}
}
