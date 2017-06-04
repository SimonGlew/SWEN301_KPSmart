package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jack on 6/3/2017.
 */
public class RouteMap {
	
	private Map<Integer, Location> locations;
	private Map<Integer, Segment> segments;
	
	public RouteMap(){
		locations = new HashMap<Integer, Location>();
		segments = new HashMap<Integer, Segment>();
	}
	
	public boolean addLocation(String name, double latitude, double longitude, int id){
		if(locations.containsKey(id)){
			KpsModel.println("Failed to add location, id " + id + " already in use.");
			return false;
		}else{
			locations.put(id, new Location(name, latitude, longitude, id));
			KpsModel.println(String.format("Added location %s (%.2f, %.2f) with id: %d\n", name, latitude, longitude, id));
			return true;
		}
	}
	
	public int addLocation(String name, double latitude, double longitude){
		int id = 1;
		while(locations.containsKey(id)) {
			id++;
		}
		locations.put(id,  new Location(name, latitude, longitude, id));
		System.out.printf("Added location %s (%.2f, %.2f) with id: %d\n", name, latitude, longitude, id);
		return id;
	}
	
	public int addSegment(int id, int originId, int destinationId){
		
		segments.put(id, new Segment(id, locations.get(originId), locations.get(destinationId)));
		System.out.printf("Added segment from %s to %s with id: %d\n", locations.get(originId).getName(), locations.get(destinationId).getName(), id);
		return id;
	}
}
