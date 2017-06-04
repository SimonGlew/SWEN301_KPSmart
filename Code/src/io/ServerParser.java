package io;

import serverclient.Packet;
import io.Codes;
import io.Codes.Events;
import model.KpsModel;

public class ServerParser {
	private KpsModel model;

	public ServerParser(KpsModel model){
		this.model = model;
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

	}

	public void parseMailCreation(Packet p){

	}

	public void parseCustomerPriceUpdate(Packet p){

	}

	public void parseTransportDiscontinue(Packet p){

	}

}
