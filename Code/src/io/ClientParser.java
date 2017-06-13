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
		}else if(p.getType().equals(Codes.ServerNewRoute) || p.getType().equals(Codes.ServerRouteList)){
			parseServerNewRoute(p);
		}else if(p.getType().equals(Codes.ServerNewCompany) || p.getType().equals(Codes.ServerCompanyList)){
			parseServerNewCompany(p);
		}else if(p.getType().equals(Codes.loginInvalid)){
			//Invalid Login
		}else if(p.getType().equals(Codes.loginValid)){
			//Valid Login
		}else if(p.getType().equals(Codes.ConfirmationMailDelivery)){
			//Mail delivery was a success
		}else if(p.getType().equals(Codes.ConfirmationCustomerPriceUpdate)){
			//Customer price update was a success
		}else if(p.getType().equals(Codes.DiscontinueRouteValid)){
			//Discontinued Route Valid
		}else if(p.getType().equals(Codes.DiscontinueRouteInvalid)){
			//Discontinued Route invalid
		}
	}
	
	public void parseBusinessFigures(Packet p){
		
	}
	
	public void parseMailDeliveryRoutes(Packet p){
		String[] information = p.getInformation().split("\\s+");
		double cheapestCost = Double.parseDouble(information[0]);
		int cheapestTime = Integer.parseInt(information[1]);
		double fastestCost = Double.parseDouble(information[2]);
		int fastestTime = Integer.parseInt(information[3]);
	}
	
	public void parseServerNewRoute(Packet p){
		String[] information = p.getInformation().split("\\s+");
		for(int i = 0; i < information.length; i++){
			this.controller.addInLocation(information[i]);
		}
	}
	
	public void parseServerNewCompany(Packet p){
		String[] information = p.getInformation().split("\\s+");
		for(int i = 0; i < information.length; i++){
			this.controller.addInCompany(information[i]);
		}
	}
}
