package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog implements ActionListener{
	JDialog loginBox;
	JPasswordField passwordField;
	JTextField usernameField;
	JButton submit;
	JButton close;
	Gui gui;

	public LoginDialog(Gui g){
		this.gui = g;
		passwordPanel();
	}

	private void passwordPanel() {
		loginBox = new JDialog(gui.frame, "Login");
		loginBox.setModal(true);
		loginBox.setResizable(false);
		loginBox.setUndecorated(true);
		loginBox.setSize(new Dimension(420,130));
		loginBox.setPreferredSize(new Dimension(420,130));
		loginBox.setLocation((gui.frame.getWidth()/2)-(loginBox.getWidth()/2), (gui.frame.getHeight()/2)-(loginBox.getHeight()/2));
		loginBox.getContentPane().setLayout(new FlowLayout());
		loginBox.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JPanel border = new JPanel();
		border.setPreferredSize(new Dimension(400,118));
		border.setBounds(0, 0, 400, 118);
		border.setBorder(BorderFactory.createEtchedBorder());

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setPreferredSize(new Dimension(100, 30));
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(270, 30));
		border.add(usernameLabel);
		border.add(usernameField);

		JLabel password = new JLabel("Password");
		password.setPreferredSize(new Dimension(100, 30));
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(270, 30));
		border.add(password);
		border.add(passwordField);

		submit = new JButton("Submit");
		border.add(submit);
		submit.addActionListener(this);

		close = new JButton("Close");
		border.add(close);
		close.addActionListener(this);
		
		loginBox.add(border);

		loginBox.pack();
		loginBox.setVisible(true);
	}

	public void reset(){
		usernameField.setText("");
		passwordField.setText("");
		submit.setEnabled(true);
		loginBox.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(submit)){
			if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
				loginBox.setVisible(false);
				gui.loginFail();

			}else{
				gui.controller.LoginRequest(usernameField.getText(), passwordField.getText());
				loginBox.setVisible(false);
			}
		}else if(arg0.getSource().equals(close)){
			System.exit(0);
		}
	}

}
