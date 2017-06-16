package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

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

	public List<String> getAllLocations() {
		List<String> allLocations = new ArrayList<String>();
		for (Location loc : locations.values()) {
			allLocations.add(loc.getName());
		}
		return allLocations;
	}

	public List<String> getAllCompanies() {
		List<String> allCompanies = new ArrayList<String>();
		for (Segment seg : segments.values()) {
			for (TransportOption option : seg.getTransportOptions().values()) {
				if (!allCompanies.contains(option.getTransportFirm())) {
					allCompanies.add(option.getTransportFirm());
				}
			}
		}
		return allCompanies;
	}

	public int addLocation(String name) {
		int id = 1;
		while (locations.containsKey(id)) {
			id++;
		}
		return addLocation(name, id);
	}

	public int addLocation(String name, int id) {
		if (locations.containsKey(id)) {
			KpsModel.println("Failed to add location, id " + id + " already in use.");
			return -1;
		} else {
			locations.put(id, new Location(name, id));
			KpsModel.println(String.format("Added location %s with id: %d", name, id));
			return id;
		}
	}

	public int getLocationId(String name) {
		for (Entry<Integer, Location> entry : locations.entrySet()) {
			if (entry.getValue().getName().equalsIgnoreCase(name)) {
				return entry.getKey();
			}
		}
		return -1;
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
			double maxWeight, double maxVol, int frequency, double duration, List<Day> list) {
		return segments.get(segId).addTransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol, frequency,
				duration, list);
	}

	public int updateTransportOption(int segId, String firm, int priority, double weightCost, double volCost,
			double maxWeight, double maxVol, int frequency, double duration, List<Day> list) {
		return segments.get(segId).updateTransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol,
				frequency, duration, list);
	}

	public Route findCheapestRoute(int originId, int destinationId, double weight, double vol, int priority, Day day, int hour) {
		Location origin = locations.get(originId);
		Location destination = locations.get(destinationId);
		Set<Location> visited = new HashSet<Location>();
		Queue<SearchNode> fringe = new PriorityQueue<SearchNode>(new Comparator<SearchNode>() {
			@Override
			public int compare(SearchNode o1, SearchNode o2) {
				if (o1.costSoFar > o2.costSoFar) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		fringe.offer(new SearchNode(origin, null, null, 0));
		Map<Location, PathSegment> pathSegments = new HashMap<Location, PathSegment>();
		while (!fringe.isEmpty()) {
			SearchNode node = fringe.poll();
			if (!visited.contains(node.loc)) {
				pathSegments.put(node.loc, new PathSegment(node.from, node.option));
				visited.add(node.loc);
				if (node.loc == destination) {
					List<TransportOption> path = new ArrayList<TransportOption>();
					Location curLoc = destination;
					while (curLoc != origin) {
						PathSegment pathseg = pathSegments.get(curLoc);
						path.add(0, pathseg.option);
						curLoc = pathseg.from;
					}
					Route route = new Route(origin, destination, path, weight, vol, day, hour);
					return route;
				}
				for (Segment segment : node.loc.getSegsOut()) {
					if (!visited.contains(segment.getDestination())) {
						for (TransportOption option : segment.getTransportOptions().values()) {
							if (weight <= option.getMaxWeight() && vol <= option.getMaxVol() && priority == option.getPriority()){
								fringe.offer(new SearchNode(segment.getDestination(), node.loc, option,
									node.costSoFar + (weight * segment.getWeightCost(priority)) + (vol * segment.getVolCost(priority))));
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public Route findFastestRoute(int originId, int destinationId, double weight, double vol, int priority, Day day, int hour) {
		Location origin = locations.get(originId);
		Location destination = locations.get(destinationId);
		Set<Location> visited = new HashSet<Location>();
		Queue<SearchNode> fringe = new PriorityQueue<SearchNode>(new Comparator<SearchNode>() {
			@Override
			public int compare(SearchNode o1, SearchNode o2) {
				if (o1.costSoFar > o2.costSoFar) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		fringe.offer(new SearchNode(origin, null, null, 0));
		Map<Location, PathSegment> pathSegments = new HashMap<Location, PathSegment>();
		while (!fringe.isEmpty()) {
			SearchNode node = fringe.poll();
			if (!visited.contains(node.loc)) {
				pathSegments.put(node.loc, new PathSegment(node.from, node.option));
				visited.add(node.loc);
				if (node.loc == destination) {
					List<TransportOption> path = new ArrayList<TransportOption>();
					Location curLoc = destination;
					while (curLoc != origin) {
						PathSegment pathseg = pathSegments.get(curLoc);
						path.add(0, pathseg.option);
						curLoc = pathseg.from;
					}
					Route route = new Route(origin, destination, path, weight, vol, day, hour);
					return route;
				}
				for (Segment segment : node.loc.getSegsOut()) {
					if (!visited.contains(segment.getDestination())) {
						for (TransportOption option : segment.getTransportOptions().values()) {
							if (weight <= option.getMaxWeight() && vol <= option.getMaxVol() && priority == option.getPriority()){
								
								List<TransportOption> options = new ArrayList<TransportOption>();
								options.add(0, option);
								Location curLoc = node.loc;
								while (curLoc != origin) {
									PathSegment pathseg = pathSegments.get(curLoc);
									options.add(0, pathseg.option);
									curLoc = pathseg.from;
								}
								
								double cost = Route.calculateTime(day, hour, origin, option.getSegment().getDestination(), options);
								fringe.offer(new SearchNode(segment.getDestination(), node.loc, option, cost));
							}
						}
					}
				}
			}
		}
		return null;
	}

	private class SearchNode {
		Location loc;
		Location from;
		TransportOption option;
		double costSoFar;

		public SearchNode(Location loc, Location from, TransportOption option, double costSoFar) {
			this.loc = loc;
			this.costSoFar = costSoFar;
			this.from = from;
			this.option = option;
		}
	}

	private class PathSegment {
		Location from;
		TransportOption option;

		public PathSegment(Location from, TransportOption option) {
			this.from = from;
			this.option = option;
		}
	}

	public Segment getSegment(int segmentId) {
		return segments.get(segmentId);
	}

	public void updateSegmentPrice(int segmentId, int priority, double weightCost, double volCost) {
		segments.get(segmentId).setPrice(priority, weightCost, volCost);
	}

	public void discontinueTransportOption(int segmentId, String company, int priority) {
		segments.get(segmentId).discontinueRoute(company, priority);
		KpsModel.println(String.format("Discontinued transport route from %s to %s with company %s and priority %d",
				segments.get(segmentId).getOrigin().getName(), segments.get(segmentId).getDestination().getName(),
				company, priority));
	}
	
	public void printMap(){
		System.out.println("Entire Map:");
		for(Entry<Integer, Location> entry: locations.entrySet()){
			System.out.printf("  Location %d: %s\n", entry.getKey(), entry.getValue().getName());
			for(Segment seg: entry.getValue().getSegsOut()){
				System.out.printf("  - Segment %d to %s\n", seg.getId(), seg.getDestination().getName());
			}
		}
	}

	public Collection<Segment> getSegments() {
		return segments.values();
	}

}
