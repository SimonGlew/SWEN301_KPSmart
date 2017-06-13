package serverclient;

public class Packet {
	private String type;
	private String broadcast;
	private String information;

	public Packet(String type, String broadcast, String information){
		this.type = type;
		this.information = information;
		this.broadcast = broadcast;
	}

	public String getType(){
		return this.type;
	}

	public String getInformation(){
		return this.information;
	}
	
	public String getBroadcast(){
		return this.broadcast;
	}
}
