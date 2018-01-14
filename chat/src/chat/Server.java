package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
	
	private int port;
	
	//List<ServerWorker> workerList = new ArrayList<ServerWorker>();

	public Server(int port)
	{
		this.port = port;
	}
	
	public void run()
	{
		try {
			
			System.out.println("before listening");
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				
				
				System.out.println("Starting to accept connection");
				Socket clientSocket = serverSocket.accept();
				System.out.println("connection established: " + clientSocket);
				ServerWorker serverWorker = new ServerWorker(this, clientSocket);
			//	workerList.add(serverWorker);
				System.out.println("Starting serverWorker");
				serverWorker.start();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
