package serverclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import gui.Gui;
import io.ClientStringBuilder;
import io.Codes;

public class ClientController {
	private Client c;
	private Gui g;
	private ArrayList<String> locations;
	private ArrayList<String> companies;
	public boolean logged;

	public ClientController(Client c){
		this.c = c;
		this.locations = new ArrayList<String>();
		this.companies = new ArrayList<String>();
		this.g = new Gui(this);
		this.logged = false;
		//setup login page
	}

	public ClientController(){
		this.locations = new ArrayList<String>();
		this.companies = new ArrayList<String>();
		this.g = new Gui(this);
		//setup login page check
	}

	public void requestMailCreationRoutes(String from, String to, String priority, double weight, double volume){
		Packet p = new Packet(Codes.ClientGetRoutesMailDelivery, null, ClientStringBuilder.requestMailCreationRoutesString(from, to, priority, weight, volume));
		this.c.sendMessage(p);
	}

	/**
	 * Requests mail creation from the server
	 * @param origin
	 * @param dest
	 * @param weight
	 * @param volume
	 */
	public void requestMailCreation(String origin, String dest, double weight, double volume, double cost) {
		// TODO:
//		Date date = new Date(time);
//		DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//		String dateAndTime = formatter.format(date);
//
//		System.out.printf("Mail delivery event creation requested @ %s\norigin: %s\ndestination: %s\nweight: %f g\nvolume: %f cm^3\n", dateAndTime, origin, dest, weight, volume);

		String day = "Sunday";

		Calendar c = Calendar.getInstance();
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

		switch(dayOfWeek){
			case 1:
				day = "Sunday";
				break;
			case 2:
				day = "Monday";
				break;
			case 3:
				day = "Tuesday";
				break;
			case 4:
				day = "Wednesday";
				break;
			case 5:
				day = "Thursday";
				break;
			case 6:
				day = "Friday";
				break;
			case 7:
				day = "Saturday";
				break;
			default:
				break;
		}

		Packet p = new Packet(Codes.MailCreation, null, ClientStringBuilder.requestMailCreationString(origin, dest, weight, volume, cost, day));
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
		Packet p = new Packet(Codes.CustomerPriceUpdate, null, ClientStringBuilder.requestCustomerPriceUpdateString(origin, dest, priority, pricePerGram, pricePerCube));
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
	public void requestTransportCostUpdate(String origin, String dest, String company, String priority, double pricePerGram, double pricePerCube, String days, int period, double duration, double maxWeight, double maxVolume) {
		Packet p = new Packet(Codes.TransportPriceUpdate, null, ClientStringBuilder.requestTransportCostUpdateString(origin, dest, company, priority, pricePerGram, pricePerCube, days, period, duration, maxWeight, maxVolume));
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
		Packet p = new Packet(Codes.TransportDiscontinue, null, ClientStringBuilder.requestTransportDiscontinuedString(origin, dest, company, priority));
		this.c.sendMessage(p);
	}

	public void LoginRequest(String username, String password){
		Packet p = new Packet(Codes.loginDetails, null, ClientStringBuilder.requestLoginDetailsString(username, password));
		this.c.sendMessage(p);
	}

	public void addInLocation(String location){
		if(!this.locations.contains(location)){
			this.locations.add(location);
		}
		g.homePanel.update();
	}

	public void addInCompany(String company){
		if(!this.companies.contains(company)){
			this.companies.add(company);
		}
		g.homePanel.update();
	}

	public void successfullLogin(boolean manager){
		this.g.loginSuccess();
		logged = true;
	}

	public void failedLogin(){
		this.g.loginFail();
		logged = false;
	}

	public String[] getLocations(){
		String[] array = new String[locations.size()];
		for(int i = 0; i < locations.size(); i++){
			array[i] = locations.get(i);
		}
		return array;
	}

	public String[] getCompanies(){
		String[] array = new String[companies.size()];
		for(int i = 0; i < companies.size(); i++){
			array[i] = companies.get(i);
		}
		return array;
	}
	
	public void successfullEvent(String string){
		g.eventSuccessfull(string);
	}

	public void notifyDeliverOption(double cheapCost, double cheapTime, double fastestCost, double fastestTime){
		g.giveDeliveryOption(cheapCost, cheapTime, fastestCost, fastestTime);
	}
}
