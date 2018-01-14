package chat;

import java.net.Socket;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class ServerWorker extends Thread{
	
	Server server;
	Socket socket;
	
	public ServerWorker(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}
	
	public void run ()
	{
		handleClient();
		try {
			socket.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handleClient()
	{
		try {
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			
			String line;
			String command;
			
			while ((line =br.readLine()) != null) {
				
				String[] tokens = StringUtils.split(line);
				if (tokens.length > 0) {
					command = tokens[0];
					if (command.equalsIgnoreCase("quit"))
						break;
					if (command.equalsIgnoreCase("login")) 
						handleLogin(outputStream, tokens);
					else
						outputStream.write("unknown command".getBytes());
						
						
						
					}
				}
					
				
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	
	
	private void handleLogin(OutputStream outputStream, String[] tokens) {
		
		String userName = tokens[1];
		String password = tokens[2];
		
		try {
			if ((userName.equals("guest") && password.equals("guest")) || (userName.equals("jim") && password.equals("jim")))
				outputStream.write("Login successful".getBytes());
			else
				outputStream.write("Login unsuccessful".getBytes());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
