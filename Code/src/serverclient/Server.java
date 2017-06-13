package serverclient;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import io.Codes;
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
				ClientThread t = new ClientThread(s, this.model, this);
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
		for(int i = this.clients.size(); --i >= 0;){
			ClientThread t = this.clients.get(i);

			if(p.getBroadcast().equals(Codes.BroadcastSingle)){
				if(t.getUserId() == id){
					if(!t.writeToClient(p)){
						this.clients.remove(i);
					}
				}
			}else{
				if(!t.writeToClient(p)){
					this.clients.remove(i);
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
			Server server = new Server(p, m);
			server.start();
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
		private Server server;

		private ClientThread(Socket s, KpsModel model, Server server){
			this.s = s;
			this.server = server;
			this.id = uniqueId++;
			this.parser = new ServerParser(model, this.server, this.id);

			try {
				this.output = new ObjectOutputStream(s.getOutputStream());
				this.input = new ObjectInputStream(s.getInputStream());
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}				
		}

		public void run(){
			boolean live = true;
			outer: while(live){
				try{
					Packet packet = (Packet)this.input.readObject();
					this.parser.parseMessage(packet);
				}catch(Exception e){
					System.out.println("EXCEPTION: " + e);
					live = false;
					break outer;
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
				this.output.writeObject(o);
			}catch(Exception e){
				System.out.println("Exception: Cannot write out from socket" + id + ", " + e);
			}
			return true;
		}


		public void close(){
			try{
				if(this.input != null)
					this.input.close();
				if(this.output != null)
					this.output.close();
				if(this.s != null)
					this.s.close();

				System.out.println(this.id + " has been disconnected");
			}catch(Exception e){
				System.out.println("Exception: cannot close socket " + id + ", " + e);
			}
		}

		private int getUserId(){
			return this.id;
		}
	}
}

