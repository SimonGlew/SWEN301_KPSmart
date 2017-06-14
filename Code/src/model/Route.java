package model;

import java.util.List;

import model.KpsModel.Day;

public class Route {
	Location origin;
	List<TransportOption> options;
	Location destination;
	Day startDay;
	int startHour;
	
	double weight;
	double vol;

	double time;
	double cost;
	double routeCost;

	public Route(Location origin, Location destination, List<TransportOption> options, double weight, double vol, Day day, int hour){
		this.origin = origin;
		this.options = options;
		this.destination = destination;
		this.weight = weight;
		this.vol = vol;
		this.startDay = day;
		this.startHour = hour;
		
		time = calculateTime(startDay, startHour, origin, destination, options);

		for(TransportOption option: options){
			cost += option.getSegment().getVolCost(option.getPriority())*vol + option.getSegment().getWeightCost(option.getPriority())*weight;
			routeCost += option.getVolCost()*vol + option.getWeightCost()*weight;
		}

	}

	public String toString(){
		String s = String.format("Path for sending a package (weight=%.2f, vol=%.2f) from %s to %s:\n", weight, vol, origin.getName(), destination.getName());
		for(int i = 0; i < options.size(); i++){
			s += String.format("%d: From %s to %s, %s \n", i+1, options.get(i).getSegment().getOrigin().getName(), options.get(i).getSegment().getDestination().getName(), options.get(i).getTransportFirm());
		}
		s+= String.format("Total Cost: %.2f", cost);
		return s;
	}
	
	public static double calculateTime(Day startDay, int startHour, Location origin, Location destination, List<TransportOption> options){
		int day = startDay.ordinal();
		int hour = startHour;
		Location currentLocation = origin;
		int optionIndex = 0;
		double transportEnd = 0;
		boolean waiting = true;
		boolean inTransit = false;
		TransportOption nextOption = options.get(0);
		while(currentLocation != destination){
			if(waiting){
				if(nextOption.containsDayInt(day)){
					if(hour%nextOption.getFrequency()==0){
						transportEnd = hour + nextOption.getDuration();
						inTransit = true;
						waiting = false;
					}
				}
			}else if(inTransit){
				if(hour >= transportEnd){
					inTransit = false;
					waiting = true;
					currentLocation = nextOption.getSegment().getDestination();
					optionIndex++;
					if(optionIndex<options.size()){
						nextOption = options.get(optionIndex);
					}else{
						break;
					}
				}
			}
			hour++;
			if(hour%24==0){
				day++;
			}
			if(day > 6){
				day -= 7;
			}
		}		
		return hour - startHour;
	}

	public double getCost() {
		return cost;
	}
	
	public double getRouteCost() {
		return routeCost;
	}

	public double getTime() {
		return time;
	}
	
}
