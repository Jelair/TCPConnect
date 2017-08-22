package zjl.network.client;

import java.io.*;
import java.net.*;

public class Client {

	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public static void main(String[] args){
		Client c = new Client();
		for(int i = 7; i < 17; i ++){
			System.out.println("---"+c.caluateAreaByRadius(i));;
		}
	}
	
	public Client(){
		try {
			//Create a socket to connnect to the server
			Socket socket = new Socket("localhost",8000);
			
			//Create an input stream to receive data from the server
			fromServer = new DataInputStream(socket.getInputStream());
			
			//Create an output stream to send data to the server
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double caluateAreaByRadius(double radius){
		double area = 0;
		try {
			this.toServer.writeDouble(radius);
			this.toServer.flush();
			
			area = this.fromServer.readDouble();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return area;
		
	}
}
