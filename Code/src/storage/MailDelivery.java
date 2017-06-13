package storage;

import java.util.List;

import model.KpsModel;
import model.KpsModel.Day;

public class MailDelivery extends BusinessEvent{
	Day day;
	String to;
	String from;
	double weight;
	double volume;
	int priority;
	double kpsCost;
	double routeCost;

	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public double getKpsCost() {
		return kpsCost;
	}

	public void setKpsCost(double kpsCost) {
		this.kpsCost = kpsCost;
	}

	public double getRouteCost() {
		return routeCost;
	}

	public void setRouteCost(double routeCost) {
		this.routeCost = routeCost;
	}

	@Override
	public String toString() {
		return "MailDelivery{" +
				"day=" + day +
				", to='" + to + '\'' +
				", from='" + from + '\'' +
				", weight=" + weight +
				", volume=" + volume +
				", priority=" + priority +
				", kpsCost=" + kpsCost +
				", routeCost=" + routeCost +
				"} " + super.toString();
	}
}
