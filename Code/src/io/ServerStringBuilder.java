package io;

import java.util.ArrayList;
import java.util.List;

import gui.CustomerPricePanel;
import model.KpsModel;
import model.Location;
import model.NavigationItem;
import storage.CustomerPriceUpdate;
import storage.MailDelivery;
import storage.TransportCostUpdate;
import storage.TransportDiscontinued;

public class ServerStringBuilder {

	public static String makeNewRouteString(String to, String from) {
		return to + "_" + from;
	}

	public static String makeBusinessFigures(double totalRevenue, double totalExpenditure, int totalNumberOfMailDeliveryEvents, int totalNumberOfCustomerPriceUpdateEvents, int totalNumberOfTransportCostUpdateEvents, int totalNumberOfTransportDiscontinuedEvents) {
		return totalRevenue + "_" + totalExpenditure + "_" + totalNumberOfMailDeliveryEvents + "_" + totalNumberOfCustomerPriceUpdateEvents + "_" + totalNumberOfTransportCostUpdateEvents + "_" + totalNumberOfTransportDiscontinuedEvents;
	}

	public static String makeMailDeliveryString(double cheapestCost, double cheapestRouteCost, int cheapestTime, double fastestCost,
			double fastestRouteCost, int fastestTime) {
		return cheapestCost + "_" + cheapestRouteCost + "_" + cheapestTime + "_" + fastestCost + "_" + fastestRouteCost + "_" + fastestTime;
	}

	public static String makeLocationListString(ArrayList<Location> locations) {
		String s = "";
		for (int i = 0; i < locations.size(); i++) {
			if (i == locations.size() - 1)
				s += locations.get(i);
			else
				s += locations.get(i) + "_";
		}
		return s;
	}

	public static String makeCompanyListString(ArrayList<String> companies) {
		String s = "";
		for (int i = 0; i < companies.size(); i++) {
			if (i == companies.size() - 1)
				s += companies.get(i);
			else
				s += companies.get(i) + "_";
		}
		return s;
	}
	
	
	public static String makeEventLogString(NavigationItem event){
		String finalString = "";
		String businessEvent = event.event.getId() + "," + event.event.getUsername() + "," + event.event.getDateyymmddhhmmss() + ",";
		if(event.event instanceof MailDelivery){
			MailDelivery e = (MailDelivery)event.event;
			businessEvent += (e.getDay() + "," + e.getFrom() + "," + e.getTo() + "," + ServerParser.getPriorityFromInt(e.getPriority()) + "," + e.getVolume() + "," + e.getWeight() + "," + e.getKpsCost() + "," + e.getRouteCost() + "," + e.getHours());
			finalString = Codes.LogMailDelivery + "_" + businessEvent;
		}else if(event.event instanceof CustomerPriceUpdate){
			CustomerPriceUpdate e = (CustomerPriceUpdate)event.event;
			businessEvent += (e.getFrom() + "," + e.getTo() + "," + ServerParser.getPriorityFromInt(e.getPriority()) + "," + e.getVolumeCost() + "," + e.getWeightCost());
			finalString = Codes.LogCustomerUpdate + "_" + businessEvent;
		}else if(event.event instanceof TransportCostUpdate){
			TransportCostUpdate e = (TransportCostUpdate)event.event;
			String days = "";
			for(int i = 0; i < e.getDays().size(); i ++){
				days += e.getDays().get(i);
				if(i != e.getDays().size() - 1){
					days += "%";
				}
			}
			businessEvent += (e.getCompany() + "," + e.getTo() + "," + e.getFrom() + "," + ServerParser.getPriorityFromInt(e.getPriority()) + "," + e.getWeightCost() + "," + e.getVolumeCost() + "," + e.getMaxWeight() + "," + e.getMaxVolume() + "," + (int)e.getDuration() + "," + e.getFrequency() + "," + days);
			finalString = Codes.LogTransportUpdate + "_" + businessEvent;
			

		}else if(event.event instanceof TransportDiscontinued){
			TransportDiscontinued e = (TransportDiscontinued)event.event;
			businessEvent += (e.getCompany() + "," + e.getTo() + "," + e.getFrom() + "," + ServerParser.getPriorityFromInt(e.getPriority()));
			finalString = Codes.LogTransportDiscontinue + "_" + businessEvent;
		}
		
		finalString += "_" + (String.valueOf(event.expenditure) + "_" + String.valueOf(event.revenue) + "_" + String.valueOf(event.numEvents) + "_" + String.valueOf(event.next) + "_" + String.valueOf(event.prev));
		
		return finalString;
	}
	
	public static String makeMailDeliveryStatsString(int numOfItems, double totalVolume, double totalWeight, double avDeliveryTime){
		return numOfItems + "_" + totalVolume + "_" + totalWeight + "_" + avDeliveryTime;
	}
}
