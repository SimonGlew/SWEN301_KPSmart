package io;

import serverclient.ClientController;
import serverclient.Packet;

public class ClientParser {
	private ClientController controller;

	public ClientParser(ClientController controller){
		this.controller = controller;
	}

	public void parseMessage(Packet p){
		if(p.getType().equals(Codes.ServerBusinessFigures)){
			parseBusinessFigures(p);
		}else if(p.getType().equals(Codes.ServerMailDeliveryRoutes)){
			parseMailDeliveryRoutes(p);
		}else if(p.getType().equals(Codes.ServerNewRoute)){
			parseServerNewRoute(p);
		}
	}
	
	public void parseBusinessFigures(Packet p){
		
	}
	
	public void parseMailDeliveryRoutes(Packet p){
		String[] information = p.getInformation().split("//s+");
		double cheapestCost = Double.parseDouble(information[0]);
		int cheapestTime = Integer.parseInt(information[1]);
		double fastestCost = Double.parseDouble(information[2]);
		int fastestTime = Integer.parseInt(information[3]);
	}
	
	public void parseServerNewRoute(Packet p){
		String[] information = p.getInformation().split("//s+");
		for(int i = 0; i < information.length; i++){
			this.controller.addInLocation(information[i]);
		}
	}
}
