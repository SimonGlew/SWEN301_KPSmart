package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class NavTransportDiscontinuePanel extends EventCreationPanel{
	
	public JLabel company;
	public JLabel to ;
	public JLabel from;
	public JLabel priority;

	public NavTransportDiscontinuePanel(){
		initPanel();
	}
	
	public void update(String company, String to, String from, String priority){
		this.company.setText(company);
		this.to.setText(to);
		this.from.setText(from);
		this.priority.setText(priority);
	}

	private void initPanel() {
		setBounds(0, 0, 525, 268);
		setLayout(null);
		
		JLabel priorityLabel = new JLabel("Priority: ");
		priorityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priorityLabel.setBounds(10, 10, 200, 20);
		add(priorityLabel);
		
		priority = new JLabel("N/A");
		priority.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priority.setBounds(150, 10, 200, 20);
		add(priority);
		
		JLabel toLabel = new JLabel("To: ");
		toLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toLabel.setBounds(10, 50, 200, 20);
		add(toLabel);
		
		to = new JLabel("N/A");
		to.setFont(new Font("Tahoma", Font.PLAIN, 16));
		to.setBounds(150, 50, 200, 20);
		add(to);
		
		JLabel fromLabel = new JLabel("From: ");
		fromLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fromLabel.setBounds(10, 90, 200, 20);
		add(fromLabel);
		
		from = new JLabel("N/A");
		from.setFont(new Font("Tahoma", Font.PLAIN, 16));
		from.setBounds(150, 90, 200, 20);
		add(from);
		
		JLabel companyLabel = new JLabel("Company: ");
		companyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		companyLabel.setBounds(10, 130, 200, 20);
		add(companyLabel);
		
		company = new JLabel("N/A");
		company.setFont(new Font("Tahoma", Font.PLAIN, 16));
		company.setBounds(150, 130, 200, 20);
		add(company);

	}
}
