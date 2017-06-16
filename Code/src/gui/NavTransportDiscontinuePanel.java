package gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NavTransportDiscontinuePanel extends EventCreationPanel{
	
	public JPanel company;
	public JPanel to ;
	public JPanel from;
	public JPanel priority;

	public NavTransportDiscontinuePanel(){
		initPanel();
	}
	
	public void update(String company, String to, String from, String priority){
		
	}

	private void initPanel() {
		setBounds(0, 0, 525, 268);
		
		JLabel companyLabel = new JLabel("Company: ");
		companyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		companyLabel.setBounds(10, 10, 200, 20);
		
		company = new JPanel();
		company.setBounds(200, 10, 200, 20);
		
	}
}
