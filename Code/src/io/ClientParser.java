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
		String[] s = p.getInformation().split("_");
		
		String eventType = s[0];
		String event = s[1];
		double expenditure = Double.parseDouble(s[2]);
		double revenue = Double.parseDouble(s[3]);
		int numEvents = Integer.parseInt(s[4]);
		boolean next = Boolean.parseBoolean(s[5]);
		boolean prev = Boolean.parseBoolean(s[6]);
				
		String[] eventArray = event.split(",");
		
		int id = Integer.parseInt(eventArray[0]);
		String username = eventArray[1];
		String date = eventArray[2];
		
		if(eventType.equals(Codes.LogMailDelivery)){			
			String day = eventArray[3];
			String from = eventArray[4];
			String to = eventArray[5];
			String priority = eventArray[6];
			double volume = Double.parseDouble(eventArray[7]);
			double weight = Double.parseDouble(eventArray[8]);
			double kpsCost = Double.parseDouble(eventArray[9]);
			double routeCost = Double.parseDouble(eventArray[10]);
			int hours = Integer.parseInt(eventArray[11]);
			
			controller.notifyLogMailDelivery(id, username, date, day, from, to, priority, volume, weight, kpsCost, routeCost, hours, expenditure, revenue, numEvents, next, prev);
			
		}else if(eventType.equals(Codes.LogCustomerUpdate)){
			String from = eventArray[3];
			String to = eventArray[4];
			String priority = eventArray[5];
			double volumeCost = Double.parseDouble(eventArray[6]);
			double weightCost = Double.parseDouble(eventArray[7]);
			
			controller.notifyLogCustomerUpdate(id, username, date, from, to, priority, volumeCost, weightCost, expenditure, revenue, numEvents, next, prev);
			
		}else if(eventType.equals(Codes.LogTransportUpdate)){			
			String company = eventArray[3];
			String to = eventArray[4];
			String from = eventArray[5];
			String priority = eventArray[6];
			double weight = Double.parseDouble(eventArray[7]);
			double volume = Double.parseDouble(eventArray[8]);
			double maxWeight = Double.parseDouble(eventArray[9]);
			double maxVolume = Double.parseDouble(eventArray[10]);
			int duration = Integer.parseInt(eventArray[11]);
			int frequency = Integer.parseInt(eventArray[12]);
			
			List<String> days = new ArrayList<String>();
			String[] d = eventArray[13].split("%");
			for(int i = 0; i < d.length; i++){
				days.add(d[i]);
			}
			
			controller.notifyLogTransportUpdate(id, username, date, company, to, from, priority, weight, volume, maxWeight, maxVolume, duration, frequency, days, expenditure, revenue, numEvents, next, prev);
			
		}else if(eventType.equals(Codes.LogTransportDiscontinue)){			
			String company = eventArray[3];
			String to = eventArray[4];
			String from = eventArray[5];
			String priority = eventArray[6];
			
			controller.notifyLogTransport(id, username, date, company, to, from, priority, expenditure, revenue, numEvents, next, prev);
		}
			
	}

	public void parseBusinessFigures(Packet p){
		String[] information = p.getInformation().split("_");
		
		double totalRevenue = Double.parseDouble(information[0]);
		double totalExpenditure = Double.parseDouble(information[1]);
		int totalNumberOfMailDeliveryEvents = Integer.parseInt(information[2]);
		int totalNumberOfCustomerPriceUpdateEvents = Integer.parseInt(information[3]);
		int totalNumberOfTransportCostUpdateEvents = Integer.parseInt(information[4]);
		int totalNumberOfTransportDiscontinuedEvents = Integer.parseInt(information[5]);
		
		controller.notifyBusinessFigures(totalRevenue, totalExpenditure, totalNumberOfMailDeliveryEvents, totalNumberOfCustomerPriceUpdateEvents, totalNumberOfTransportCostUpdateEvents, totalNumberOfTransportDiscontinuedEvents);
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
		
		controller.notifyCriticalRoutes(criticalRoutes);
	}
	
	public void parseServerMailDeliveryStats(Packet p){
		String[] information = p.getInformation().split("_");
				
		int numOfItems = Integer.parseInt(information[0]);
		double totalVolume = Double.parseDouble(information[1]);
		double totalWeight = Double.parseDouble(information[2]);
		double avDeliveryTime = Double.parseDouble(information[3]);
		
		controller.notifyMailDeliveryStats(numOfItems, totalVolume, totalWeight, avDeliveryTime);
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
