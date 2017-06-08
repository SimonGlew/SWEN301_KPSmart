package io;

import serverclient.Packet;
import serverclient.Server;
import io.Codes;
import io.Codes.Events;
import model.KpsModel;

public class ServerParser {
	private KpsModel model;
	private Server.ClientThread out;

	public ServerParser(KpsModel model, Server.ClientThread out){
		this.model = model;
		this.out = out;
	}

	public void parseMessage(Packet p){
		if(p.getType().equals(Codes.Events.TransportPriceUpdate)){
			parseTransportPriceUpdate(p);
		}else if(p.getType().equals(Codes.Events.CustomerPriceUpdate)){
			parseCustomerPriceUpdate(p);
		}else if(p.getType().equals(Codes.Events.MailCreation)){
			parseMailCreation(p);
		}else if(p.getType().equals(Codes.Events.TransportDiscontinue)){
			parseTransportDiscontinue(p);
		}
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

	public void parseMailCreation(Packet p){
		String[] s = p.getInformation().split("\\s+");

		String origin = s[0];
		String destination = s[1];
		double weight = Double.parseDouble(s[2]);
		double volume = Double.parseDouble(s[3]);

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

}
