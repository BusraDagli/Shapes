package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.STriangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.Editor.MyButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
public class Editor extends JFrame
{
	ShapesView sview;
	SCollection model;
	JLabel output;
	String op;
	String input;
	JTextArea textArea;
	private PrintStream printStream;
	private UndoManager undoManager;
	private JFileChooser fileChooser;
	private JPanel testComponent;
	private MyButton undoButton;
	private MyButton redoButton;

	private ArrayList<Shape> selected_data_shape;
	private JPanel loginComponent;
	
	public Editor()
	{	
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		
		this.selected_data_shape = new ArrayList<>();
		this.undoManager = new UndoManager();
		this.undoButton = new MyButton("Undo", e -> {
			undoManager.undo();
			this.sview.repaint();
		});
		this.redoButton = new MyButton("Redo", e -> {
			undoManager.redo();
			this.sview.repaint();
		});
//		fileChooser.addChoosableFileFilter(f);
		// jButton1.setSize(400, 100);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		this.buildLogin();
		this.buildModel();

		this.sview = new ShapesView(this.model);
//		this.sview.setPreferredSize(new Dimension(300,300));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(900, 600));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);

		buildMenu();
		buildView();
		this.fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new ShapeFileFilter());
		
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(300,300));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
		buildConsole();
	}
	


	private void buildLogin() {
		// TODO Auto-generated method stub
		this.loginComponent = new ShapesLogin();
	}
	private void buildView() {
		// TODO Auto-generated method stub
		this.buildTestComponent();
//		this.setLayout(new BorderLayout());
		testComponent.setPreferredSize(new Dimension(250, 200));
		this.add(testComponent, BorderLayout.LINE_END);

	}

	private void buildModel()
	{		
		
		this.model = new SCollection();
		
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(10,10),20,30);
		r.addAttributes(new ColorAttributes(true,false,Color.BLUE,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		this.model.add(r);
		
		SCircle c = new SCircle(new Point(100,100),10);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);
		
		SText t= new SText(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new FontAttributes());
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);
		
		STriangle tri = new STriangle(new Point(100, 200), new Point(180,100), new Point(250,150),3);
		tri.addAttributes(new ColorAttributes(true,true,Color.blue,Color.ORANGE));
		tri.addAttributes(new SelectionAttributes());
		this.model.add(tri);
		
		SCollection sc = new SCollection();
		sc.addAttributes(new SelectionAttributes());
		r= new SRectangle(new Point(20,30),30,30);
		r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
		r.addAttributes(new SelectionAttributes());
		sc.add(r);
		c = new SCircle(new Point(150,100),20);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
		c.addAttributes(new SelectionAttributes());
		sc.add(c);
		this.model.add(sc);
		
	}
	private void buildConsole() {
		JPanel console = new JPanel();
	    textArea = new JTextArea(2, 10);
	    
	    JTextArea textAreaConsole = new JTextArea(2, 10);
	    textArea.getDocument().putProperty("filterNewlines", Boolean.TRUE);
	    textAreaConsole.setEditable(false);
	    textArea.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if(e.getKeyCode() == KeyEvent.VK_ENTER){
					textAreaConsole.setText(null);
					input = textArea.getText().trim();
					System.out.println(input);
					textArea.setText(null);
	            }
	        }

	    });
	    printStream = new PrintStream(new InterpretOutputStream(textAreaConsole));
		output = new JLabel("");
		console.add(textAreaConsole);
		console.add(output);
		console.add(textArea);
		this.add(console,BorderLayout.PAGE_END);

	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	private void buildMenu() {
		JMenuBar jmenuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		

		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		// Mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		// action Listener
		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (fileChooser.showOpenDialog(Editor.this) == JFileChooser.APPROVE_OPTION) {
					// to String method
					try {
						System.out.println(fileChooser.getSelectedFile());
						Editor.this.loadFromFileCall(fileChooser.getSelectedFile());
						Editor.this.sview.repaint();
					} catch (IOException | ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(Editor.this, "Impossible d'ouvrir", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (fileChooser.showSaveDialog(Editor.this) == JFileChooser.APPROVE_OPTION) {
					// to String method
					try {

						Editor.this.saveToFileCall(fileChooser.getSelectedFile());
//						Editor.this.saveToFileCall(new File("hello.shp"));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(Editor.this, "Impossible d'enregistrer", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
//					System.out.println(fileChooser.getSelectedFile());
			}
		});
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int action = JOptionPane.showConfirmDialog(Editor.this, "voulez vraiment quitter l'application?",
						"Confirmer", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		// accelerato for the menu
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		JMenu windowMenu = new JMenu("Window");
	
		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Buttons Toolbar");

		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
	
		windowMenu.add(showMenu);
		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
				testComponent.setVisible(menuItem.isSelected());
			}
		});
		showFormItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		JMenu editMenu = new JMenu("Edit");

		JMenuItem undoItem = new JMenuItem("undo");
		JMenuItem redoItem = new JMenuItem("redo");
		JMenuItem cutItem = new JMenuItem("cut");
		JMenuItem copyItem = new JMenuItem("copy");
		JMenuItem pasteItem = new JMenuItem("paste");
		JMenuItem deleteItem = new JMenuItem("delete");
		JMenuItem selectAllItem = new JMenuItem("selectAll");

		editMenu.add(undoItem);
		editMenu.add(redoItem);
		editMenu.addSeparator();
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.addSeparator();
		editMenu.add(deleteItem);
		editMenu.add(selectAllItem);
		
		editMenu.setMnemonic(KeyEvent.VK_E);
		undoItem.setMnemonic(KeyEvent.VK_Z);
		redoItem.setMnemonic(KeyEvent.VK_Y);

		undoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Editor.this.undoManager.undo();
				Editor.this.sview.repaint();
			}
		});
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		redoItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Editor.this.undoManager.redo();
				Editor.this.sview.repaint();
			}
		});
		redoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));

