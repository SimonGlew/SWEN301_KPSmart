package io;

import serverclient.Packet;
import serverclient.Server;
import users.StaffMember;

import java.util.ArrayList;
import java.util.List;

import io.Codes;
import model.KpsModel;
import model.KpsModel.Day;
import model.Location;
import model.Route;

public class ServerParser {
	private KpsModel model;
	private Server server;
	private int clientId;
	private String username;

	public ServerParser(KpsModel model, Server server, int clientId){
		this.model = model;
		this.server = server;
		this.clientId = clientId;

	}

	public Packet parseMessage(Packet p){
		String[] temp = p.getType().split("\\.");
		if(temp[0].equals(Codes.EventSubString)){
			return parseEvent(p);
		}else if(p.getType().equals(Codes.ClientGetRoutesMailDelivery)){
			return parseClientGetRoutesMailDelivery(p);
		}else if(p.getType().equals(Codes.loginDetails)){
			return parseClientLoginDetails(p);
		}else{
			return null;
		}
	}

	public Packet parseEvent(Packet p){
		if(p.getType().equals(Codes.TransportPriceUpdate)){
			return parseTransportPriceUpdate(p);
		}else if(p.getType().equals(Codes.CustomerPriceUpdate)){
			return parseCustomerPriceUpdate(p);
		}else if(p.getType().equals(Codes.MailCreation)){
			return parseMailCreation(p);
		}else if(p.getType().equals(Codes.TransportDiscontinue)){
			return parseTransportDiscontinue(p);
		}else{
			return null;
		}
	}

	public Packet parseClientLoginDetails(Packet p){
		String[] information = p.getInformation().split("_");

		String username = information[0];
		String password = information[1];

		StaffMember staff = model.validateLogin(username, password);

		if(staff == null){
			return this.broadcastInvalidLogin();
		}
		this.username = username;
		return this.broadcastValidLogin(staff.isManager());
	}

	public Packet parseClientGetRoutesMailDelivery(Packet p){
		String[] information = p.getInformation().split("_");

		String from = information[0];
		String to = information[1];
		int priority = parsePriority(information[2]);
		double weight = Double.parseDouble(information[3]);
		double volume = Double.parseDouble(information[4]);
		Route cheapest = model.getCheapestRoute(from, to, priority, weight, volume);
		Route fastest = model.getFastestRoute(from, to, priority, weight, volume);
		return broadcastRoutesMailDelivery(cheapest.getCost(), cheapest.getRouteCost(), (int)cheapest.getTime(), fastest.getCost(), fastest.getRouteCost(), (int)fastest.getTime());
	}

	public Packet parseMailCreation(Packet p){
		String[] s = p.getInformation().split("_");
		
		String origin = s[0];
		String destination = s[1];
		double weight = Double.parseDouble(s[2]);
		double volume = Double.parseDouble(s[3]);
		int priority = parsePriority(s[4]);
		double kpsCost = Double.parseDouble(s[5]);
		double routeCost = Double.parseDouble(s[6]);
		Day day = model.parseDay(s[7]);
		double hours = Double.parseDouble(s[8]);
		model.newMailDelivery(username, day, origin, destination, weight, volume, priority, kpsCost, routeCost, hours);
		return sendConfirmationMailDelivery();
	}

	public Packet parseTransportPriceUpdate(Packet p){
		String[] s = p.getInformation().split("_");
		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		int priority = parsePriority(s[3]);
		double pricePerGram = Double.parseDouble(s[4]);
		double pricePerCube = Double.parseDouble(s[5]);
		List<Day> days = new ArrayList<Day>();
		String[] dayArray = s[6].split("\\s+");
		for(String day: dayArray){
			days.add(model.parseDay(day));
		}
		int frequency = Integer.parseInt(s[7]);
		double duration = Double.parseDouble(s[8]);
		double maxWeight = Double.parseDouble(s[9]);
		double maxVol = Double.parseDouble(s[10]);
		String returnString = model.newTransportPriceUpdate(username, origin, destination, company, priority, pricePerGram, pricePerCube, maxWeight, maxVol, days, frequency, duration);

		if(returnString.equals(Codes.ConfirmationMadeRoute)){
			return broadcastConfirmationMadeRoute(destination, origin);
		}else{
			return broadcastTransportRouteUpdate();
		}
	}

	public Packet parseCustomerPriceUpdate(Packet p){
		String[] s = p.getInformation().split("_");
		String origin = s[0];
		String destination = s[1];
		int priority = parsePriority(s[2]);
		double pricePerGram = Double.parseDouble(s[3]);
		double pricePerCube = Double.parseDouble(s[4]);
		String returnString = model.newCustomerPriceUpdate(username, origin, destination, priority, pricePerGram, pricePerCube);

		if(returnString.equals(Codes.ConfirmationMadeCustomerRoute)){
			return broadcastConfirmationMadeCustomerRoute(destination, origin);
		}else{
			return broadcastConfirmationUpdateCustomerRoute();
		}
	}

