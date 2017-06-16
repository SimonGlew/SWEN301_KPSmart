package model;

import storage.BusinessEvent;

public class NavigationItem {
	
	public BusinessEvent event;
	public double revenue;
	public double expenditure;
	public boolean prev;
	public boolean next;
	public int numEvents;
	
	public NavigationItem(BusinessEvent event, double revenue, double expenditure, boolean prev, boolean next, int numEvents){
		this.event = event;
		this.revenue = revenue;
		this.expenditure = expenditure;
		this.prev = prev;
		this.next = next;
		this.numEvents = numEvents;
	}
	
}
