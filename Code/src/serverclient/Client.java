package serverclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import io.ServerParser;

public class Client {
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Socket s;

	private String ip;
	private int port;

	private ClientController controller;
	private String username;



	public Client(String ip, int port){
		this.ip = ip;
		this.port = port;

		this.start();
	}

	public void start(){
		try{
			this.s = new Socket(ip, port);
		}catch(Exception e){
			System.out.println("Exception: Error connecting to server with ip " + this.ip + " and port " + this.port + ", " + e);
		}

		try{
			this.input = new ObjectInputStream(this.s.getInputStream());
			this.output = new ObjectOutputStream(this.s.getOutputStream());
		}catch(Exception e){
			System.out.println("Exception: Error creating io streams, " + e);
		}

		this.controller = new ClientController(this);
		new ServerThread().start();
	}

	public void sendMessage(){
		try{
			this.output.writeObject(new Object());
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
			Scanner s = new Scanner("client-config.txt");
			String host = s.next();
			int p = s.nextInt();

			s.close();
			new Client(host, p);
		}catch(Exception e){
			System.out.println("Exception, cannot read config file");
		}

	}

	public class ServerThread extends Thread{
		public void run(){
			while(true){
				try{
					Object o = input.readObject();
					Object o1 = ServerParser.parseServerMessage(o);
				}catch(Exception e){
					System.out.println("Exception: error reading from server, " + e);
				}
			}
		}
	}
}
