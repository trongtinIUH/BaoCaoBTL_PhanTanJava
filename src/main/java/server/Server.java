package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import app.App_Karaoke4T;



public class Server {
	public static void main(String[] args) {
		
		try(ServerSocket server = new ServerSocket(7878);
				){
			
			System.out.println("Server started on port 7878");
			
			while (true) {
				Socket client = server.accept();
				
				System.out.println("Client connected"	);
				System.out.println("Client IP: " + client.getInetAddress().getHostName());
				System.out.println("Client Port: " + client.getPort());
				
				Thread t = new Thread(new ClientHandler(client));
				t.start();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class ClientHandler implements Runnable{
	private Socket socket;

	public ClientHandler(Socket client) {
		super();
		this.socket = client;
	}

	@Override
	public void run() {
		
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			
//			int choice = 0;
//			
//			while(true) {
//				choice = in.readInt();
//				switch(choice) {
//				case 1:
//
//					break;
//				case 2:
//				
//					
//					break;
//				}
//			}
			App_Karaoke4T app_Karaoke4T = new App_Karaoke4T();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}