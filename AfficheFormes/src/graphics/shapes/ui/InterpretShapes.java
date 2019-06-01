package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.CommandException;

public class InterpretShapes {

	private InputStream in;
	private PrintStream out;
	private BufferedReader br;
	private final String command[] = {"menu","add","delete","exit"};
	private final String command2[] = {"rectangle", "triangle", "circle", "textbox"};
	private final String[] color = {"blue","black", "none","cyan","orange","red","yellow"};
	private final String[] triangles = {"equilateral", "right angled"};
	private Color filled,stroked;
	private int width,lenght,radius;
	private Point p1,p2,p3;
	private Shape addshape;
	private SCollection model;
	private String text;
	
	private boolean exit;
	private Editor editor;
	
	
	public InterpretShapes(SCollection model, Editor editor, PrintStream out) {
		this(System.in, System.out);
		this.exit = false;
		this.model = model;
		this.p1 = new Point();
		this.p2 = new Point();
		this.p3 = new Point();
		this.editor = editor;
		this.out = out;
	}
	public InterpretShapes(InputStream in, PrintStream out) {
		this.in = in;
		this.out = out;
		this.br = new BufferedReader(new InputStreamReader(this.in));
		
	}
	public boolean exit() {
		return exit;
	}
	
	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public String printPrompt() {
		// TODO Auto-generated method stub
		return "->";
	}

	public String fetch() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		while(editor.getInput() == null) {
			Thread.sleep(1000);
		}
		String input = editor.getInput();
		editor.setInput(null);
		return input;
	}

	public void execute(String command) throws IOException, CommandException, ShapeException, InterruptedException {
		// TODO Auto-generated method stub
		if(command.equals("menu")) {
			this.doMenu();
		}
		else if(command.equals("add")) {
			out.println("Chose the shape you want to add :");
			for(String item : command2) {
				out.println(item);
			}
			while(editor.getInput() == null) {
				Thread.sleep(1000);
			}
			
			String shape = (String) this.fetch();
//			String shape = fetch();
			if(shape.equals("rectangle")) {
				out.println("Chose the width :");
				this.width = Integer.parseInt(this.fetch());
				
				out.println("Chose the length :");
				this.lenght = Integer.parseInt(this.fetch());
				
				this.filled();
				this.stroked();
				
				this.addshape = new SRectangle(new Point(600,400), width,lenght);
				this.addshape.addAttributes(new ColorAttributes(true,true,filled,stroked));
				this.addshape.addAttributes(new SelectionAttributes());
				this.model.add(addshape);
				
			}
			
			else if(shape.equals("triangle")) {
				
				out.println("What kind of triangle do you want?");
				
				for(String item : triangles) {
					out.println(item);
				}
				
				while(editor.getInput() == null) {
					Thread.sleep(1000);
				}
				
				String kind = (String) this.fetch();
				if(kind.equals("equilateral")) {
					p1.x =200;
					p1.y =100;
					p2.x =50;
					p2.y =400;
					p3.x =350;
					p3.y =400;
					
					this.filled();
					this.stroked();
					this.addshape = new STriangle(new Point(p1),new Point(p2),new Point(p3),3);
					
					this.addshape.addAttributes(new ColorAttributes(true,true,filled,stroked));
					this.addshape.addAttributes(new SelectionAttributes());
					this.model.add(addshape);
				}
				
				else if(kind.equals("right angled")) {
					p1.x =100;
					p1.y =100;
					p2.x =100;
					p2.y =300;
					p3.x =300;
					p3.y =300;
				
				
				
					this.filled();
					this.stroked();
					this.addshape = new STriangle(new Point(p1),new Point(p2),new Point(p3),3);
				
					this.addshape.addAttributes(new ColorAttributes(true,true,filled,stroked));
					this.addshape.addAttributes(new SelectionAttributes());
					this.model.add(addshape);
				}
			}
			else if(shape.equals("circle")) {
				out.println("Chose the radius :");
				this.radius = Integer.parseInt(this.fetch());
				
				this.filled();
				this.stroked();
				
				this.addshape = new SCircle(new Point(600,400), radius);
				this.addshape.addAttributes(new ColorAttributes(true,true,filled,stroked));
				this.addshape.addAttributes(new SelectionAttributes());
				this.model.add(addshape);
				
			}
			else if(shape.equals("textbox")) {
				out.println("Chose your text :");
				this.text = this.fetch();
				this.filled();
				this.stroked();
				this.addshape = new SText(new Point(250,150),text);
				this.addshape.addAttributes(new ColorAttributes(true,true,filled,stroked));
				this.addshape.addAttributes(new FontAttributes());
				this.addshape.addAttributes(new SelectionAttributes());
				this.model.add(addshape);
			}
			else {
				throw new ShapeException(shape);
			}
		}
		else if(command.equals("delete")) {
				
		}
		else if(command.equals("exit")) {
			this.setExit(true);
		}
		else {
			throw new CommandException(command);
			}
		editor.sview.repaint();
		}
		
	

	public void doMenu() {
		for(String item : command) {
			out.println(item);
		}
	}
	
	public Color filled() throws IOException, InterruptedException {
		out.println("Chose the filled color :");
		for(String item : color) {
			out.println(item);
		}
		String colorf = (String) this.fetch();
		if(colorf.equals("blue")) {
			this.filled = Color.BLUE;
		}
		else if(colorf.equals("black")) {
			this.filled = Color.BLACK;
			
		}
		else if(colorf.equals("none")) {
			this.filled = Color.WHITE;
		}
		else if(colorf.equals("cyan")) {
			this.filled = Color.CYAN;
		}
		else if(colorf.equals("orange")) {
			this.filled = Color.ORANGE;
		}
		else if(colorf.equals("red")) {
			this.filled = Color.RED;
		}
		else if(colorf.equals("yellow")) {
			this.filled = Color.YELLOW;
		}
		return filled;
		
	}
	public Color stroked() throws IOException, InterruptedException {
		out.println("Chose the stroked color :");
		for(String item : color) {
			out.println(item);
		}
		String colors = (String) this.fetch();
		if(colors.equals("blue")) {
			this.stroked = Color.BLUE;
		}
		else if(colors.equals("black")) {
			this.stroked = Color.BLACK;
		}
		else if(colors.equals("none")) {
			this.stroked = Color.WHITE;
		}
		else if(colors.equals("cyan")) {
			this.stroked = Color.CYAN;
		}
		else if(colors.equals("orange")) {
			this.stroked = Color.ORANGE;
		}
		else if(colors.equals("red")) {
			this.stroked = Color.RED;
		}
		else if(colors.equals("yellow")) {
			this.stroked = Color.YELLOW;
		}
		return stroked;
	}
	public String fetch(String input) {
		editor.setInput(null);
		return input;
	}

}
