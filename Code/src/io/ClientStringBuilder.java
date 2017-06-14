package io;

public class ClientStringBuilder {
	public static String requestMailCreationString(String origin, String destination, double weight, double volume, String priority, double cost, double routeCost, String day, double time){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "weight: " + weight + "\n"
				+ "volume: " + volume + "\n"
				+ "}";*/

		return origin + "_" + destination + "_" + weight + "_" + volume + "_" + priority + "_" + cost + "_" + routeCost + "_" + day + "_" + time;
	}

	public static String requestMailCreationRoutesString(String from, String to, String priority, double weight, double volume){
		return from + "_" + to + "_" + priority + "_" + weight + "_" + volume;
	}

	public static String requestLoginDetailsString(String username, String password){
		return username + "_" + password;
	}

	public static String requestCustomerPriceUpdateString(String origin, String destination, String priority, double pricePerGram, double pricePerCube){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "priority: " + priority + "\n"
				+ "pricePerGram: " + pricePerGram + "\n"
				+ "pricePerCube: " + pricePerCube + "\n"
				+ "}";*/

		return origin + "_" + destination + "_" + priority + "_" + pricePerGram + "_" + pricePerCube;

	}

	public static String requestTransportCostUpdateString(String origin, String destination, String company, String priority, double pricePerGram, double pricePerCube, String days, int period, double duration, double maxWeight, double maxVolume){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "company : " + company + "\n"
				+ "priority: " + priority + "\n"
				+ "pricePerGram: " + pricePerGram + "\n"
				+ "pricePerCube: " + pricePerCube + "\n"
				+ "day: " + day + "\n"
				+ "period: " + period + "\n"
				+ "duration: " + duration + "\n"
				+ "}";*/

		return origin + "_" + destination + "_" + company + "_" + priority + "_" + pricePerGram + "_" + pricePerCube + "_" + days + "_" + period + "_" + duration + "_" + maxWeight + "_" + maxVolume;
	}

	public static String requestTransportDiscontinuedString(String origin, String destination, String company, String priority){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "company : " + company + "\n"
				+ "priority: " + priority + "\n"
				+ "}";*/

		return origin + "_" + destination + "_" + company + "_" + priority;
	}
}
