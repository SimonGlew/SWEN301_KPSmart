package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import serverclient.ClientController;

/**
 * Graphical User Interface for the KPSmart Client
 *
 */
public class Gui implements ActionListener{
	private ClientController controller;
	private JFrame frame;
	private JDialog loginBox;

	public Gui(ClientController controller){
		this.controller = controller;
		
		frameSetup();
		passwordPanel();
	}
	
	private void frameSetup(){
		// Set LookAndFeel to Nimbus
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) { }
		}
		
		frame = new JFrame("KPSmart");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new HomePanel(this.controller));
		frame.setVisible(true);
	}

	private void passwordPanel() {
		loginBox = new JDialog(frame, "Login");
		loginBox.setModal(true);
		loginBox.setResizable(false);
		loginBox.setSize(new Dimension(420,150));
		loginBox.setPreferredSize(new Dimension(420,150));
		loginBox.setLocation((frame.getWidth()/2)-(loginBox.getWidth()/2), (frame.getHeight()/2)-(loginBox.getHeight()/2));
		loginBox.getContentPane().setLayout(new FlowLayout());
		loginBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setPreferredSize(new Dimension(100, 30));
		JTextField usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(300, 30));
		loginBox.getContentPane().add(usernameLabel);
		loginBox.getContentPane().add(usernameField);
		
		JLabel password = new JLabel("Password");
		password.setPreferredSize(new Dimension(100, 30));
		JTextField passwordField = new JTextField();
		passwordField.setPreferredSize(new Dimension(300, 30));
		loginBox.getContentPane().add(password);
		loginBox.getContentPane().add(passwordField);
		
	    JButton submit = new JButton("Submit");
	    loginBox.getContentPane().add(submit);
	    submit.addActionListener(this);
	    
	    loginBox.pack();
	    loginBox.setVisible(true);
	}
	public static void main(String[] args) {
		new Gui(null);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		loginBox.setVisible(false);
	}
 }
