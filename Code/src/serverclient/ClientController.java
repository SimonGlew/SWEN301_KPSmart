package serverclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientController {
	private Client c;

	public ClientController(Client c){
		this.c = c;
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
		Date date = new Date(time);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		String dateAndTime = formatter.format(date);
		
		System.out.printf("Mail delivery event creation requested @ %s\norigin: %s\ndestination: %s\nweight: %f g\nvolume: %f cm^3\n", dateAndTime, origin, dest, weight, volume);
	}
	
	/**
	 * Requests a customer price update from the server
	 * @param origin
	 * @param dest
	 * @param priority
	 * @param pricePerGram
	 * @param pricePerCub
	 */
	public void requestCustomerPriceUpdate(String origin, String dest, String priority, double pricePerGram, double pricePerCube) {
		
	}
	
	/**
	 * Requests a transport cost update from the server
	 * @param origin
	 * @param dest
	 * @param company
	 * @param method
	 * @param pricePerGram
	 * @param pricePerCube
	 * @param day
	 * @param period
	 * @param duration
	 */
	public void requestTransportCostUpdate(String origin, String dest, String company, String method, double pricePerGram, double pricePerCube, String day, double period, double duration) {
		
	}
	
	/**
	 * Requests a transport route to be discontinued from the server
	 * @param origin
	 * @param dest
	 * @param company
	 * @param method
	 */
	public void requestTransportDiscontinued(String origin, String dest, String company, String method) {
		
	}
}
