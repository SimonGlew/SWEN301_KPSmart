package client.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
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
}
