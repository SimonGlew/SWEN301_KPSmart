package model;

import java.util.Calendar;
import java.util.List;

import io.Codes;
import storage.BusinessEvent;
import storage.CustomerPriceUpdate;
import storage.KpsDatabase;
import storage.MailDelivery;
import storage.TransportCostUpdate;
import storage.TransportDiscontinued;
import storage.UserDatabase;
import users.StaffMember;

/**
 * Created by Jack on 6/3/2017.
 */

public class KpsModel {

	public enum Day {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};

	private RouteMap routeMap;
	private KpsDatabase database;
	private UserDatabase userDatabase;

	public KpsModel(){
		routeMap = new RouteMap();
		database = new KpsDatabase();
		userDatabase = new UserDatabase();

		for(BusinessEvent event: database.getBusinessEvents()){
			processEvent(event);
		}
	}

	public List<String> getCompanies(){
		return routeMap.getAllCompanies();
	}

	public List<String> getLocations(){
		return routeMap.getAllLocations();
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

	public String newTransportPriceUpdate(String origin, String destination, String company, int priority,
			double pricePerGram, double pricePerCube, double maxWeight, double maxVol, List<Day> days, int frequency, double duration) {
		boolean newRoute = false;
		int originId = routeMap.getLocationId(origin);
		if(originId == -1){
			originId = routeMap.addLocation(origin);
			newRoute = true;
		}
		int destinationId = routeMap.getLocationId(destination);
		if(destinationId == -1){
			destinationId = routeMap.addLocation(destination);
			newRoute = true;
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

		if(newRoute){
			return Codes.ConfirmationMadeRoute;
		}else{
			return Codes.ConfirmationUpdateRoute;
		}

	}

	public String newCustomerPriceUpdate(String origin, String destination, int priority, double weightCost, double volCost) {
		boolean newRoute = false;
		int originId = routeMap.getLocationId(origin);
		if(originId == -1){
			originId = routeMap.addLocation(origin);
			newRoute = true;
		}
		int destinationId = routeMap.getLocationId(destination);
		if(destinationId == -1){
			destinationId = routeMap.addLocation(origin);
			newRoute = true;
		}
		int segmentId = routeMap.getSegmentIdFrom(originId, destinationId);
		if(segmentId == -1){
			segmentId = routeMap.addSegment(originId, destinationId);
		}else{
			routeMap.updateSegmentPrice(segmentId, priority, weightCost, volCost);
		}
		database.addCustomerPriceUpdate(getDateTimeNow(), "usernamesarehard", origin, destination, priority, weightCost, volCost);

		if(newRoute){
			return Codes.ConfirmationMadeCustomerRoute;
		}else{
			return Codes.ConfirmationUpdateCustomerRoute;
		}
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
			return Codes.DiscontinueRouteInvalid;
		}
		database.addTransportDiscontinued(getDateTimeNow(), "usernamesarehard", company, origin, destination, priority);
		return Codes.DiscontinueRouteValid;
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

	public Route getCheapestRoute(String from, String to, int priority, double weight, double volume) {
		int originId = routeMap.getLocationId(from);
		if(originId == -1){
			System.out.println("Origin not in map: " + from);
			return null;
		}
		int destinationId = routeMap.getLocationId(to);
		if(destinationId == -1){
			System.out.println("Destination not in map: " + to);
			return null;
		}
		return routeMap.findCheapestRoute(originId, destinationId, weight, volume, priority);
	}

	public Route getFastestRoute(String from, String to, int priority, double weight, double volume) {
		int originId = routeMap.getLocationId(from);
		if(originId == -1){
			return null;
		}
		int destinationId = routeMap.getLocationId(to);
		if(destinationId == -1){
			return null;
		}
		return routeMap.findCheapestRoute(originId, destinationId, weight, volume, priority);
	}

	public StaffMember validateLogin(String username, String password) {
		return userDatabase.solveUserWithCredentials(username, password);
	}
}
