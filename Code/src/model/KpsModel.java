package model;

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
	
	public static void main(String[] args){
		new KpsModel();
	}
}
