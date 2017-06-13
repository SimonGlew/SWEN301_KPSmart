package storage;

public class TransportDiscontinued extends BusinessEvent {
	String company;
	String to;
	String from;
	int priority;

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

	@Override
	public String toString() {
		return "TransportDiscontinued{" +
				"company='" + company + '\'' +
				", to='" + to + '\'' +
				", from='" + from + '\'' +
				", priority=" + priority +
				"} " + super.toString();
	}


}
