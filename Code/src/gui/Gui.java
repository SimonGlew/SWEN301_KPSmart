package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import serverclient.ClientController;

/**
 * Graphical User Interface for the KPSmart Client
 *
 */
public class Gui {
	ClientController controller;
	JFrame frame;

	public Gui(ClientController controller){
		this.controller = controller;
		
		frame = new JFrame("KPSmart");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new HomePanel(this.controller));
		
		frame.setVisible(true);
	}
}
