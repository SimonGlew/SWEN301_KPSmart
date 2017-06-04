package serverclient;

public class Packet {
	public String type;
	public String information;

	public Packet(String type, String information){
		this.type = type;
		this.information = information;
	}
}
