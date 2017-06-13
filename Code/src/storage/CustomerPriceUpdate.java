package storage;

public class CustomerPriceUpdate extends BusinessEvent {
	String to;
	String from;
	int priority;
	double weightCost;
	double volumeCost;

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


	@Override
	public String toString() {
		return "CustomerPriceUpdate{" +
				"to='" + to + '\'' +
				", from='" + from + '\'' +
				", priority=" + priority +
				", weightCost=" + weightCost +
				", volumeCost=" + volumeCost +
				"} " + super.toString();
	}
}
