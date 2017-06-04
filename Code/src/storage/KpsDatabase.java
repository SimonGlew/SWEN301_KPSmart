package storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.KpsModel;

/**
 * Created by Jack on 6/3/2017.
 */
public class KpsDatabase {

	static final String LOGFILE = "logfile.xml";

	ArrayList<BusinessEvent> businessEvents;

	public KpsDatabase(){
		loadEvents();
		for(BusinessEvent b: businessEvents){
			System.out.println(b.toString());
		}
	}

	public void loadEvents(){
		businessEvents = new ArrayList<>();
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try{

			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(LOGFILE);
			Element e = dom.getDocumentElement();
			NodeList nodes = e.getChildNodes();


			for(int i = 0; i< nodes.getLength(); i++){
				Node node = nodes.item(i);

				switch(node.getNodeName()){
					case "TransportCostUpdate":
						businessEvents.add(parseTransportCostUpdate(node));
						break;
					case "TransportDiscontinued":
						businessEvents.add(parseTransportDiscontinued(node));
						break;
					case "MailDelivery":
						businessEvents.add(parseMailDelivery(node));
						break;
					case "CustomerPriceUpdate":
						businessEvents.add(parseCustomerPriceUpdate(node));
						break;
					default:
						break;
				}
			}
		}
		catch(Exception e){

		}

	}

	public CustomerPriceUpdate parseCustomerPriceUpdate(Node node){
		CustomerPriceUpdate customerPriceUpdate = new CustomerPriceUpdate();
		NodeList childNodes = node.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); i++){
			Node data = childNodes.item(i);
			switch(data.getNodeName()){
				case "To":
					customerPriceUpdate.setTo(data.getTextContent());
					break;
				case "From":
					customerPriceUpdate.setFrom(data.getTextContent());
					break;
				case "WeightCost":
					customerPriceUpdate.setWeightCost(Double.parseDouble(data.getTextContent()));
					break;
				case "VolumeCost":
					customerPriceUpdate.setVolumeCost(Double.parseDouble(data.getTextContent()));
					break;
				case "Priority":
					customerPriceUpdate.setPriority(Integer.parseInt(data.getTextContent()));
					break;
			}
		}
		return customerPriceUpdate;
	}

	public MailDelivery parseMailDelivery(Node node){
		MailDelivery mailDelivery = new MailDelivery();
		NodeList childNodes = node.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); i++){
			Node data = childNodes.item(i);
			switch(data.getNodeName()){
				case "Day":
					mailDelivery.setDay(parseDay(data));
					break;
				case "To":
					mailDelivery.setTo(data.getTextContent());
					break;
				case "From":
					mailDelivery.setFrom(data.getTextContent());
					break;
				case "Weight":
					mailDelivery.setWeight(Double.parseDouble(data.getTextContent()));
					break;
				case "Volume":
					mailDelivery.setVolume(Double.parseDouble(data.getTextContent()));
					break;
				case "Priority":
					mailDelivery.setPriority(Integer.parseInt(data.getTextContent()));
					break;
			}
		}
		return mailDelivery;
	}

	public TransportDiscontinued parseTransportDiscontinued(Node node){
		TransportDiscontinued transportDiscontinued = new TransportDiscontinued();
		NodeList childNodes = node.getChildNodes();
		for(int i = 0; i < childNodes.getLength(); i++){
			Node data = childNodes.item(i);
			switch(data.getNodeName()){
				case "Company":
					transportDiscontinued.setCompany(data.getTextContent());
					break;
				case "To":
					transportDiscontinued.setTo(data.getTextContent());
					break;
				case "From":
					transportDiscontinued.setFrom(data.getTextContent());
					break;
				case "Priority":
					transportDiscontinued.setPriority(Integer.parseInt(data.getTextContent()));
					break;
			}
		}

		return transportDiscontinued;

	}

	public TransportCostUpdate parseTransportCostUpdate(Node node){
		TransportCostUpdate transportCostUpdate = new TransportCostUpdate();
		NodeList childNodes = node.getChildNodes();

		for(int j =  0; j < childNodes.getLength(); j++){
			Node data = childNodes.item(j);
			switch(data.getNodeName()){
				case "Company":
					transportCostUpdate.setCompany(data.getTextContent());
					break;
				case "To":
					transportCostUpdate.setTo(data.getTextContent());
					break;
				case "From":
					transportCostUpdate.setFrom(data.getTextContent());
					break;
				case "Priority":
					transportCostUpdate.setPriority(Integer.parseInt(data.getTextContent()));
					break;
				case "WeightCost":
					transportCostUpdate.setWeightCost(Double.parseDouble(data.getTextContent()));
					break;
				case "VolumeCost":
					transportCostUpdate.setVolumeCost(Double.parseDouble(data.getTextContent()));
					break;
				case "MaxWeight":
					transportCostUpdate.setMaxWeight(Double.parseDouble(data.getTextContent()));
					break;
				case "MaxVolume":
					transportCostUpdate.setMaxVolume(Double.parseDouble(data.getTextContent()));
					break;
				case "Duration":
					transportCostUpdate.setDuration(Double.parseDouble(data.getTextContent()));
					break;
				case "Frequency":
					transportCostUpdate.setFrequency(Integer.parseInt(data.getTextContent()));
					break;
				case "Days":
					transportCostUpdate.setDays(parseDaysNode(data));
					break;

			}

		}

		return transportCostUpdate;
	}

	public List<KpsModel.Day> parseDaysNode(Node node){
		ArrayList<KpsModel.Day> days = new ArrayList<>();
		NodeList dayNodes = node.getChildNodes();

		for(int d = 0; d< dayNodes.getLength(); d++){
			days.add(parseDay(dayNodes.item(d)));
		}
		return days;
	}

	public KpsModel.Day parseDay(Node dayNode){
		switch(dayNode.getTextContent()){
			case "Monday":
				return KpsModel.Day.Monday;
			case "Tuesday":
				return KpsModel.Day.Tuesday;
			case "Wednesday":
				return KpsModel.Day.Wednesday;
			case "Thursday":
				return KpsModel.Day.Thursday;
			case "Friday":
				return KpsModel.Day.Friday;
			case "Saturday":
				return KpsModel.Day.Saturday;
			case "Sunday":
				return KpsModel.Day.Sunday;
			default:
				return null;
		}
	}




	public static void main (String[] args){
		new KpsDatabase();
	}

	public List<BusinessEvent> getBusinessEvents() {
		return businessEvents;
	}





}
