package model;

import java.util.ArrayList;
import java.util.List;

import storage.BusinessEvent;
import storage.KpsDatabase;
import storage.MailDelivery;

public class BusinessMonitoring {

	KpsDatabase database;
	RouteMap routeMap;
	
	public BusinessMonitoring(KpsDatabase database, RouteMap routeMap){
		this.database = database;
		this.routeMap = routeMap;
	}
	
	public double getRevenue(){
		return getRevenue(database.getBusinessEvents());
	}
	
	public double getRevenue(List<BusinessEvent> events){
		double revenue = 0;
		for(BusinessEvent event: events){
			if(event instanceof MailDelivery){
				MailDelivery mail = (MailDelivery)event;
				revenue += mail.getKpsCost();
			}
		}
		return revenue;
	}
	
	public double getExpenditure(){
		return getExpenditure(database.getBusinessEvents());
	}
	
	public double getExpenditure(List<BusinessEvent> events){
		double expenditure = 0;
		for(BusinessEvent event: events){
			if(event instanceof MailDelivery){
				MailDelivery mail = (MailDelivery)event;
				expenditure += mail.getRouteCost();
			}
		}
		return expenditure;
	}
	
	public int getNumberOfEvents(){
		return database.getBusinessEvents().size();
	}
	
	public int getNumberOfMailDelivery(){
		return database.getAllMailDelivery().size();
	}
	
	public int getNumberOfCustomerPriceUpdate(){
		return database.getAllCustomerPriceUpdate().size();
	}
	
	public int getNumberOfTransportCostUpdate(){
		return database.getAllTransportCostUpdates().size();
	}
	
	public int getNumberOfTransportDiscontinued(){
		return database.getAllTransportDiscontinued().size();
	}
	
	public int getNumberOfItems(String to, String from, int priority){
		int numItems = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			numItems++;
		}
		return numItems;
	}
	
	public double getTotalVolume(String to, String from, int priority){
		double totalVolume = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			totalVolume += mail.getVolume();
		}
		return totalVolume;
	}
	
	public double getTotalWeight(String to, String from, int priority){
		double totalWeight = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			totalWeight += mail.getWeight();
		}
		return totalWeight;
	}
	
	public double getAverageDeliveryTime(String to, String from, int priority){
		double numItems = 0;
		double totalTime = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			numItems++;
			totalTime += mail.getHours();
		}
		if(numItems == 0){
			return 0;
		}
		return totalTime/numItems++;
	}
	
	public List<CriticalRoute> getCriticalRoutes(){
		double numItems = getNumberOfItems(null, null, 0);
		double averageWeight = getTotalWeight(null, null, 0)/numItems;
		double averageVolume = getTotalVolume(null, null, 0)/numItems;
		List<CriticalRoute> criticalRoutes = new ArrayList<CriticalRoute>();
		for(Segment segment: routeMap.getSegments()){
			for(TransportOption option: segment.getTransportOptions().values()){
				double averageKpsCost = segment.getWeightCost(option.getPriority())*averageWeight 
						+ segment.getVolCost(option.getPriority())*averageVolume;
				double averageRouteCost = option.getWeightCost()*averageWeight
						+ option.getVolCost()*averageVolume;
				if(averageRouteCost > averageKpsCost){
					criticalRoutes.add(new CriticalRoute(segment.getOrigin().getName(), segment.getDestination().getName(), 
							option.getPriority(), option.getTransportFirm(), averageRouteCost - averageKpsCost));
				}
			}
		}		
		System.out.println(criticalRoutes.size());
		return criticalRoutes;
	}
	
	public NavigationItem getNavigationItem(int eventID){
		List<BusinessEvent> events = database.getBusinessEvents();
		if(eventID == -1){
			return new NavigationItem(events.get(events.size()-1), getRevenue(), getExpenditure(), events.size() > 1, false, events.size());
		}
		List<BusinessEvent> eventSoFar = new ArrayList<BusinessEvent>();
		for(int i = 0; i < events.size(); i++){
			eventSoFar.add(events.get(i));
			if(events.get(i).getId() == eventID){
				double revenue = getRevenue(eventSoFar);
				double expenditure = getExpenditure(eventSoFar);
				return new NavigationItem(events.get(i), revenue, expenditure, i > 0, i < events.size() - 1, events.size());
			}
		}
		return null;
	}
	
}
