
package cr.ac.itcr.server.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		System.out.println("Clase servidor iniciada");
	}	
	
	public void runServer()
	{
		
		try {
			ServerSocket server = new ServerSocket(8080,10);
		} catch (IOException e) {
				
			System.out.println("Error");
		}
	}
		

}

}
