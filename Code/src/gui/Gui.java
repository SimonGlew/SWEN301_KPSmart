package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import io.Codes;
import serverclient.ClientController;

/**
 * Graphical User Interface for the KPSmart Client
 *
 */
public class Gui{
	public ClientController controller;
	public JFrame frame;
	public JDialog loginBox;
	public HomePanel homePanel;
	public LoginDialog passwordDialog;

	public Gui(ClientController controller){
		this.controller = controller;
		this.homePanel = new HomePanel(this, this.controller);
		frameSetup();
		passwordDialog = new LoginDialog(this);
	}

	public void loginSuccess(){
		System.out.println(controller.getManager());
		String manager = "";
		if(controller.getManager()){
			manager = "(Manger) ";
			homePanel.criticalRoutesButton.setEnabled(true);
			homePanel.eventNavButton.setEnabled(true);
		}else{
			homePanel.criticalRoutesButton.setEnabled(false);
			homePanel.eventNavButton.setEnabled(false);
		}
		homePanel.username.setText("Logged In: " + manager + passwordDialog.usernameField.getText());
		passwordDialog.loginBox.dispose();
		homePanel.update();
	}

	public void loginFail(){
		JOptionPane.showMessageDialog(frame, "Failed Login: Try Again");
		passwordDialog.reset();
	}

	public void logout(){
		passwordDialog = new LoginDialog(this);
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
		frame.add(homePanel);
		frame.setVisible(true);
	}
	
	public void eventSuccessfull(String string){
		JOptionPane.showMessageDialog(frame, "Event: " + string + " successfully logged");
		if(string.equals(Codes.GUICustomerPriceUpdate) 
				|| string.equals(Codes.GUIUpdatedCustomerRoute) 
				|| string.equals(Codes.GUIConfirmationMadeCustomerRoute)){
			homePanel.customerPrice.clear();			
		}else if(string.equals(Codes.GUIUpdatedRoute) ||
				string.equals(Codes.GUIConfirmationMadeRoute)){
				homePanel.transportCost.clear();
		}else if(string.equals(Codes.GUIMailDelivery)){
			homePanel.mail.clear();
		}else if(string.equals(Codes.GUIDiscontinueRoute)){
			homePanel.transportDisc.clear();
		}
	}
	
	public void eventFailed(String string){
		JOptionPane.showMessageDialog(frame, "Event Failed: " + string);
	}

	public void giveDeliveryOption(double cheapCost, double cheapRouteCost, double cheapTime, double fastestCost, double fastestRouteCost, double fastestTime) {
		homePanel.mail.showDeliveryOption(cheapCost, cheapRouteCost, cheapTime, fastestCost, fastestRouteCost, fastestTime);
	}
 }
