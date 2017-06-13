package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Graphical User Interface for the KPSmart Client
 *
 */
public class Gui {
	JFrame frame;

	public Gui(){
		frame = new JFrame("KPSmart");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new HomePanel());
		
		frame.setVisible(true);
	}
}
