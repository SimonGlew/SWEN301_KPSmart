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
	private Segment segment;

	public TransportOption(String firm, int priority, double weightCost, double volCost, double maxWeight,
			double maxVol, int frequency, double duration, Segment segment, List<Day> list) {
		this.transportFirm = firm;
		this.priority = priority;
		this.setWeightCost(weightCost);
		this.setVolCost(volCost);
		this.setMaxVol(maxVol);
		this.setMaxWeight(maxWeight);
		this.frequency = frequency;
		this.duration = duration;
		this.segment = segment;
		this.days = list;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public double getMaxVol() {
		return maxVol;
	}

	public void setMaxVol(double maxVol) {
		this.maxVol = maxVol;
	}

	public double getWeightCost() {
		return weightCost;
	}

	public void setWeightCost(double weightCost) {
		this.weightCost = weightCost;
	}

	public double getVolCost() {
		return volCost;
	}

	public void setVolCost(double volCost) {
		this.volCost = volCost;
	}

	public Segment getSegment(){
		return segment;
	}

	public String getTransportFirm(){
		return transportFirm;
	}

	public int getPriority() {
		return priority;
	}

	public List<Day> getDays() {
		return days;
	}

	public int getFrequency() {
		return frequency;
	}
	
	public double getDuration() {
		return duration;
	}

	public boolean containsDayInt(int day) {
		for(Day d: days){
			if(d.ordinal() == day){
				return true;
			}
		}
		return false;
	}

}
