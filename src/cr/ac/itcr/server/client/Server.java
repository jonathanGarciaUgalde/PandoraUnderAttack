package cr.ac.itcr.server.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
	   
	public static final String UNKNOWN_HOST = "Unknown Host";
	    private PrintWriter out;
	    Socket echoSocket = null;

	    public Server (String hostName, int portNumber) throws ConnectException {

	        try {
	            try {
	                echoSocket = new Socket(hostName, portNumber);
	            } catch (ConnectException e) {
	                throw e;
	            } catch (UnknownHostException e) {
	                System.out.println(UNKNOWN_HOST);
	            }

	            out = new PrintWriter(echoSocket.getOutputStream(), true);

	        } catch (ConnectException e) {
	            throw e;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void send(String text) {
	        out.println(text);
	    }


	    public void close() {
	        try {
	            echoSocket.close();
	        } catch (IOException e) {
	            e.getMessage();
	        }
	    }
	

}
