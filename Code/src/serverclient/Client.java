package serverclient;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import io.ClientParser;

public class Client {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket s;

	private String ip;
	private int port;

	private ClientController controller;
	private ClientParser parser;
	private String username;


	public Client(String ip, int port){
		this.ip = ip;
		this.port = port;

		this.start();
	}

	public void start(){
		try{
			this.s = new Socket(this.ip, this.port);
		}catch(Exception e){
			System.out.println("Exception: Error connecting to server with ip " + this.ip + " and port " + this.port + ", " + e);
			return;
		}

		try{
			this.input = new ObjectInputStream(this.s.getInputStream());
			this.output = new ObjectOutputStream(this.s.getOutputStream());
		}catch(Exception e){
			System.out.println("Exception: Error creating io streams, " + e);
		}

		this.controller = new ClientController(this);
		this.parser = new ClientParser(this.controller);

		System.out.println("Connected to " + this.ip + " on port " + this.port);

		new ServerThread(this.input);
	}

	public void sendMessage(Packet p){
		try{
			this.output.writeObject(p);
		}catch(Exception e){
			System.out.println("Exception: error writing to server, " + e);
		}
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsername(String name){
		this.username = name;
	}

	public static void main(String[] args){
		try{
			Scanner s = new Scanner(new File("client-config.txt"));
			String host = s.next();
			int p = s.nextInt();

			s.close();
			new Client(host, p);
		}catch(Exception e){
			System.out.println("Exception, cannot read config file");
		}

	}

	public class ServerThread extends Thread{
		ObjectInputStream i;
		
		public ServerThread(ObjectInputStream i){
			this.i = i;
			start();
		}
		
		public void run(){
			while(true){
				try{
					Packet packet = (Packet)i.readObject();
					parser.parseMessage(packet);
				}catch(IOException e){
					System.out.println("Exception: error reading from server IO, " + e);
					break;
				} catch (ClassNotFoundException e) {
					System.out.println("Exception: error reading from server Class Not Found, " + e);
				}
			}
		}
	}
}
