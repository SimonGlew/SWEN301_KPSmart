package model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.KpsModel.Day;

/**
 * Created by Jack on 6/3/2017.
 */
public class Segment {
	private int id;
	private Map<Integer, Double> weightCosts;
	private Map<Integer, Double> volCosts;
	private Location origin, destination;
	private Map<Integer, TransportOption> options;

	public Segment(int id, Location origin, Location destination){
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		options = new HashMap<Integer, TransportOption>();
		weightCosts = new HashMap<Integer, Double>();
		volCosts = new HashMap<Integer, Double>();
		for(int i = 1; i <= 4; i++){
			weightCosts.put(i, 1d);
			volCosts.put(i, 1d);
		}
	}

	public Location getOrigin() {
		return origin;
	}

	public int addTransportOption(String firm, int priority, double weightCost, double volCost, double maxWeight, double maxVol, int frequency, double duration, List<Day> list){
		int id = 1;
		while(options.containsKey(id)){
			id++;
		}
		return addTransportOption(id, firm, priority, weightCost, volCost, maxWeight, maxVol, frequency, duration, list);
	}

	public int addTransportOption(int id, String firm, int priority, double weightCost, double volCost, double maxWeight, double maxVol, int frequency, double duration, List<Day> list){
		options.put(id, new TransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol, frequency, duration, this, list));
		KpsModel.println(String.format("Added transport option for segment %d, for %s with id: %d", this.id, firm, priority, id));
		return id;
	}

	public int updateTransportOption(String firm, int priority, double weightCost, double volCost, double maxWeight, double maxVol, int frequency, double duration, List<Day> list){
		int optionId = getTransportOptionId(firm, priority);
		options.put(id, new TransportOption(firm, priority, weightCost, volCost, maxWeight, maxVol, frequency, duration, this, list));
		KpsModel.println(String.format("Updated transport option for segment %d, for %s with id: %d", this.id, firm, priority, id));
		return optionId;
	}

	public Map<Integer, TransportOption> getTransportOptions(){
		return options;
	}

	public Location getDestination() {
		return destination;
	}

	public int getId(){
		return id;
	}

	public int getTransportOptionId(String company, int priority) {
		for(Entry<Integer, TransportOption> entry: options.entrySet()){
			if(entry.getValue().getTransportFirm().equals(company) && (entry.getValue().getPriority() == priority)){
				return entry.getKey();
			}
		}
		return -1;
	}

	public void setPrice(int priority, double weightCost, double volCost) {
		weightCosts.put(priority, weightCost);
		volCosts.put(priority, volCost);
	}

	public void discontinueRoute(String transportFirm, int priority) {
		List<Integer> toRemove = new ArrayList<Integer>();
		for(Entry<Integer, TransportOption> option: options.entrySet()){
			if(option.getValue().getTransportFirm().equals(transportFirm) && option.getValue().getPriority()==priority){
				toRemove.add(option.getKey());
			}
		}
		for(Integer option: toRemove){
			options.remove(option);
		}
	}

}
