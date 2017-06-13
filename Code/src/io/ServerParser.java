package io;

import serverclient.Packet;
import serverclient.Server;
import io.Codes;
import model.KpsModel;

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
		String[] temp = p.getType().split(".");
		if(temp[0].equals(Codes.EventSubString)){
			parseEvent(p);
		}else if(p.getType().equals(Codes.ClientGetRoutesMailDelivery)){
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
		String[] information = p.getInformation().split("//s+");
		
		String username = information[0];
		String password = information[1];
	}
	
	public void parseClientGetRoutesMailDelivery(Packet p){
		String[] information = p.getInformation().split("//s+");
		
		String from = information[0];
		String to = information[1];
		String priority = information[2];
		double weight = Double.parseDouble(information[3]);
		double volume = Double.parseDouble(information[4]);
	}
	
	public void parseMailCreation(Packet p){
		String[] s = p.getInformation().split("\\s+");

		String origin = s[0];
		String destination = s[1];
		double weight = Double.parseDouble(s[2]);
		double volume = Double.parseDouble(s[3]);
		double cost = Double.parseDouble(s[4]);
		String day = s[5];

	}

	public void parseTransportPriceUpdate(Packet p){
		String[] s = p.getInformation().split("\\s+");

		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		String priority = s[3];
		double pricePerGram = Double.parseDouble(s[4]);
		double pricePerCube = Double.parseDouble(s[5]);
		String day = s[6];
		double period = Double.parseDouble(s[6]);
		double duration = Double.parseDouble(s[7]);

	}

	

	public void parseCustomerPriceUpdate(Packet p){
		String[] s = p.getInformation().split("\\s+");

		String origin = s[0];
		String destination = s[1];
		String priority = s[2];
		double pricePerGram = Double.parseDouble(s[3]);
		double pricePerCube = Double.parseDouble(s[4]);

	}

	public void parseTransportDiscontinue(Packet p){
		String[] s = p.getInformation().split("\\s+");

		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		String priority = s[3];
	}
	
	public void broadcastValidLogin(){
		this.server.broadcast(new Packet(Codes.loginValid, Codes.BroadcastSingle, null), this.clientId);
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
