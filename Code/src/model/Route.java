package model;

import java.util.List;

public class Route {
	Location origin;
	List<TransportOption> options;
	Location destination;

	double weight;
	double vol;

	int time;
	double cost;

	public Route(Location origin, Location destination, List<TransportOption> options, double weight, double vol){
		this.origin = origin;
		this.options = options;
		this.destination = destination;
		this.weight = weight;
		this.vol = vol;

		for(TransportOption option: options){
			cost += option.getSegment().getVolCost(option.getPriority())*vol + option.getSegment().getWeightCost(option.getPriority())*weight;
		}

		time = 40;

	}

	public String toString(){
		String s = String.format("Path for sending a package (weight=%.2f, vol=%.2f) from %s to %s:\n", weight, vol, origin.getName(), destination.getName());
		for(int i = 0; i < options.size(); i++){
			s += String.format("%d: From %s to %s, %s \n", i+1, options.get(i).getSegment().getOrigin().getName(), options.get(i).getSegment().getDestination().getName(), options.get(i).getTransportFirm());
		}
		s+= String.format("Total Cost: %.2f", cost);
		return s;
	}

	public double getCost() {
		return cost;
	}

	public int getTime() {
		return time;
	}
}
