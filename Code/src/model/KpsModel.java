package model;

import java.util.Calendar;

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

	}

	private void processTransportDiscontinued(TransportDiscontinued event){

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
}
