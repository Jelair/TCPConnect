package zjl.network.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MultiThreadServer();
	}
	
	public MultiThreadServer(){
		try {
			//Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);

			//Number a client
			int clientNo = 1;
			
			while(true){
				//Listen for a new connection request
				Socket socket  = serverSocket.accept();
				InetAddress inetAddress = socket.getInetAddress();
				System.out.println("Client " + clientNo + "'s host name is " + inetAddress.getHostName());
				System.out.println("Client " + clientNo + "'s IP Address is " + inetAddress.getAddress());
				
				//Create a new thread for the connnection
				HandleAClient task = new HandleAClient(socket);
				
				//Start the new thread;
				new Thread(task).start();
				
				//Increment clientNo
				clientNo++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Inner class
	//Define the thread class for handling new connection
	class HandleAClient implements Runnable{

		private Socket socket;//A connected socket
		
		public HandleAClient(Socket socket){
			this.socket = socket;
		}
		
		@Override //Run a thread
		public void run() {
			
			try {
				//Create data input and output streams
				DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
				
				//Continuously serve the client
				while(true){
					//Receive radius from the client
					double radius = inputFromClient.readDouble();
					
					//Compute area
					double area = radius * radius * Math.PI;
					
					//Send area back to the client
					outputToClient.writeDouble(area);
					
					System.out.println("Server: radius - area" + radius + " - " + area);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
