package gui;

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
		
		JLabel companyLabel = new JLabel("Company: ");
		companyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		companyLabel.setBounds(10, 10, 200, 20);
		add(companyLabel);
		
		company = new JLabel();
		company.setFont(new Font("Tahoma", Font.PLAIN, 16));
		company.setBounds(200, 10, 200, 20);
		add(company);
		
		JLabel toLabel = new JLabel("To: ");
		toLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		toLabel.setBounds(10, 50, 200, 20);
		add(toLabel);
		
		to = new JLabel();
		to.setFont(new Font("Tahoma", Font.PLAIN, 16));
		to.setBounds(200, 50, 200, 20);
		add(to);
		
		JLabel fromLabel = new JLabel("From: ");
		fromLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fromLabel.setBounds(10, 90, 200, 20);
		add(fromLabel);
		
		from = new JLabel();
		from.setFont(new Font("Tahoma", Font.PLAIN, 16));
		from.setBounds(200, 90, 200, 20);
		add(from);
		
		JLabel priorityLabel = new JLabel("Priority: ");
		priorityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priorityLabel.setBounds(10, 120, 200, 20);
		add(priorityLabel);
		
		priority = new JLabel();
		priority.setFont(new Font("Tahoma", Font.PLAIN, 16));
		priority.setBounds(200, 120, 200, 20);
		add(priority);
	}
}
