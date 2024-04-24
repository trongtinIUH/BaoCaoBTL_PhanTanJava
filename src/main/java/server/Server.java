package server;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import app.App_Karaoke4T;



public class Server {
	public static void main(String[] args) {
		
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
		     App_Karaoke4T app_Karaoke4T = new App_Karaoke4T();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}