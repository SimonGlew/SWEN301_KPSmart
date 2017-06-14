package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginDialog implements ActionListener{
	JDialog loginBox;
	JTextField passwordField;
	JTextField usernameField;
	JButton submit;
	Gui gui;

	public LoginDialog(Gui g){
		this.gui = g;
		passwordPanel();
	}

	private void passwordPanel() {
		loginBox = new JDialog(gui.frame, "Login");
		loginBox.setModal(true);
		loginBox.setResizable(false);
		loginBox.setSize(new Dimension(420,150));
		loginBox.setPreferredSize(new Dimension(420,150));
		loginBox.setLocation((gui.frame.getWidth()/2)-(loginBox.getWidth()/2), (gui.frame.getHeight()/2)-(loginBox.getHeight()/2));
		loginBox.getContentPane().setLayout(new FlowLayout());
		loginBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setPreferredSize(new Dimension(100, 30));
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(300, 30));
		loginBox.getContentPane().add(usernameLabel);
		loginBox.getContentPane().add(usernameField);

		JLabel password = new JLabel("Password");
		password.setPreferredSize(new Dimension(100, 30));
		passwordField = new JTextField();
		passwordField.setPreferredSize(new Dimension(300, 30));
		loginBox.getContentPane().add(password);
		loginBox.getContentPane().add(passwordField);

	    submit = new JButton("Submit");
	    loginBox.getContentPane().add(submit);
	    submit.addActionListener(this);

	    loginBox.pack();
	    loginBox.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		gui.controller.LoginRequest(usernameField.getText(), passwordField.getText());
		submit.setEnabled(false);

		while(!gui.controller.logged){System.out.println("waiting");}


		gui.homePanel.username.setText("Logged In: " );
		loginBox.setVisible(false);
	}
}
