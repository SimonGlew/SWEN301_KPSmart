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

import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import users.PasswordEncoder;
import users.StaffMember;

public class UserDatabase {
	static final String USERLOG= "userlog.xml";

	private List<StaffMember> staffMembers;

	public UserDatabase(){
		loadUsers();
		writeUsers();
	}

	public void loadUsers(){
		staffMembers = new ArrayList<StaffMember>();
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();

			dom = db.parse(USERLOG);

			Element users = dom.getDocumentElement();

			NodeList nodes = users.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				switch (node.getNodeName()) {
				case "StaffMember":
					staffMembers.add(parseStaffMember(node));
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

	public StaffMember parseStaffMember(Node p){
		StaffMember member = new StaffMember();
		NodeList childNodes = p.getChildNodes();

		for(int i = 0; i < childNodes.getLength(); i++){
			Node data = childNodes.item(i);
			switch(data.getNodeName()){
				case "Username":
					member.setUsername(data.getTextContent());
					break;
				case "Password":
					member.setEncodedPassword(data.getTextContent());
					break;
				case "Manager":
					member.setManager(Boolean.parseBoolean(data.getTextContent()));
					break;
			}
		}
		return member;

	}

	public void writeUsers() {
		Document dom;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			dom = db.newDocument();

			Element root = dom.createElement("Users");

			for (StaffMember sm : staffMembers) {
				root.appendChild(createStaffMemberElement(dom, sm));
			}

			dom.appendChild(root);
			printDOMToLog(dom);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public Element createStaffMemberElement(Document dom, StaffMember sm){
		Element smElement = dom.createElement("StaffMember");

		Element userElement = dom.createElement("Username");
		userElement.setTextContent(sm.getUsername());

		Element passElement = dom.createElement("Password");
		passElement.setTextContent(sm.getEncodedPassword());

		Element managerElement = dom.createElement("Manager");
		managerElement.setTextContent(sm.isManager()+"");

		smElement.appendChild(userElement);
		smElement.appendChild(passElement);
		smElement.appendChild(managerElement);
		return smElement;

	}

	public void printDOMToLog(Document dom) {
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
			tr.setOutputProperty(OutputKeys.METHOD, "xml");
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

			// send DOM to file
			tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(USERLOG)));

		} catch (TransformerException te) {
			System.out.println(te.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	public StaffMember solveUserWithCredentials(String username, String password){
		for(StaffMember sm: staffMembers){
			if(sm.getUsername().equals(username)){
				if(PasswordEncoder.verifyPassword(password, sm.getEncodedPassword())){
					return sm;
				}
			}
		}
		return null;
	}

	public void addStaffMember(String username, String password, boolean isManager){
		staffMembers.add(new StaffMember(username, PasswordEncoder.encodePassword(password), isManager));
		writeUsers();
	}

	public static void main(String[] args){
		UserDatabase ud = new UserDatabase();
//		System.out.println(ud.solveUserWithCredentials("a", "a"));
//		System.out.println(ud.solveUserWithCredentials("admin", "admin"));
//		System.out.println(ud.solveUserWithCredentials("Jonathan", "Neutron"));
//		System.out.println(ud.solveUserWithCredentials("Kyal", "Kyal"));
//		System.out.println(ud.solveUserWithCredentials("Jack", "Jack"));
//		System.out.println(ud.solveUserWithCredentials("Dallan", "CubaSt"));
//		System.out.println(ud.solveUserWithCredentials("admin", "admin"));
//		System.out.println(ud.solveUserWithCredentials("ChickenNugget", "nugget"));
//		System.out.println(ud.solveUserWithCredentials("tutorStandard", "tutor"));
//		System.out.println(ud.solveUserWithCredentials("tutorManager", "tutor"));

	}

}
