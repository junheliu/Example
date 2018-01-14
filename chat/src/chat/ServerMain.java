package chat;

public class ServerMain {

	public static void main(String[] args) {
		System.out.println("Hello");
		int port = 8188;
		
		System.out.println("Start running ...");
		Server server = new Server(port);
		server.start();

	}

}
