package model;

/**
 * Created by Jack on 6/3/2017.
 */
public class Location {
    private String name;
    private double latitude, longitude;
    private int id;
    
    public Location(String name, double latitude, double longitude, int id){
    	this.name = name;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.id = id;
    }

	public String getName() {
		return name;
	}
}