	public Packet parseTransportDiscontinue(Packet p){
		String[] s = p.getInformation().split("_");

		String origin = s[0];
		String destination = s[1];
		String company = s[2];
		int priority = parsePriority(s[3]);
		String message = model.newTransportDiscontinue(username, origin, destination, company, priority);

		if(message.equals(Codes.DiscontinueRouteValid)){
			return this.broadcastConfirmationDiscontinueRouteValid();
		}else if(message.equals(Codes.DiscontinueRouteInvalid)){
			return this.broadcastConfirmationDiscontinueRouteInvalid();
		}else{
			return null;
		}
	}

	public int parsePriority(String priority){
		if(priority.equals(Codes.Priorities.InternationalAir)){
			return 1;
		}else if(priority.equals(Codes.Priorities.InternationalStandard)){
			return 2;
		}else if(priority.equals(Codes.Priorities.DomesticStandard)){
			return 3;
		}
		return 4;
	}

	public Packet broadcastValidLogin(boolean isManager){
		return new Packet(Codes.loginValid, Codes.BroadcastSingle, Boolean.toString(isManager));
	}

	public Packet broadcastInvalidLogin(){
		return new Packet(Codes.loginInvalid, Codes.BroadcastSingle, null);
	}

	public Packet sendConfirmationMailDelivery(){
		return new Packet(Codes.ConfirmationMailDelivery, Codes.BroadcastSingle, null);
	}

	public Packet sendConfirmationCustomerPriceUpdate(){
		return new Packet(Codes.ConfirmationCustomerPriceUpdate, Codes.BroadcastSingle, null);
	}

	public Packet sendDiscontinueRouteValid(){
		return new Packet(Codes.DiscontinueRouteValid, Codes.BroadcastSingle, null);
	}

	public Packet sendDiscontinueRouteInvalid(){
		return new Packet(Codes.DiscontinueRouteInvalid, Codes.BroadcastSingle, null);
	}

	public Packet broadcastBusinessFigures(double totalRevenue, double totalExpenditure, int totalNumberOfEvents, int totalNumberOfMail, double totalWeightOfMail, double totalVolumeOfMail){
		return new Packet(Codes.ServerBusinessFigures, Codes.BroadcastAll, ServerStringBuilder.makeBusinessFigures());
	}

	public Packet broadcastRoute(String to, String from){
		return new Packet(Codes.ServerNewRoute, Codes.BroadcastAll, ServerStringBuilder.makeNewRouteString(to, from));
	}

	public Packet broadcastConfirmationMadeRoute(String destination, String origin){
		return new Packet(Codes.ConfirmationMadeRoute, Codes.BroadcastSingle, ServerStringBuilder.makeNewRouteString(destination, origin));
	}

	public Packet broadcastRoutesMailDelivery(double cheapestCost, double cheapestRouteCost, int cheapestTime, double fastestCost, double fastestRouteCost, int fastestTime){
		return new Packet(Codes.ServerMailDeliveryRoutes, Codes.BroadcastSingle, ServerStringBuilder.makeMailDeliveryString(cheapestCost, cheapestRouteCost, cheapestTime, fastestCost, fastestRouteCost, fastestTime));
	}

	public Packet broadcastTransportRouteUpdate(){
		return new Packet(Codes.ConfirmationUpdateRoute, Codes.BroadcastSingle, null);
	}

	public Packet broadcastConfirmationMadeCustomerRoute(String destination, String origin){
		return new Packet(Codes.ConfirmationMadeCustomerRoute, Codes.BroadcastSingle, ServerStringBuilder.makeNewRouteString(destination, origin));
	}

	public Packet broadcastConfirmationUpdateCustomerRoute(){
		return new Packet(Codes.ConfirmationUpdateCustomerRoute, Codes.BroadcastSingle, null);
	}

	public Packet broadcastConfirmationDiscontinueRouteValid(){
		return new Packet(Codes.DiscontinueRouteValid, Codes.BroadcastSingle, null);
	}

	public Packet broadcastConfirmationDiscontinueRouteInvalid(){
		return new Packet(Codes.DiscontinueRouteInvalid, Codes.BroadcastSingle, null);
	}

	public String getStringFromArrayList(List<String> list){
		String s = "";
		for(int i = 0; i < list.size(); i++){
			if(i == list.size() - 1){ s+= list.get(i); }
			else {s += list.get(i) + "_";}
		}
		return s;
	}
}

