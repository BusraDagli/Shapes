package graphics.shapes.ui;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ShapesLogin extends JPanel{

	private String password;
	private String user;
	public boolean close = false;

	public static javax.swing.JButton jButton1;
	public static javax.swing.JLabel jLabel1;
	public static javax.swing.JLabel jLabel2;
	public static javax.swing.JLabel jLabel3;
	public static javax.swing.JTextField jTextField1;

	public static JPasswordField jTextField2;

	public ShapesLogin() {
		initComponents();
		
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	public void initComponents() {
		// TODO Auto-generated method stub
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new JPasswordField();

		jButton1 = new javax.swing.JButton();
		///setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		jLabel1.setText("Librariran	 Login Form");

		jLabel2.setText("User name : ");

		jLabel3.setText("Password :");

		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}

		});

		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}

		});
		jButton1.setText("login");

		jButton1.addActionListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					jButton1ActionPerformed(e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void jButton1ActionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		//this.add(comp, constraints);
		
	
	}

	private void jTextField1ActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void jTextField2ActionPerformed(ActionEvent evt) {

	}
}
