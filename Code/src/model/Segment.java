package model;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Jack on 6/3/2017.
 */
public class Segment {
	private int id;
	private Location origin, destination;
	private List<TransportOption> options;

	public Segment(int id, Location origin, Location destination){
		this.id = id;
		this.origin = origin;
		this.destination = destination;
		options = new ArrayList<TransportOption>();
	}

	public void addTransportOption(){

	}

	public Location getDestination() {
		return destination;
	}

	public int getId(){
		return id;
	}

}
