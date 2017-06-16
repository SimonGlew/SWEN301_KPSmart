package gui;

import java.util.List;

public class NavTransportCostPanel extends EventCreationPanel{

	public NavTransportCostPanel(){
		initPanel();
	}
	

	public void update(String company, String to, String from, String priority, double weight, double volume,
			double maxWeight, double maxVolume, int duration, int frequency, List<String> days) {
		// TODO Auto-generated method stub
		
	}
	
	private void initPanel() {
		setBounds(0, 0, 525, 268);
		
	}

}
