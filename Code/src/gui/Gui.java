package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		frameSetup();
		passwordDialog = new LoginDialog(this);
	}

	public void loginSuccess(){
		System.out.println(controller.getManager());
		String manager = "";
		if(controller.getManager()){
			manager = "(Manger) ";
			homePanel.scrollPaneManager.setVisible(true);
		}else{
			homePanel.scrollPaneManager.setVisible(false);
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
		homePanel.scrollPaneManager.setVisible(false);
		homePanel.showPanel("PLACE_HOLDER");
		homePanel.customerPrice.clear();	
		homePanel.transportCost.clear();
		homePanel.mail.clear();
		homePanel.transportDisc.clear();
		passwordDialog.reset();
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

		this.homePanel = new HomePanel(this, this.controller);
		
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
	
	public void updateCriticalRoutes(ArrayList<ArrayList<String>> criticalRoutes) {
		homePanel.criticalRoutesPanel.updateCriticalRoutes(criticalRoutes);
	}

	public void updateBusiness(double totalRevenue, double totalExpenditure, int totalNumberOfMailDeliveryEvents,
			int totalNumberOfCustomerPriceUpdateEvents, int totalNumberOfTransportCostUpdateEvents,
			int totalNumberOfTransportDiscontinuedEvents) {
		homePanel.accounting.updateBusiness(totalRevenue, totalExpenditure, totalNumberOfMailDeliveryEvents, totalNumberOfCustomerPriceUpdateEvents, totalNumberOfTransportCostUpdateEvents, totalNumberOfTransportDiscontinuedEvents);
		
	}
 }
