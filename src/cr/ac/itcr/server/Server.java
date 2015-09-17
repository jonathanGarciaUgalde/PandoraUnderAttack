package cr.ac.itcr.server;
import  java .io.*;//  aqui  se importa  la libreria  donde   se va  a  formar los  archivos  txt
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;



public class Server {
	String  armas="12";
	String meritocracia="122";
	String  recursos="23";
	
	FileWriter archivoEscritura;// se crea el nombre  y  el  formato del archivo
	
	BufferedWriter  buferEscritura;// asigna  ese  archivo  creado  a el bufer
	
	PrintWriter   datoQueSeAlmacena;// escribe lo que  recibe  en el archivo
	
	FileReader archivoParaLectura;//
	
	BufferedReader buferParaLectura;//
	
	
	private ServerSocket server;  //socket del servidor
	private Socket connection; //conexi�n con cliente
	private int counter = 1; //contador del n�mero de conexiones
	private DataOutputStream output; //output stream al cliente
	private DataInputStream input; //input stream del cliente

	//configuraci�n y ejecuci�n del servidor
	
	public  String   insertar(){
		String salida="";
		try{
		
		
		String e= ""+ armas +"*"+ meritocracia+"+"+ recursos;
		
				// se realizan las  asignaciones de  los objetos 
	
		archivoEscritura= new FileWriter("archivo.txt") ;
		
		buferEscritura = new BufferedWriter(archivoEscritura) ;
		
		datoQueSeAlmacena= new PrintWriter(buferEscritura);
		
		datoQueSeAlmacena.println(e);
		
		datoQueSeAlmacena.flush();
		
		salida="se inserto  e el  archivo  ";
			   
		}   
			   
	
	catch(IOException e){salida= "error  al insertar"; 
	}
		
	return  salida;
	}
	public String  consulta (){
		
		String  salida=" ";
		String  temporal="";
		 try{
			 archivoParaLectura= new  FileReader ("archivo.txt");
			 buferParaLectura= new  BufferedReader(archivoParaLectura);
			 
		
		 while ((temporal=buferParaLectura.readLine())!=null ){
			 salida+=temporal+"\n";
			 
		 }
	}
		  	catch(IOException e){salida="error al analizar los requerimientos";
		 }
		 
	return  salida;
	}
	
	
			 
	
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
						sendData("Mensaje recibido");
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
		output = new DataOutputStream(connection.getOutputStream());
		output.flush();
		
		//configura un intput stream para objetos
		input = new DataInputStream(connection.getInputStream());
		
		
		System.out.println("Got I/O streams");
	}
	
	//procesa la conexi�n con el cliente
	public void processConnection() throws IOException{
		
		String message = "Connection successful"; 
		sendData(message); //env�a un mensaje de conexi�n exitosa al cliente
	
		message = (String) input.readUTF();
		System.out.println(message);
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
			
			output.writeUTF("Server >>>" + message);
			output.flush();	//manda el objeto al cliente por el output stream
		}
		catch (IOException ioException){
			
			System.out.println("Error writing object");
		}
	}
	
	//leer mensaje del cliente
	public void readData() throws IOException{
		
		String message = input.readUTF();
		System.out.println(message);
	}
}