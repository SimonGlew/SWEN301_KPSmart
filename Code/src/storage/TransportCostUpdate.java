package storage;

import java.util.List;

import model.KpsModel;

public class TransportCostUpdate  implements BusinessEvent{
	String company;
	String to;
	String from;
	int priority;
	double weightCost;
	double volumeCost;
	double maxWeight;
	double maxVolume;
	double duration;
	int frequency;
	List<KpsModel.Day> days;

	public TransportCostUpdate(String company, String to, String from, int priority, double weightCost,
			double volumeCost, double maxWeight, double maxVolume, double duration, int frequency, List<KpsModel.Day> days) {
		super();
		this.company = company;
		this.to = to;
		this.from = from;
		this.priority = priority;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.duration = duration;
		this.frequency = frequency;
		this.days = days;
	}


}
