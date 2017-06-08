package io;

public class ClientStringBuilder {
	public static String requestMailCreationString(String origin, String destination, double weight, double volume){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "weight: " + weight + "\n"
				+ "volume: " + volume + "\n"
				+ "}";*/

		return origin + " " + destination + " " + weight + " " + volume;
	}

	public static String requestCustomerPriceUpdateString(String origin, String destination, String priority, double pricePerGram, double pricePerCube){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "priority: " + priority + "\n"
				+ "pricePerGram: " + pricePerGram + "\n"
				+ "pricePerCube: " + pricePerCube + "\n"
				+ "}";*/

		return origin + " " + destination + " " + priority + " " + pricePerGram + " " + pricePerCube;

	}

	public static String requestTransportCostUpdateString(String origin, String destination, String company, String priority, double pricePerGram, double pricePerCube, String day, double period, double duration){
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

		return origin + " " + destination + " " + company + " " + priority + " " + pricePerGram + " " + pricePerCube + " " + day + " " + period + " " + duration;
	}

	public static String requestTransportDiscontinuedString(String origin, String destination, String company, String priority){
		/*return "{\n"
				+ "origin: " + origin + "\n"
				+ "destination: " + destination + "\n"
				+ "company : " + company + "\n"
				+ "priority: " + priority + "\n"
				+ "}";*/

		return origin + " " + destination + " " + company + " " + priority;
	}
}
