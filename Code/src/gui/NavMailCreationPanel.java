package gui;

import java.awt.Font;

import javax.swing.JLabel;

public class NavMailCreationPanel extends EventCreationPanel{
	
	public JLabel to ;
	public JLabel from;
	public JLabel priority;
	public JLabel volume;
	public JLabel weight;
	public JLabel kpsCost;
	public JLabel routeCost;
	public JLabel hours;

	public NavMailCreationPanel(){
		initPanel();
	}
	
	public void update(String day, String from, String to, String priority, double volume, double weight, double kpsCost, double routeCost, int hours){
		this.to.setText(to);
		this.from.setText(from);
		this.priority.setText(priority);
		this.volume.setText(volume + "");
		this.weight.setText(weight + "");
		this.kpsCost.setText(kpsCost + "");
		this.routeCost.setText(routeCost + "");
		this.hours.setText(hours + "");
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

		JLabel volumeLabel = new JLabel("Volume: ");
		volumeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volumeLabel.setBounds(10, 130, 200, 20);
		add(volumeLabel);
		
		volume = new JLabel("N/A");
		volume.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volume.setBounds(150, 130, 200, 20);
		add(volume);

		JLabel weightLabel = new JLabel("Weight: ");
		weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weightLabel.setBounds(10, 170, 200, 20);
		add(weightLabel);
		
		weight = new JLabel("N/A");
		weight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weight.setBounds(150, 170, 200, 20);
		add(weight);
		
		JLabel kpsLabel = new JLabel("KPS Cost: ");
		kpsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kpsLabel.setBounds(230, 10, 200, 20);
		add(kpsLabel);
		
		kpsCost = new JLabel("N/A");
		kpsCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kpsCost.setBounds(390, 10, 200, 20);
		add(kpsCost);	
		
		JLabel routeLabel = new JLabel("Route Cost: ");
		routeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		routeLabel.setBounds(230, 50, 200, 20);
		add(routeLabel);
		
		routeCost = new JLabel("N/A");
		routeCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		routeCost.setBounds(390, 50, 200, 20);
		add(routeCost);
		
		JLabel hoursLabel = new JLabel("Delivery Time (hrs): ");
		hoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hoursLabel.setBounds(230, 90, 250, 20);
		add(hoursLabel);
		
		hours = new JLabel("N/A");
		hours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hours.setBounds(390, 90, 200, 20);
		add(hours);

	}
}
