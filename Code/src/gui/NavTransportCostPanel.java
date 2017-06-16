package gui;

import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;

public class NavTransportCostPanel extends EventCreationPanel{

	public JLabel company;
	public JLabel to ;
	public JLabel from;
	public JLabel weight;
	public JLabel priority;
	public JLabel volume;
	public JLabel maxWeight;
	public JLabel maxVolume;
	public JLabel duration;
	public JLabel frequency;
	public JLabel days;
	
	public NavTransportCostPanel(){
		initPanel();
	}
	

	public void update(String company, String to, String from, String priority, double weight, double volume,
			double maxWeight, double maxVolume, int duration, int frequency, List<String> days) {
		this.to.setText(to);
		this.from.setText(from);
		this.priority.setText(priority);
		this.company.setText(company);
		this.weight.setText(weight + "");
		this.volume.setText(volume + "");
		this.maxWeight.setText(maxWeight + "");
		this.maxVolume.setText(maxVolume + "");
		this.duration.setText(duration + "");
		this.frequency.setText(frequency + "");
		updateDays(days);
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
		
		JLabel weightLabel = new JLabel("Weight: ");
		weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weightLabel.setBounds(10, 170, 200, 20);
		add(weightLabel);
		
		weight = new JLabel("N/A");
		weight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weight.setBounds(150, 170, 200, 20);
		add(weight);
		
		JLabel volumeLabel = new JLabel("Volume: ");
		volumeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volumeLabel.setBounds(10, 210, 200, 20);
		add(volumeLabel);
		
		volume = new JLabel("N/A");
		volume.setFont(new Font("Tahoma", Font.PLAIN, 16));
		volume.setBounds(150, 210, 200, 20);
		add(volume);
		
		JLabel maxWeightLabel = new JLabel("Max Weight: ");
		maxWeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		maxWeightLabel.setBounds(315, 10, 200, 20);
		add(maxWeightLabel);
		
		maxWeight = new JLabel("N/A");
		maxWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		maxWeight.setBounds(460, 10, 200, 20);
		add(maxWeight);
		
		JLabel maxVolumeLabel = new JLabel("Max Volume: ");
		maxVolumeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		maxVolumeLabel.setBounds(315, 50, 200, 20);
		add(maxVolumeLabel);
		
		maxVolume = new JLabel("N/A");
		maxVolume.setFont(new Font("Tahoma", Font.PLAIN, 16));
		maxVolume.setBounds(460, 50, 200, 20);
		add(maxVolume);
		
		JLabel durationLabel = new JLabel("Duration: ");
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		durationLabel.setBounds(315, 90, 200, 20);
		add(durationLabel);
		
		duration = new JLabel("N/A");
		duration.setFont(new Font("Tahoma", Font.PLAIN, 16));
		duration.setBounds(460, 90, 200, 20);
		add(duration);
		
		JLabel frequencyLabel = new JLabel("Frequency: ");
		frequencyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frequencyLabel.setBounds(315, 130, 200, 20);
		add(frequencyLabel);
		
		frequency = new JLabel("N/A");
		frequency.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frequency.setBounds(460, 130, 200, 20);
		add(frequency);

		JLabel daysLabel = new JLabel("Days: ");
		daysLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		daysLabel.setBounds(315, 170, 200, 20);
		add(daysLabel);
		
		days = new JLabel("N/A");
		days.setFont(new Font("Tahoma", Font.PLAIN, 16));
		days.setBounds(315, 200, 300, 20);
		add(days);
	}
	
	private void updateDays(List<String> days) {
		String d = "";
		for(String s: days){
			if(s.equals("Monday")) d += "MO";
			else if(s.equals("Tuesday")) d += ", TU";
			else if(s.equals("Wednesday")) d += ", WE";
			else if(s.equals("Thursday")) d += ", TH";
			else if(s.equals("Friday")) d += ", FR";
			else if(s.equals("Saturday")) d += ", SA";
			else if(s.equals("Sunday")) d += ", SU";
		}
		
		this.days.setText(d);
	}

}
