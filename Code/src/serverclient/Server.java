package serverclient;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.ServerParser;
import model.KpsModel;

public class Server {
	private int port;

	private Map<Integer, String> IDtoUsername;
	private List<ClientThread> clients;
	private KpsModel model;

	private boolean alive;
	private static int uniqueId;


	public Server(int port, KpsModel model){
		this.port = port;
		this.model = model;
		this.IDtoUsername = new HashMap<Integer, String>();
		this.clients = new ArrayList<ClientThread>();

		start();
	}

	public void start(){
		alive = true;
		try{
			ServerSocket ss = new ServerSocket(this.port);
			System.out.println("Waiting for clients within port " + this.port);

			while(alive){
				Socket s = ss.accept();
				if(!alive)
					break;
				ClientThread t = new ClientThread(s, this.model);
				System.out.println("Client accepted " + t.getUserId() + " and started");
				this.clients.add(t);
				t.start();
			}

			try{
				ss.close();
				for(ClientThread t : this.clients){
					t.close();
				}
			}catch(Exception e){
				System.out.println("Exception: cannot close server down on port " + this.port + ", " + e);
			}
		}catch(Exception e){
			System.out.println("Exception: cannot start server on port " + this.port + ", " + e);
		}
	}

	public synchronized void broadcast(Packet p, int id){
		for(int i = clients.size(); --i >= 0;){
			ClientThread t = clients.get(i);

			if(p.getType() == "SINGLE SENDBACK"){ //TODO: Placeholder for messages with single sendback
				if(t.getUserId() == id){
					if(!t.writeToClient(p)){
						clients.remove(i);
					}
				}
			}else{
				if(!t.writeToClient(p)){
					clients.remove(i);
				}
			}
		}
	}

	public synchronized void remove(int id){
		for(int i = 0; i < clients.size(); i ++){
			ClientThread t = clients.get(i);
			if(t.getUserId() == id){
				clients.remove(i);
				return;
			}
		}
	}

	public static void main(String[] args){
		try{
			Scanner s = new Scanner(new File("server-config.txt"));
			int p = s.nextInt();

			s.close();
			KpsModel m = new KpsModel();
			new Server(p, m);
		}catch(Exception e){
			System.out.println("Exception: Reading in config file: " + e);
		}
	}


	public class ClientThread extends Thread{
		private Socket s;
		private ObjectInputStream input;
		private ObjectOutputStream output;
		private int id;
		private ServerParser parser;

		public ClientThread(Socket s, KpsModel model){
			this.s = s;
			this.parser = new ServerParser(model);
			this.id = uniqueId++;

			try{
				input = new ObjectInputStream(s.getInputStream());
				output = new ObjectOutputStream(s.getOutputStream());
			}catch(Exception e){
				System.out.println("Exception: Creating client output streams " + e);
			}
		}

		public void run(){
			boolean live = true;
			while(live){
				try{
					Packet packet = (Packet)input.readObject();
					this.parser.parseMessage(packet);
				}catch(Exception e){
					live = false;
					System.out.println(this.id + "cannot read input stream, " + e);
				}
			}
			IDtoUsername.remove(this.id);
			remove(this.id);
			close();

		}

		private boolean writeToClient(Object o){
			if(!s.isConnected())
				return false;

			try{
				output.writeObject(o);
			}catch(Exception e){
				System.out.println("Exception: Cannot write out from socket" + id + ", " + e);
			}
			return true;
		}


		public void close(){
			try{
				if(input != null)
					input.close();
				if(output != null)
					output.close();
				if(s != null)
					s.close();
			}catch(Exception e){
				System.out.println("Exception: cannot close socket " + id + ", " + e);
			}
		}

		private int getUserId(){
			return this.id;
		}
	}
}

