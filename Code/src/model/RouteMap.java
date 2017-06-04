package model;

import java.util.HashMap;
import java.util.Map;
import model.KpsModel.Day;

/**
 * Created by Jack on 6/3/2017.
 */
public class RouteMap {

	private Map<Integer, Location> locations;
	private Map<Integer, Segment> segments;

	public RouteMap() {
		locations = new HashMap<Integer, Location>();
		segments = new HashMap<Integer, Segment>();
	}

	public int addLocation(String name, double latitude, double longitude) {
		int id = 1;
		while (locations.containsKey(id)) {
			id++;
		}
		return addLocation(name, latitude, longitude, id);
	}

	public int addLocation(String name, double latitude, double longitude, int id) {
		if (locations.containsKey(id)) {
			KpsModel.println("Failed to add location, id " + id + " already in use.");
			return -1;
		} else {
			locations.put(id, new Location(name, latitude, longitude, id));
			KpsModel.println(
					String.format("Added location %s (%.2f, %.2f) with id: %d", name, latitude, longitude, id));
			return id;
		}
	}

	public int addSegment(int originId, int destinationId) {
		int id = 1;
		while (segments.containsKey(id)) {
			id++;
		}
		return addSegment(id, originId, destinationId);
	}

	public int addSegment(int id, int originId, int destinationId) {
		if (segments.containsKey(id)) {
			KpsModel.println(String.format("Error: Cannot add segment as id %d is already in use.", id));
			return -1;
		}
		if (!locations.containsKey(originId)) {
			KpsModel.println(
					String.format("Error: Cannot add segment as origin location with id %d does not exist.", originId));
			return -1;
		}
		if (getSegmentIdFrom(originId, destinationId) != -1) {
			KpsModel.println(String.format(
					"Error: Cannot add segment as origin location with id %d already has segment to destination with id %d.",
					originId, destinationId));
			return -1;
		}
		if (!locations.containsKey(destinationId)) {
			KpsModel.println(String.format(
					"Error: Cannot add segment as destination location with id %d does not exist.", destinationId));
			return -1;
		}
		segments.put(id, new Segment(id, locations.get(originId), locations.get(destinationId)));
		locations.get(originId).addSegOut(segments.get(id));
		KpsModel.println(String.format("Added segment from %s to %s with id: %d", locations.get(originId).getName(),
				locations.get(destinationId).getName(), id));
		return id;
	}

	public int getSegmentIdFrom(int originId, int destinationId) {
		if (!locations.containsKey(originId)) {
			return -1;
		}
		if (!locations.containsKey(destinationId)) {
			return -1;
		}
		Segment s = locations.get(originId).getSegToLocation(locations.get(destinationId));
		if (s == null) {
			return -1;
		}
		return s.getId();
	}

	public int addTransportOption(int segId, String firm, int priority, double weightCost, double volCost,
			double maxWeight, double maxVol, int frequency, int duration, Day... days) {
		return segments.get(segId).addTransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol, frequency,
				duration, days);
	}
}
