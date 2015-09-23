/**
 *    En esta clase se llevar�  acabo la  cracion el servidor para  la  comunicaci�n con  el cliente y el envio de datos  segun la  solicitud y  segun el rango y  losa  recursos  que tenga  el solicitante
 *
 * @author Jonathan Garcia Ugalde
 * @author Gustavo Hernadez Granera
 * 
 *@since 11/09/2015
 * 
 *@version  7.0
 * 
 * 
 * */

package cr.ac.itcr.server;
//se importan  las diferentes  librerias necesarias  para el  funcionamiento
import  java .io.*;//  aqui  se importa  la libreria  donde   se va  a  formar los  archivos  txt
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;



public class Server {
	
	
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
	
	/**
	 * Metodo  que  se encarga de insetar un objeto, con los requerimientos que solicita el ciente del clan.
	 *  
	 *  
	*/
	public  String   insertar(){
		String salida="";
		
		try{
			archivoEscritura= new FileWriter("archivo.txt") ;
			
			buferEscritura = new BufferedWriter(archivoEscritura) ;
			
			datoQueSeAlmacena= new PrintWriter(buferEscritura);
			archivoEscritura= new FileWriter("archivo.txt") ;
			
			buferEscritura = new BufferedWriter(archivoEscritura) ;
			
			datoQueSeAlmacena= new PrintWriter(buferEscritura);
			
			
		for( int i=0;i<=500; i++){
		

		
				// se realizan las  asignaciones de  los objetos 
	
			
		datoQueSeAlmacena.println("ejemplo de  impresion  para ver que se almacena ");
		
		
		salida="se inserto  e el  archivo  ";
			   
		}   
		}	   
	
	catch(IOException e){salida= "error  al insertar"; 
	}
		
	return  salida;
	
	/**
	 * El metodo  consulta es el encargado de mostrar  el objeto que se cre� para el clan que solicit� algun requerimiento.
	 * @return retorna    los objetos  que se  solicitaron  en el servidos  para   el cliente. 
	*/
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
		  	catch(IOException e){salida="no se  puede mostrar  la informaci�n  del objeto  que se cre�";
		 }
		 
		 
	return  salida;
	}
	
	
	/**
	 *  runServer  :  es   el metodo  que  configura el  servidor para recibir  conexiones; procesa  conexiones 
	 *  en este  metodo  es donde   se crea  el socket del servidor
	*/
	
	public void runServer(){
		
		try{ 
			
			server = new ServerSocket(8080); // aqui  se crea un socket del servidor
				
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
						/**
						 * si no encuentra mensajes y hay menos de 5 conexiones
						 * vuelve a esperar por la conexi�n
						 *cuando encuentra otra conexi�n y la verifica vuelve a ejecutar
						 *el while para leer los mensajes que mand� el nuevo cliente
						 * 
						 */
						
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
	/**
	 * Metodo  que espera  que llegue  alguna conexi�n  y despliega la informacion de la conexi�n 
	 * 
	 * 
	 * @throws IOException  si  ocurriera un error  se  activa  una  excepci�n
	 */
	
	private void waitForConnection() throws IOException{
		
		System.out.println("Waiting for connection"); 
		connection = server.accept(); //permite al servidor aceptar una conexi�n
		System.out.println("Connection " + counter + " received from: " + connection.getInetAddress().getHostName());
		++counter;
	}
	
	
	
	
	/**
	 * El metodo getStremas  se encarga de abrir las v�as (streams) para enviar y recibir datos
	 
	 * @throws IOException
	 */
	 
	private void getStreams() throws IOException{
		
		//configura un output stream para objetos
		output = new DataOutputStream(connection.getOutputStream());
		output.flush();
		
		//configura un intput stream para objetos
		input = new DataInputStream(connection.getInputStream());
		
		
		System.out.println("Got I/O streams");
	}
	/**
	 * este  metodo se encarga de procesar  la conexi�n con el cliente 
	 * 
	 * @throws IOException
	 */
	
	public void processConnection() throws IOException{
		
		String message = "Connection successful"; 
		sendData(message); //env�a un mensaje de conexi�n exitosa al cliente
	
		message = (String) input.readUTF();
		System.out.println(message);
	}
	
	/**
	 * 
	 * 
	 * Metodo encargado de cerrar las v�as de comunicaci�n y el socket, termina el servidor
	 * @throws IOException
	 */
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
	
	/**
	 * 
	 *  Metodod que  envia   el mensaje  al cliente* @param message
	 */
	
	public void sendData(String message){
		
		try{ //env�a un objeto al cliente
			
			output.writeUTF("Server >>>" + message);
			output.flush();	//manda el objeto al cliente por el output stream
		}
		catch (IOException ioException){
			
			System.out.println("Error writing object");
		}
	}
	
	/**
	 *  El server mediante  este m�todo se encarga  de  leer el  mensaje del cliente
	 * @throws IOException
	 *
	 */
	public void readData() throws IOException{
		
		String message = input.readUTF();
		System.out.println(message);
	}

}