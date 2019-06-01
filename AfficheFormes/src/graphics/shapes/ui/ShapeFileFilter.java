package graphics.shapes.ui;

import java.io.File;

public class ShapeFileFilter extends javax.swing.filechooser.FileFilter {

	public boolean accept(File  file) {
		// TODO Auto-generated method stub
		if(file.isDirectory())
		{
			return true;
		}
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name);
		if(extension == null)
		{
			
			return false;
		}
		if(extension.equals("shp"))
		{
			return true;
			
		}
		return false;
	} 

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub 
		return "Shape databse files (*.shp)";
	}
}
