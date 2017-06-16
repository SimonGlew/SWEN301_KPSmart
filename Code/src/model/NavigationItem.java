package model;

import storage.BusinessEvent;

public class NavigationItem {
	
	BusinessEvent event;
	double revenue;
	double expenditure;
	boolean prev;
	boolean next;
	int numEvents;
	
	public NavigationItem(BusinessEvent event, double revenue, double expenditure, boolean prev, boolean next, int numEvents){
		this.event = event;
		this.revenue = revenue;
		this.expenditure = expenditure;
		this.prev = prev;
		this.next = next;
		this.numEvents = numEvents;
	}
	
}
