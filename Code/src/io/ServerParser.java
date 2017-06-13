package io;

import serverclient.Packet;
import serverclient.Server;

import java.util.ArrayList;
import java.util.List;

import io.Codes;
import model.KpsModel;
import model.KpsModel.Day;
import model.Location;

public class ServerParser {
	private KpsModel model;
	private Server server;
	private int clientId;

	public ServerParser(KpsModel model, Server server, int clientId){
		this.model = model;
		this.server = server;
		this.clientId = clientId;
	}

	public void parseMessage(Packet p){
		String[] temp = p.getType().split("\\.");

		if(temp[0].equals(Codes.EventSubString)){
			parseEvent(p);
		}else if(p.getType().equals(Codes.ClientGetRoutesMailDelivery)){
			System.out.println(p.getInformation());
			parseClientGetRoutesMailDelivery(p);
		}else if(p.getType().equals(Codes.loginDetails)){
			parseClientLoginDetails(p);
		}
	}
	
	public void parseEvent(Packet p){
		if(p.getType().equals(Codes.TransportPriceUpdate)){
			parseTransportPriceUpdate(p);
		}else if(p.getType().equals(Codes.CustomerPriceUpdate)){
			parseCustomerPriceUpdate(p);
		}else if(p.getType().equals(Codes.MailCreation)){
			parseMailCreation(p);
		}else if(p.getType().equals(Codes.TransportDiscontinue)){
			parseTransportDiscontinue(p);
		}
	}
	
	public void parseClientLoginDetails(Packet p){
		String[] information = p.getInformation().split("_");
		
		String username = information[0];
		String password = information[1];
	}
	
	public void parseClientGetRoutesMailDelivery(Packet p){
		System.out.println(p.getInformation());
		String[] information = p.getInformation().split("_");
		
		String from = information[0];
		String to = information[1];
		String priority = information[2];
		double weight = Double.parseDouble(information[3]);
		double volume = Double.parseDouble(information[4]);
	}
	
	public void parseMailCreation(Packet p){
		String[] s = p.getInformation().split("_");

		String origin = s[0];
		String destination = s[1];
		double weight = Double.parseDouble(s[2]);
		double volume = Double.parseDouble(s[3]);
		double cost = Double.parseDouble(s[4]);
		String day = s[5];

	}

	public void parseTransportPriceUpdate(Packet p){
		String[] s = p.getInformation().split("_");
		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		//TODO: PARSE ACTUAL PRIORITIES TO NUMBERS
		int priority = 1;
		double pricePerGram = Double.parseDouble(s[4]);
		double pricePerCube = Double.parseDouble(s[5]);
		List<Day> days = new ArrayList<Day>();
		String[] dayArray = s[6].split("\\s+");
		for(String day: dayArray){
			days.add(model.parseDay(day));
		}
		//TODO: Should this be an int or double? Model is set up for ints, this is probably wrong
		int frequency = (int)Double.parseDouble(s[7]);
		double duration = Double.parseDouble(s[8]);
		//TODO: RECEIVE MAXVOL AND MAXWEIGHT FROM CLIENT
		double maxWeight = 1000;
		double maxVol = 1000;
		String message = model.newTransportPriceUpdate(origin, destination, company, priority, pricePerGram, pricePerCube, maxWeight, maxVol, days, frequency, duration);
		//TODO: Return message to client
	}

	public void parseCustomerPriceUpdate(Packet p){
		String[] s = p.getInformation().split("_");
		String origin = s[0];
		String destination = s[1];
		//TODO: PARSE ACTUAL PRIORITIES TO NUMBERS
		int priority = 1;
		double pricePerGram = Double.parseDouble(s[3]);
		double pricePerCube = Double.parseDouble(s[4]);
		model.newCustomerPriceUpdate(origin, destination, priority, pricePerGram, pricePerCube);
		//TODO: Return message to client
	}

	public void parseTransportDiscontinue(Packet p){
		String[] s = p.getInformation().split("_");

		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		//TODO: PARSE ACTUAL PRIORITY TO NUMBER
		int priority = 1;
		String message = model.newTransportDiscontinue(origin, destination, company, priority);
		//TODO: Return message to client
	}
	
	public void broadcastValidLogin(){
		this.server.broadcast(new Packet(Codes.loginValid, Codes.BroadcastSingle, null), this.clientId);
		this.server.broadcast(new Packet(Codes.ServerCompanyList, Codes.BroadcastSingle, ServerStringBuilder.makeCompanyListString(new ArrayList<String>())), this.clientId);
		this.server.broadcast(new Packet(Codes.ServerRouteList, Codes.BroadcastSingle, ServerStringBuilder.makeLocationListString(new ArrayList<Location>())), this.clientId);
	}
	
	public void broadcastInvalidLogin(){
		this.server.broadcast(new Packet(Codes.loginInvalid, Codes.BroadcastSingle, null), this.clientId);
	}
	
	public void sendConfirmationMailDelivery(){
		this.server.broadcast(new Packet(Codes.ConfirmationMailDelivery, Codes.BroadcastSingle, null), this.clientId);
	}
	
	public void sendConfirmationCustomerPriceUpdate(){
		this.server.broadcast(new Packet(Codes.ConfirmationCustomerPriceUpdate, Codes.BroadcastSingle, null), this.clientId);
	}
	
	public void sendDiscontinueRouteValid(){
		this.server.broadcast(new Packet(Codes.DiscontinueRouteValid, Codes.BroadcastSingle, null), this.clientId);
	}
	
	public void sendDiscontinueRouteInvalid(){
		this.server.broadcast(new Packet(Codes.DiscontinueRouteInvalid, Codes.BroadcastSingle, null), this.clientId);
	}
	
	public void broadcastBusinessFigures(double totalRevenue, double totalExpenditure, int totalNumberOfEvents, int totalNumberOfMail, double totalWeightOfMail, double totalVolumeOfMail){
		this.server.broadcast(new Packet(Codes.ServerBusinessFigures, Codes.BroadcastAll, ServerStringBuilder.makeBusinessFigures()),  -1);
	}
	
	public void broadcastRoute(String to, String from){
		this.server.broadcast(new Packet(Codes.ServerNewRoute, Codes.BroadcastAll, ServerStringBuilder.makeNewRouteString(to, from)), -1);
	}
	
	public void broadcastRoutesMailDelivery(double cheapestCost, int cheapestTime, double fastestCost, int fastestTime){
		this.server.broadcast(new Packet(Codes.ServerMailDeliveryRoutes, Codes.BroadcastSingle, ServerStringBuilder.makeMailDeliveryString(cheapestCost, cheapestTime, fastestCost, fastestTime)), this.clientId);
	}
}
