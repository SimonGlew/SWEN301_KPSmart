package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 6/3/2017.
 */
public class Location {
    private String name;
    private double latitude, longitude;
    private int id;
    private List<Segment> segsOut;

    public Location(String name, double latitude, double longitude, int id){
    	this.name = name;
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.id = id;
    	segsOut = new ArrayList<Segment>();
    }

	public String getName() {
		return name;
	}

	public void addSegOut(Segment s){
		segsOut.add(s);
	}

	public Segment getSegToLocation(Location dest){
		for(Segment s: segsOut){
			if(s.getDestination() == dest){
				return s;
			}
		}
		return null;
	}
}