//		this.menuBar.add(new MyButton("ADDCircle", new AddShapeAction("circle",undoManager, model,this.sview)));
		jmenuBar.add(fileMenu);
		jmenuBar.add(windowMenu);
		jmenuBar.add(editMenu);
		this.setJMenuBar(jmenuBar);

	}

	private void buildTestComponent() {
		// TODO Auto-generated method stub
		this.testComponent = new JPanel(new GridLayout(0, 1, 5, 0));
//	    this.testComponent.add(new MyButton("ADDCircle", new AddCircleAction(undoManager, model)));
		this.testComponent.add(new MyButton("ADDCircle", new AddShapeAction("circle", undoManager, model, this)));
		this.testComponent
				.add(new MyButton("ADDRectangle", new AddShapeAction("rectangle", undoManager, model, this)));
		this.testComponent
				.add(new MyButton("ADDTriangle", new AddShapeAction("triangle", undoManager, model, this)));

		//JTextArea jt = new JTextArea("hhh");
		//this.testComponent.add(jt);
		Shape shapetodelete = ShapeController.shape;
		this.testComponent.add(new MyButton("Pastselected", new PasteShapeAction(undoManager, model, this)));
		this.testComponent.add(new MyButton("SelectAll", new SelectShapeAction(undoManager, model, this)));
		this.testComponent.add(new MyButton("DELETEselected", new DeleteAction(undoManager, model, shapetodelete, this)));
//	    this.testComponent.add(new MyButton("ADDText", new AddAction(undoManager, model,new SText(new Point(100,350),"hello") )));
		this.testComponent.add(undoButton);
		this.testComponent.add(redoButton);
		undoManager.addPropertyChangeListener(e -> updateButtons());
		updateButtons();
	}

	private void updateButtons() {
		// TODO Auto-generated method stub
		undoButton.setVisible(undoManager.isUndoAvailable());
		undoButton.setText("Undo " + undoManager.getUndoName());
		redoButton.setVisible(undoManager.isRedoAvailable());
		redoButton.setText("Redo " + undoManager.getRedoName());
	}
	
	public static class MyButton extends JButton {
		public MyButton(String text, ActionListener actionListener) {
			super(text);
			addActionListener(actionListener);

		}

//		public MyButton(String text, DeleteAction deleteAction) {
//			super(text);
//			addActionListener((ActionListener) deleteAction);
//		}
	}

	public void saveTofile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Shape[] shapes = this.model.getArray().toArray(new Shape[this.model.getArray().size()]);
		for (int i = 0; i < shapes.length; i++) {
			System.out.println(shapes[i].getLoc());
		}
		oos.writeObject(shapes);
//		oos.writeObject(this.model.getArray().get(0));
//		oos.writeObject(this.model.getArray());
//		oos.flush();

		oos.close();

	}

	public void loadFromFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Shape[] shapes = (Shape[]) ois.readObject();
		this.model.getArray().clear();
//			this.model.getArray().addAll(Arrays.asList(shapes));
		this.model.getArray().add(new SRectangle());
//			this.model.getArray().addAll(shapes1);

//		fis.close();

		ois.close();
	}

	public void saveToFileCall(File file) throws IOException {
		this.saveTofile(file);
	}

	public void loadFromFileCall(File file) throws IOException, ClassNotFoundException {
		this.loadFromFile(file);
	}
	public static void main(String[] args) throws IOException, CommandException, ShapeException, InterruptedException
	{
		Editor self = new Editor();
		InterpretShapes interpret = new InterpretShapes(self.model, self, self.printStream);
		self.pack();
		self.sview.requestFocusInWindow();
		self.setVisible(true);
		
		while(!interpret.exit()) {
			
			try {
				self.output.setText("<html>"+interpret.printPrompt()+"<br>"+"</html>");
				if(self.input!=null) {
					interpret.execute(interpret.fetch(self.input));
					self.input = null;
				}

				
				}
				
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
