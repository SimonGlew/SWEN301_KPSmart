package gui;

import javax.swing.JFrame;

import serverclient.ClientController;

/**
 * Graphical User Interface for the KPSmart Client
 *
 */
public class Gui {
	private ClientController controller;
	private JFrame frame;

	public Gui(ClientController controller){
		this.controller = controller;
		
		frame = new JFrame("KPSmart");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new HomePanel(this.controller));
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Gui(null);
	}
 }
