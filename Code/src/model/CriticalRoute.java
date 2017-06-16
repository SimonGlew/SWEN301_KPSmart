package model;

public class CriticalRoute {

	String from;
 	String to;
	int priority;
	String company;
	double priceDiff;
	
	public CriticalRoute(String from, String to, int priority, String company, double priceDiff){
		this.from = from;
		this.to = to;
		this.priority = priority;
		this.company = company;
		this.priceDiff = priceDiff;
	}
}
