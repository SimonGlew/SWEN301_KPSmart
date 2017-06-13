package io;

public class ServerStringBuilder {
	
	public static String makeNewRouteString(String to, String from){
		return to + " " + from;
	}
	
	public static String makeBusinessFigures(){
		return "";
	}
	
	public static String makeMailDeliveryString(double cheapestCost, int cheapestTime, double fastestCost, int fastestTime){
		return cheapestCost + " " + cheapestTime + " " + fastestCost + " " + fastestTime; 
	}
}
