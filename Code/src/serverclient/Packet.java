package serverclient;

public class Packet {
	private String type;
	private String information;

	public Packet(String type, String information){
		this.type = type;
		this.information = information;
	}

	public String getType(){
		return this.type;
	}

	public String getInformation(){
		return this.information;
	}
}
