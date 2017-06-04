package storage;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * Created by Jack on 6/3/2017.
 */
public class KpsDatabase {

	static final String LOGFILE = "logfile.xml";

	ArrayList<BusinessEvent> BusinessEvents;

	public KpsDatabase(){
		loadEvents();
	}

	public void loadEvents(){
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try{

			DocumentBuilder db = dbf.newDocumentBuilder();
			dom = db.parse(LOGFILE);


		}
		catch(Exception e){

		}

	}




	public static void main (String[] args){
		new KpsDatabase();
	}





}
