package model;

import java.util.Map;
import java.util.HashMap;
import model.KpsModel.Day;

/**
 * Created by Jack on 6/3/2017.
 */
public class Segment {
	private int id;
	private Location origin, destination;
	private Map<Integer, TransportOption> options;

	public Segment(int id, Location origin, Location destination){
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		options = new HashMap<Integer, TransportOption>();
	}

	public int addTransportOption(String firm, int priority, double weightCost, double volCost, double maxWeight, double maxVol, int frequency, int duration, Day... days){
		int id = 1;
		while(options.containsKey(id)){
			id++;
		}
		return addTransportOption(id, firm, priority, weightCost, volCost, maxWeight, maxVol, frequency, duration, days);
	}

	public int addTransportOption(int id, String firm, int priority, double weightCost, double volCost, double maxWeight, double maxVol, int frequency, double duration, Day... days){
		options.put(id, new TransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol, frequency, duration, days));
		KpsModel.println(String.format("Added transport option for segment %d, for %s with id: %d", this.id, firm, priority, id));
		return id;
	}

	public Location getDestination() {
		return destination;
	}

	public int getId(){
		return id;
	}

}
