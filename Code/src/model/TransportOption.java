package model;

import java.util.ArrayList;
import java.util.List;
import model.KpsModel.Day;

/**
 * Created by Jack on 6/3/2017.
 */
public class TransportOption {
	private String transportFirm;
	private int priority;
	private double weightCost, volCost, maxWeight, maxVol;
	private int frequency;
	private double duration;
	private List<KpsModel.Day> days;

	public TransportOption(String firm, int priority, double weightCost, double volCost, double maxWeight,
			double maxVol, int frequency, double duration, Day... days) {
		this.transportFirm = firm;
		this.priority = priority;
		this.weightCost = weightCost;
		this.volCost = volCost;
		this.maxVol = maxVol;
		this.maxWeight = maxWeight;
		this.frequency = frequency;
		this.duration = duration;
		this.days = new ArrayList<Day>();
		for(Day day: days){
			this.days.add(day);
		}
	}
}
