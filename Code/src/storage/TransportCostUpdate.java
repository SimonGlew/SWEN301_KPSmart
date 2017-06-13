package storage;

import java.util.List;

import model.KpsModel;

public class TransportCostUpdate  extends BusinessEvent{
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

	public TransportCostUpdate() {

	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getWeightCost() {
		return weightCost;
	}

	public void setWeightCost(double weightCost) {
		this.weightCost = weightCost;
	}

	public double getVolumeCost() {
		return volumeCost;
	}

	public void setVolumeCost(double volumeCost) {
		this.volumeCost = volumeCost;
	}

	public double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public double getMaxVolume() {
		return maxVolume;
	}

	public void setMaxVolume(double maxVolume) {
		this.maxVolume = maxVolume;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public List<KpsModel.Day> getDays() {
		return days;
	}

	public void setDays(List<KpsModel.Day> days) {
		this.days = days;
	}

	@Override
	public String toString() {
		return "TransportCostUpdate{" +
				"company='" + company + '\'' +
				", to='" + to + '\'' +
				", from='" + from + '\'' +
				", priority=" + priority +
				", weightCost=" + weightCost +
				", volumeCost=" + volumeCost +
				", maxWeight=" + maxWeight +
				", maxVolume=" + maxVolume +
				", duration=" + duration +
				", frequency=" + frequency +
				", days=" + days +
				"} " + super.toString();
	}


}
