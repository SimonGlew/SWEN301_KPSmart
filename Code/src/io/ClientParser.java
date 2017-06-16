package io;

import java.util.ArrayList;
import java.util.List;

import serverclient.ClientController;
import serverclient.Packet;

public class ClientParser {
	private ClientController controller;

	public ClientParser(ClientController controller){
		this.controller = controller;
	}

	public void parseMessage(Packet p){
		if(p.getType().equals(Codes.ServerAccountingFigures)){
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
			controller.successfullLogin(Boolean.parseBoolean(p.getInformation()));
		}else if(p.getType().equals(Codes.ConfirmationMailDelivery)){
			controller.successfullEvent(Codes.GUIMailDelivery);
		}else if(p.getType().equals(Codes.ConfirmationCustomerPriceUpdate)){
			controller.successfullEvent(Codes.GUICustomerPriceUpdate);
		}else if(p.getType().equals(Codes.DiscontinueRouteValid)){
			controller.successfullEvent(Codes.GUIDiscontinueRoute);
		}else if(p.getType().equals(Codes.DiscontinueRouteInvalid)){
			controller.failedEvent(Codes.GUIDiscontinueRouteInvalid);
		}else if(p.getType().equals(Codes.ConfirmationUpdateRoute)){
			controller.successfullEvent(Codes.GUIUpdatedRoute);
		}else if(p.getType().equals(Codes.ConfirmationMadeRoute)){
			controller.successfullEvent(Codes.GUIConfirmationMadeRoute);
		}else if(p.getType().equals(Codes.ConfirmationMadeCustomerRoute)){
			controller.successfullEvent(Codes.GUIConfirmationMadeCustomerRoute);
		}else if(p.getType().equals(Codes.ConfirmationUpdateCustomerRoute)){
			controller.successfullEvent(Codes.GUIUpdatedCustomerRoute);
		}else if(p.getType().equals(Codes.MailDeliveryStats)){
			parseServerMailDeliveryStats(p);
		}else if(p.getType().equals(Codes.CriticalRoutes)){
			parseCriticalRoutes(p);
		}else if(p.getType().equals(Codes.ServerFailedMailDeliveryRoutes)){
			controller.failedEvent(Codes.GUIServerFailedMailDeliveryRoutes);
		}else if(p.getType().equals(Codes.EventLog)){
			parseEventLog(p);
		}
	}
	
	public void parseEventLog(Packet p){
		
	}

	public void parseBusinessFigures(Packet p){
		String[] information = p.getInformation().split("_");
		
		double totalRevenue = Double.parseDouble(information[0]);
		double totalExpenditure = Double.parseDouble(information[1]);
		int totalNumberOfMailDeliveryEvents = Integer.parseInt(information[2]);
		int totalNumberOfCustomerPriceUpdateEvents = Integer.parseInt(information[3]);
		int totalNumberOfTransportCostUpdateEvents = Integer.parseInt(information[4]);
		int totalNumberOfTransportDiscontinuedEvents = Integer.parseInt(information[5]);
	}
	
	public void parseCriticalRoutes(Packet p){
		String[] information = p.getInformation().split("_");
		ArrayList<ArrayList<String>> criticalRoutes = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < information.length; i++){
			criticalRoutes.add(new ArrayList<String>());
			String[] info = information[i].split(",");
			for(int z = 0; z < info.length; z++){
				String in = info[z];
				criticalRoutes.get(i).add(in);
			}
		}
	}
	
	public void parseServerMailDeliveryStats(Packet p){
		String[] information = p.getInformation().split("_");
				
		int numOfItems = Integer.parseInt(information[0]);
		double totalVolume = Double.parseDouble(information[1]);
		double totalWeight = Double.parseDouble(information[2]);
		double avDeliveryTime = Double.parseDouble(information[3]);
	}

	public void parseMailDeliveryRoutes(Packet p){
		String[] information = p.getInformation().split("_");
		double cheapestCost = Double.parseDouble(information[0]);
		double cheapestRouteCost = Double.parseDouble(information[1]);
		int cheapestTime = Integer.parseInt(information[2]);
		double fastestCost = Double.parseDouble(information[3]);
		double fastestRouteCost = Double.parseDouble(information[4]);
		int fastestTime = Integer.parseInt(information[5]);
		
		controller.notifyDeliverOption(cheapestCost, cheapestRouteCost, cheapestTime, fastestCost, fastestRouteCost, fastestTime);
	}

	public void parseServerNewRoute(Packet p){
		String[] information = p.getInformation().split("_");
		for(int i = 0; i < information.length; i++){
			this.controller.addInLocation(information[i]);
		}
		System.out.println(this.controller.getLocations().length);
	}

	public void parseServerNewCompany(Packet p){
		String[] information = p.getInformation().split("_");
		for(int i = 0; i < information.length; i++){
			this.controller.addInCompany(information[i]);
		}
		System.out.println(this.controller.getCompanies().length);

	}
}
