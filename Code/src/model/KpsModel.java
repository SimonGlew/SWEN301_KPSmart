package model;

import java.util.Calendar;
import java.util.List;

import storage.BusinessEvent;
import storage.CustomerPriceUpdate;
import storage.KpsDatabase;
import storage.MailDelivery;
import storage.TransportCostUpdate;
import storage.TransportDiscontinued;

/**
 * Created by Jack on 6/3/2017.
 */

public class KpsModel {

	public enum Day {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};

	private RouteMap routeMap;
	private KpsDatabase database;
	
	public KpsModel(){
		routeMap = new RouteMap();
		database = new KpsDatabase();

		for(BusinessEvent event: database.getBusinessEvents()){
			processEvent(event);
		}
	}

	private void processEvent(BusinessEvent event){
		if(event instanceof TransportCostUpdate){
			processTransportCostUpdate((TransportCostUpdate)event);
		}else if(event instanceof CustomerPriceUpdate){
			processCustomerPriceUpdate((CustomerPriceUpdate)event);
		}else if(event instanceof TransportDiscontinued){
			processTransportDiscontinued((TransportDiscontinued)event);
		}else if(event instanceof MailDelivery){
			processMailDelivery((MailDelivery)event);
		}
	}

	private void processTransportCostUpdate(TransportCostUpdate event){
		int originId = routeMap.getLocationId(event.getFrom());
		if(originId == -1){
			originId = routeMap.addLocation(event.getFrom());
		}
		int destinationId = routeMap.getLocationId(event.getTo());
		if(destinationId == -1){
			destinationId = routeMap.addLocation(event.getTo());
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId == -1){
			segmentId = routeMap.addSegment(originId, destinationId);
		}
		int transportOptionId = routeMap.getSegment(segmentId).getTransportOptionId(event.getCompany(), event.getPriority());
		if(transportOptionId == -1){
			routeMap.addTransportOption(segmentId, event.getCompany(), event.getPriority(), event.getWeightCost(), event.getVolumeCost(), event.getMaxWeight(), event.getMaxVolume(), event.getFrequency(), event.getDuration(), event.getDays());
		}else{
			routeMap.updateTransportOption(segmentId, event.getCompany(), event.getPriority(), event.getWeightCost(), event.getVolumeCost(), event.getMaxWeight(), event.getMaxVolume(), event.getFrequency(), event.getDuration(), event.getDays());
		}
	}

	private void processCustomerPriceUpdate(CustomerPriceUpdate event){
		int originId = routeMap.getLocationId(event.getFrom());
		if(originId == -1){
			originId = routeMap.addLocation(event.getFrom());
		}
		int destinationId = routeMap.getLocationId(event.getTo());
		if(destinationId == -1){
			destinationId = routeMap.addLocation(event.getTo());
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId == -1){
			segmentId = routeMap.addSegment(originId, destinationId);
		}else{
			routeMap.updateSegmentPrice(segmentId, event.getPriority(), event.getWeightCost(), event.getVolumeCost());
		}
	}

	private void processTransportDiscontinued(TransportDiscontinued event){
		int originId = routeMap.getLocationId(event.getFrom());
		if(originId == -1){
			originId = routeMap.addLocation(event.getFrom());
		}
		int destinationId = routeMap.getLocationId(event.getTo());
		if(destinationId == -1){
			destinationId = routeMap.addLocation(event.getTo());
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId != -1){
			routeMap.discontinueTransportOption(segmentId, event.getCompany(), event.getPriority());
		}
	}

	private void processMailDelivery(MailDelivery event){
		
	}


	public static void println(String s){
		Calendar cal = Calendar.getInstance();
		System.out.printf("[%02d/%02d/%04d %02d:%02d:%02d] %s\n", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),
				cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), s);
	}

	public static void main(String[] args){
		new KpsModel();
	}

	public String newTransportPriceUpdate(String origin, String destination, String company, int priority,
			double pricePerGram, double maxWeight, double pricePerCube, double maxVol, List<Day> days, int frequency, double duration) {
		int originId = routeMap.getLocationId(origin);
		if(originId == -1){
			originId = routeMap.addLocation(origin);
		}
		int destinationId = routeMap.getLocationId(destination);
		if(destinationId == -1){
			destinationId = routeMap.addLocation(destination);
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId == -1){
			segmentId = routeMap.addSegment(originId, destinationId);
		}
		int transportOptionId = routeMap.getSegment(segmentId).getTransportOptionId(company, priority);
		if(transportOptionId == -1){
			routeMap.addTransportOption(segmentId, company, priority, pricePerGram, pricePerCube, maxWeight, maxVol, frequency, duration, days);
		}else{
			routeMap.updateTransportOption(segmentId, company, priority, pricePerGram, pricePerCube, maxWeight, maxVol, frequency, duration, days);
		}
		database.addTransportCostUpdate(getDateTimeNow(), "usernamesarehard", company, origin, destination, priority, pricePerGram, pricePerCube, maxWeight, maxVol, duration, frequency, days);
		return "Successfuly Updated Transport Route.";
	}
	
	public String newCustomerPriceUpdate(String origin, String destination, int priority, double weightCost, double volCost) {
		int originId = routeMap.getLocationId(origin);
		if(originId == -1){
			originId = routeMap.addLocation(origin);
		}
		int destinationId = routeMap.getLocationId(destination);
		if(destinationId == -1){
			destinationId = routeMap.addLocation(origin);
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId == -1){
			segmentId = routeMap.addSegment(originId, destinationId);
		}else{
			routeMap.updateSegmentPrice(segmentId, priority, weightCost, volCost);
		}
		database.addCustomerPriceUpdate(getDateTimeNow(), "usernamesarehard", origin, destination, priority, weightCost, volCost);
		return "Successfully Updated Customer Price";
	}
	
	public String newTransportDiscontinue(String origin, String destination, String company, int priority) {
		int originId = routeMap.getLocationId(origin);
		if(originId == -1){
			originId = routeMap.addLocation(origin);
		}
		int destinationId = routeMap.getLocationId(destination);
		if(destinationId == -1){
			destinationId = routeMap.addLocation(destination);
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId != -1){
			routeMap.discontinueTransportOption(segmentId, company, priority);
		}else{
			return "Transport Route does not exist";
		}
		database.addTransportDiscontinued(getDateTimeNow(), "usernamesarehard", company, origin, destination, priority);
		return "Successfuly Discontinued Transport Route";
	}
	
	public String getDateTimeNow(){
		return getDateTime(Calendar.getInstance());
	}
	
	public String getDateTime(Calendar c){
		//yymmddhhmmss
		return String.format("%02d%02d%02d%02d%02d%02d", 
				c.get(Calendar.YEAR)%100,
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND));
	}
	
	
	public Day parseDay(String s){
		for(Day day: Day.values()){
			if(day.toString().equalsIgnoreCase(s)){
				return day;
			}
		}
		return null;
	}
}
