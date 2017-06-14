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
			controller.failedLogin();
		}else if(p.getType().equals(Codes.loginValid)){
			controller.successfullLogin();
		}else if(p.getType().equals(Codes.ConfirmationMailDelivery)){
			//Mail delivery was a success
		}else if(p.getType().equals(Codes.ConfirmationCustomerPriceUpdate)){
			//Customer price update was a success
		}else if(p.getType().equals(Codes.DiscontinueRouteValid)){
			//Discontinued Route Valid
		}else if(p.getType().equals(Codes.DiscontinueRouteInvalid)){
			//Discontinued Route invalid
		}else if(p.getType().equals(Codes.ConfirmationUpdateRoute)){
			//Updated route with transport price Update
		}else if(p.getType().equals(Codes.ConfirmationMadeRoute)){
			//Made new Route with transport price Update
		}else if(p.getType().equals(Codes.ConfirmationMadeCustomerRoute)){
			//Made new Route with Customer Price Update
		}else if(p.getType().equals(Codes.ConfirmationUpdateCustomerRoute)){
			//Updated route with customer price update
		}else if(p.getType().equals(Codes.DiscontinueRouteValid)){
			//Valid discontinue Route
		}else if(p.getType().equals(Codes.DiscontinueRouteInvalid)){
			//Invalid discontinue route
		}
	}

	public void parseBusinessFigures(Packet p){

	}

	public void parseMailDeliveryRoutes(Packet p){
		String[] information = p.getInformation().split("_");
		double cheapestCost = Double.parseDouble(information[0]);
		int cheapestTime = Integer.parseInt(information[1]);
		double fastestCost = Double.parseDouble(information[2]);
		int fastestTime = Integer.parseInt(information[3]);
	}

	public void parseServerNewRoute(Packet p){
		String[] information = p.getInformation().split("_");
		for(int i = 0; i < information.length; i++){
			this.controller.addInLocation(information[i]);
		}
	}

	public void parseServerNewCompany(Packet p){
		String[] information = p.getInformation().split("_");
		for(int i = 0; i < information.length; i++){
			this.controller.addInCompany(information[i]);
		}
	}
}
