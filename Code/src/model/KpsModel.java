package model;

import java.util.Calendar;

/**
 * Created by Jack on 6/3/2017.
 */

public class KpsModel {

	private RouteMap routeMap;

	public KpsModel(){
		routeMap = new RouteMap();
		addTestMapData();
	}

	private void addTestMapData(){
		//Dummy Locations
		routeMap.addLocation("Hastings", 0.00, 0.00);
		routeMap.addLocation("Wellington", 0.00, 0.00);
		routeMap.addLocation("Porirua", 0.00, 0.00);
		routeMap.addLocation("Karori", 0.00, 0.00, 45);
	}

	public static void println(String s){
		Calendar cal = Calendar.getInstance();
		System.out.printf("[%02d/%02d/%04d %02d:%02d:%02d] %s", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH),
				cal.get(Calendar.YEAR), cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), s);
	}

	public static void main(String[] args){
		new KpsModel();
	}
}
