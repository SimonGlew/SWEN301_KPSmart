package io;

import java.util.ArrayList;
import java.util.List;

import model.Location;

public class ServerStringBuilder {

	public static String makeNewRouteString(String to, String from) {
		return to + "_" + from;
	}

	public static String makeBusinessFigures() {
		return "";
	}

	public static String makeMailDeliveryString(double cheapestCost, int cheapestTime, double fastestCost,
			int fastestTime) {
		return cheapestCost + "_" + cheapestTime + "_" + fastestCost + "_" + fastestTime;
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
}
