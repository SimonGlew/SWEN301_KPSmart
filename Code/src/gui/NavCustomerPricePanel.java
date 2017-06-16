package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class NavCustomerPricePanel extends EventCreationPanel{

	public JLabel to ;
	public JLabel from;
	public JLabel priority;
	public JLabel volumeCost;
	public JLabel weightCost;
	
	public NavCustomerPricePanel(){
		initPanel();
	}
	
	public void update(String from, String to, String priority, double volumeCost, double weightCost){
		this.to.setText(to);
		this.from.setText(from);
		this.priority.setText(priority);
		this.volumeCost.setText(volumeCost + "");
		this.weightCost.setText(weightCost + "");
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
		
		JLabel volumeLabel = new JLabel("Volume Cost: ");
		volumeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volumeLabel.setBounds(10, 130, 200, 20);
		add(volumeLabel);
		
		volumeCost = new JLabel("N/A");
		volumeCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volumeCost.setBounds(150, 130, 200, 20);
		add(volumeCost);

		JLabel weightLabel = new JLabel("Weight Cost: ");
		weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weightLabel.setBounds(10, 170, 200, 20);
		add(weightLabel);
		
		weightCost = new JLabel("N/A");
		weightCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weightCost.setBounds(150, 170, 200, 20);
		add(weightCost);


	}
}
