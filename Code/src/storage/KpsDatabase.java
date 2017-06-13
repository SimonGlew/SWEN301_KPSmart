package storage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import model.KpsModel;
import model.KpsModel.Day;

/**
 * Created by Jack on 6/3/2017.
 */
public class KpsDatabase {

	static final String LOGFILE = "logfile.xml";

	ArrayList<BusinessEvent> businessEvents;

	public KpsDatabase() {
		loadEvents();
		for (BusinessEvent b : businessEvents) {
			System.out.println(b.toString());
		}
		writeEvents();
	}

	public static void main(String[] args) {
		new KpsDatabase();
	}

	public ArrayList<BusinessEvent> getBusinessEvents() {
		return businessEvents;
	}

	public void loadEvents() {
		businessEvents = new ArrayList<>();
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();

			dom = db.parse(LOGFILE);

			Element e = dom.getDocumentElement();

			NodeList nodes = e.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				switch (node.getNodeName()) {
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
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public void writeEvents() {
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			dom = db.newDocument();

			Element root = dom.createElement("Events");

			for (BusinessEvent e : businessEvents) {
				if (e instanceof TransportCostUpdate) {
					root.appendChild(createTransportCostUpdateElement((TransportCostUpdate) e, dom));
				} else if (e instanceof TransportDiscontinued) {
					root.appendChild(createTransportDiscontinuedElement((TransportDiscontinued) e, dom));
				} else if (e instanceof MailDelivery) {
					root.appendChild(createMailDeliveryElement((MailDelivery) e, dom));
				} else if (e instanceof CustomerPriceUpdate) {
					root.appendChild(createCustomerPriceUpdateElement((CustomerPriceUpdate) e, dom));
				}
			}
			dom.appendChild(root);
			printDOMToLog(dom);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public void printDOMToLog(Document dom) {
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			// send DOM to file
			tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(LOGFILE)));

		} catch (TransformerException te) {
			System.out.println(te.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public Element createCustomerPriceUpdateElement(CustomerPriceUpdate e, Document dom){
		Element cpuElement = dom.createElement("CustomerPriceUpdate");

		Element eventIDElement = dom.createElement("EventID");
		eventIDElement.setTextContent(e.getId()+"");

		Element dateTimeElement = dom.createElement("DateTime");
		dateTimeElement.setTextContent(e.getDateyymmddhhmmss());

		Element userElement = dom.createElement("Username");
		userElement.setTextContent(e.getUsername());

		Element toElement = dom.createElement("To");
		toElement.setTextContent(e.getTo());

		Element fromElement = dom.createElement("From");
		fromElement.setTextContent(e.getFrom());

		Element priorityElement = dom.createElement("Priority");
		priorityElement.setTextContent(e.getPriority()+"");

		Element weightCostElement = dom.createElement("WeightCost");
		weightCostElement.setTextContent(e.getWeightCost() + "");

		Element volumeCostElement = dom.createElement("VolumeCost");
		volumeCostElement.setTextContent(e.getVolumeCost() + "");

		cpuElement.appendChild(eventIDElement);
		cpuElement.appendChild(dateTimeElement);
		cpuElement.appendChild(userElement);
		cpuElement.appendChild(toElement);
		cpuElement.appendChild(fromElement);
		cpuElement.appendChild(priorityElement);
		cpuElement.appendChild(weightCostElement);
		cpuElement.appendChild(volumeCostElement);
		return cpuElement;
	}

	public Element createMailDeliveryElement(MailDelivery e, Document dom){
		Element mdElement = dom.createElement("MailDelivery");

		Element eventIDElement = dom.createElement("EventID");
		eventIDElement.setTextContent(e.getId()+"");

		Element dateTimeElement = dom.createElement("DateTime");
		dateTimeElement.setTextContent(e.getDateyymmddhhmmss());

		Element userElement = dom.createElement("Username");
		userElement.setTextContent(e.getUsername());

		Element dayElement = dom.createElement("Day");
		dayElement.setTextContent(convertDayToString(e.getDay()));

		Element toElement = dom.createElement("To");
		toElement.setTextContent(e.getTo());

		Element fromElement = dom.createElement("From");
		fromElement.setTextContent(e.getFrom());

		Element weightElement = dom.createElement("Weight");
		weightElement.setTextContent(e.getWeight()+"");

		Element volumeElement = dom.createElement("Volume");
		volumeElement.setTextContent(e.getVolume()+"");

		Element priorityElement = dom.createElement("Priority");
		priorityElement.setTextContent(e.getPriority()+"");

		Element costElement = dom.createElement("Cost");
		costElement.setTextContent(e.getCost()+"");

		mdElement.appendChild(eventIDElement);
		mdElement.appendChild(dateTimeElement);
		mdElement.appendChild(userElement);
		mdElement.appendChild(dayElement);
		mdElement.appendChild(toElement);
		mdElement.appendChild(fromElement);
		mdElement.appendChild(weightElement);
		mdElement.appendChild(volumeElement);
		mdElement.appendChild(priorityElement);
		mdElement.appendChild(costElement);

		return mdElement;
	}

	public Element createTransportDiscontinuedElement(TransportDiscontinued e, Document dom){
		Element tdElement = dom.createElement("TransportDiscontinued");

		Element eventIDElement = dom.createElement("EventID");
		eventIDElement.setTextContent(e.getId()+"");

		Element dateTimeElement = dom.createElement("DateTime");
		dateTimeElement.setTextContent(e.getDateyymmddhhmmss());

		Element userElement = dom.createElement("Username");
		userElement.setTextContent(e.getUsername());

		Element companyElement = dom.createElement("Company");
		companyElement.setTextContent(e.getCompany());

		Element toElement = dom.createElement("To");
		toElement.setTextContent(e.getTo());

		Element fromElement = dom.createElement("From");
		fromElement.setTextContent(e.getFrom());

		Element priorityElement = dom.createElement("Priority");
		priorityElement.setTextContent(e.getPriority()+"");

		tdElement.appendChild(eventIDElement);
		tdElement.appendChild(dateTimeElement);
		tdElement.appendChild(userElement);
		tdElement.appendChild(companyElement);
		tdElement.appendChild(toElement);
		tdElement.appendChild(fromElement);
		tdElement.appendChild(priorityElement);
		return tdElement;
	}

	public Element createTransportCostUpdateElement(TransportCostUpdate e, Document dom) {
		Element tcuElement = dom.createElement("TransportCostUpdate");

		Element eventIDElement = dom.createElement("EventID");
		eventIDElement.setTextContent(e.getId()+"");

		Element dateTimeElement = dom.createElement("DateTime");
		dateTimeElement.setTextContent(e.getDateyymmddhhmmss());

		Element userElement = dom.createElement("Username");
		userElement.setTextContent(e.getUsername());

		Element companyElement = dom.createElement("Company");
		companyElement.setTextContent(e.getCompany());

		Element toElement = dom.createElement("To");
		toElement.setTextContent(e.getTo());

		Element fromElement = dom.createElement("From");
		fromElement.setTextContent(e.getFrom());

		Element priorityElement = dom.createElement("Priority");
		priorityElement.setTextContent(e.getPriority() + "");

		Element weightCostElement = dom.createElement("WeightCost");
		weightCostElement.setTextContent(e.getWeightCost() + "");

		Element volumeCostElement = dom.createElement("VolumeCost");
		volumeCostElement.setTextContent(e.getVolumeCost() + "");

		Element maxWeightElement = dom.createElement("MaxWeight");
		maxWeightElement.setTextContent(e.getMaxWeight() + "");

		Element maxVolumeElement = dom.createElement("MaxVolume");
		maxVolumeElement.setTextContent(e.getMaxVolume() + "");

		Element durationElement = dom.createElement("Duration");
		durationElement.setTextContent(e.getDuration() + "");

		Element frequencyElement = dom.createElement("Frequency");
		frequencyElement.setTextContent(e.getFrequency() + "");

		Element daysElement = dom.createElement("Days");
		for (Day d : e.days) {
			Element dayElement = dom.createElement("Day");
			dayElement.setTextContent(convertDayToString(d));
			daysElement.appendChild(dayElement);
		}

		tcuElement.appendChild(eventIDElement);
		tcuElement.appendChild(dateTimeElement);
		tcuElement.appendChild(userElement);
		tcuElement.appendChild(companyElement);
		tcuElement.appendChild(toElement);
		tcuElement.appendChild(fromElement);
		tcuElement.appendChild(priorityElement);
		tcuElement.appendChild(weightCostElement);
		tcuElement.appendChild(volumeCostElement);
		tcuElement.appendChild(maxWeightElement);
		tcuElement.appendChild(maxVolumeElement);
		tcuElement.appendChild(durationElement);
		tcuElement.appendChild(frequencyElement);
		tcuElement.appendChild(daysElement);

		return tcuElement;

	}

	public CustomerPriceUpdate parseCustomerPriceUpdate(Node node) {
		CustomerPriceUpdate customerPriceUpdate = new CustomerPriceUpdate();
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node data = childNodes.item(i);
			switch (data.getNodeName()) {
				case "DateTime":
					customerPriceUpdate.setDateyymmddhhmmss(data.getTextContent());
					break;
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

	public MailDelivery parseMailDelivery(Node node) {
		MailDelivery mailDelivery = new MailDelivery();
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node data = childNodes.item(i);
			switch (data.getNodeName()) {
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

	public TransportDiscontinued parseTransportDiscontinued(Node node) {
		TransportDiscontinued transportDiscontinued = new TransportDiscontinued();
		NodeList childNodes = node.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node data = childNodes.item(i);
			switch (data.getNodeName()) {
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

	public TransportCostUpdate parseTransportCostUpdate(Node node) {
		TransportCostUpdate transportCostUpdate = new TransportCostUpdate();
		NodeList childNodes = node.getChildNodes();

		for (int j = 0; j < childNodes.getLength(); j++) {
			Node data = childNodes.item(j);
			switch (data.getNodeName()) {
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

	public List<KpsModel.Day> parseDaysNode(Node node) {
		ArrayList<KpsModel.Day> days = new ArrayList<>();
		NodeList dayNodes = node.getChildNodes();

		for (int d = 0; d < dayNodes.getLength(); d++) {
			if (dayNodes.item(d).getNodeName().equals("Day")) {
				days.add(parseDay(dayNodes.item(d)));
			}
		}
		return days;
	}

	public KpsModel.Day parseDay(Node dayNode) {
		switch (dayNode.getTextContent()) {
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

	public String convertDayToString(Day d) {
		if (d == Day.Monday) {
			return "Monday";
		} else if (d == Day.Tuesday) {
			return "Tuesday";
		} else if (d == Day.Wednesday) {
			return "Wednesday";
		} else if (d == Day.Thursday) {
			return "Thursday";
		} else if (d == Day.Friday) {
			return "Friday";
		} else if (d == Day.Saturday) {
			return "Saturday";
		} else if (d == Day.Sunday) {
			return "Sunday";
		}
		return "ERROR";
	}

}
