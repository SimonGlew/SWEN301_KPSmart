package storage;

import java.util.ArrayList;

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

					default:
						break;
				}
			}
		}
		catch(Exception e){

		}

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
					ArrayList<KpsModel.Day> days = new ArrayList<>();
					NodeList dayNodes = data.getChildNodes();
					for(int d = 0; d< dayNodes.getLength(); d++){
						Node dayNode = dayNodes.item(d);
						switch(dayNode.getTextContent()){
							case "Monday":
								days.add(KpsModel.Day.Monday);
								break;
							case "Tuesday":
								days.add(KpsModel.Day.Tuesday);
								break;
							case "Wednesday":
								days.add(KpsModel.Day.Wednesday);
								break;
							case "Thursday":
								days.add(KpsModel.Day.Thursday);
								break;
							case "Friday":
								days.add(KpsModel.Day.Friday);
								break;
							case "Saturday":
								days.add(KpsModel.Day.Saturday);
								break;
							case "Sunday":
								days.add(KpsModel.Day.Sunday);
								break;
						}
					}
					transportCostUpdate.setDays(days);
					break;

			}

		}

		return transportCostUpdate;
	}




	public static void main (String[] args){
		new KpsDatabase();
	}





}
