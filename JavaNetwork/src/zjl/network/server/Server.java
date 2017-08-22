package zjl.network.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public static void main(String[] args){
		new Server();
	}

	public Server(){
		try {
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			
			//Listen for a connection request
			Socket socket  = serverSocket.accept();
			
			//Create data input and output streams
			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
			
			while(true){
				double radius = inputFromClient.readDouble();
				double area = radius * radius * Math.PI;
				outputToClient.writeDouble(area);
				System.out.println("radius - area" + radius + " - " + area);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
