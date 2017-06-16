package io;

import java.util.ArrayList;
import java.util.List;

import model.Location;

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
	
	public static String makeEventLogString(Object event){
		return "";
	}
	
	public static String makeMailDeliveryStatsString(int numOfItems, double totalVolume, double totalWeight, double avDeliveryTime){
		return numOfItems + "_" + totalVolume + "_" + totalWeight + "_" + avDeliveryTime;
	}
}
