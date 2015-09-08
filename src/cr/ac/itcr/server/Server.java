package cr.ac.itcr.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {
	
	private ServerSocket server;  //variable de socket del servidor
	private Socket connection; //variable de conexi�n con cliente
	private int counter = 1; //variable de contador del n�mero de conexiones
	private ObjectOutputStream output; //variable del output stream al cliente
	private ObjectInputStream input; //variable del input stream del cliente

	//configuraci�n y ejecuci�n del servidor
	public void runServer(){
		
		try{ //configura el servidor para recibir conexiones; procesa conexiones
			
			server = new ServerSocket(8080); //crea un socket del servidor
				
			try{
					
				waitForConnection(); //espera a que alg�n cliente se conecte
				getStreams(); // recibe los input y output streams
				processConnection(); //verifica la conexi�n
				
				while (true){ //lee los input streams que mand� el cliente
					try{
						readData();
					}
					//en caso de que no hayan mensajes ejecuta el catch
					catch (IOException ioException){ 
						if (counter == 6) //cuando llega a 5 conexiones cierra el servidor
							closeConnection();
						if (counter == 6) //cuando llega a 5 conexiones sale del while
							break;	
						//si no encuentra mensajes y hay menos de 5 conexiones
						//vuelve a esperar por la conexi�n
						//cuando encuentra otra conexi�n y la verifica vuelve a ejecutar
						//el while para leer los mensajes que mand� el nuevo cliente
						waitForConnection();
						getStreams();
						processConnection();
					}
				}
			}
			catch(EOFException eofException){
					
				System.out.println("Server terminated connection");	
			}
		}	
		catch(IOException ioException){
					
			ioException.printStackTrace();		
		}
		
	}
	
	//espera a que llegue algna conexi�n y despliega informaci�n de la conexi�n
	private void waitForConnection() throws IOException{
		
		System.out.println("Waiting for connection"); 
		connection = server.accept(); //permite al servidor aceptar una conexi�n
		System.out.println("Connection " + counter + " received from: " + connection.getInetAddress().getHostName());
		++counter;
	}
	
	//abre las v�as (streams) para enviar y recibir datos
	private void getStreams() throws IOException{
		
		//configura un output stream para objetos
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		
		//configura un intput stream para objetos
		input = new ObjectInputStream(connection.getInputStream());
		
		
		System.out.println("Got I/O streams");
	}
	
	//procesa la conexi�n con el cliente
	public void processConnection() throws IOException{
		
		String message = "Connection successful"; 
		sendData(message); //env�a un mensaje de conexi�n exitosa al cliente
	
		try{ //lee el mensaje de conexi�n que envi� el cliente
				
			message = (String) input.readObject();
			System.out.println(message);

		}
		catch (ClassNotFoundException classNotFoundException){
			
			System.out.println("Unknown object type received");
		}
	}
	
	//cierra las v�as de comunicaci�n y el socket, termina el servidor
	public void closeConnection() throws IOException{
		
		System.out.println("Terminating connection");
		
		try{
			
			output.close();
			input.close();
			connection.close();
		}
		catch (IOException ioException){
			
			ioException.printStackTrace();
		}
	}
	
	//env�ar mensajes al cliente
	public void sendData(String message){
		
		try{ //env�a un objeto al cliente
			
			output.writeObject("Server >>>" + message);
			output.flush();	//manda el objeto al cliente por el output stream
		}
		catch (IOException ioException){
			
			System.out.println("Error writing object");
		}
	}
	
	//leer mensaje del cliente
	public void readData() throws IOException{
		
		try{ //guarda el mensaje en una variable y lo muestra
				
			String message = (String) input.readObject();
			System.out.println(message);
	
		}
		catch (ClassNotFoundException classNotFoundException){
				
			System.out.println("Unknown object type received");
		}
	}
}