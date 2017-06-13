package serverclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import gui.Gui;
import io.ClientStringBuilder;
import io.Codes;

public class ClientController {
	private Client c;
	private Gui g;

	public ClientController(Client c){
		this.c = c;
		this.g = new Gui(this);
		//setup login page
	}

	/**
	 * Requests mail creation from the server
	 * @param origin
	 * @param dest
	 * @param weight
	 * @param volume
	 */
	public void requestMailCreation(String origin, String dest, double weight, double volume, long time) {
		// TODO:
//		Date date = new Date(time);
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		String dateAndTime = formatter.format(date);
//
//		System.out.printf("Mail delivery event creation requested @ %s\norigin: %s\ndestination: %s\nweight: %f g\nvolume: %f cm^3\n", dateAndTime, origin, dest, weight, volume);

		Packet p = new Packet(Codes.Events.MailCreation, ClientStringBuilder.requestMailCreationString(origin, dest, weight, volume));
		this.c.sendMessage(p);
	}

	/**
	 * Requests a customer price update from the server
	 * @param origin
	 * @param dest
	 * @param priority
	 * @param pricePerGram
	 * @param pricePerCube
	 *
	 */
	public void requestCustomerPriceUpdate(String origin, String dest, String priority, double pricePerGram, double pricePerCube) {
		Packet p = new Packet(Codes.Events.MailCreation, ClientStringBuilder.requestCustomerPriceUpdateString(origin, dest, priority, pricePerGram, pricePerCube));
		this.c.sendMessage(p);
	}

	/**
	 * Requests a transport cost update from the server
	 * @param origin
	 * @param dest
	 * @param company
	 * @param priority
	 * @param pricePerGram
	 * @param pricePerCube
	 * @param day
	 * @param period
	 * @param duration
	 */
	public void requestTransportCostUpdate(String origin, String dest, String company, String priority, double pricePerGram, double pricePerCube, String day, double period, double duration) {
		Packet p = new Packet(Codes.Events.TransportPriceUpdate, ClientStringBuilder.requestTransportCostUpdateString(origin, dest, company, priority, pricePerGram, pricePerCube, day, period, duration));
		this.c.sendMessage(p);
	}

	/**
	 * Requests a transport route to be discontinued from the server
	 * @param origin
	 * @param dest
	 * @param company
	 * @param priority
	 */
	public void requestTransportDiscontinued(String origin, String dest, String company, String priority) {
		Packet p = new Packet(Codes.Events.TransportDiscontinue, ClientStringBuilder.requestTransportDiscontinuedString(origin, dest, company, priority));
		this.c.sendMessage(p);
	}
}
