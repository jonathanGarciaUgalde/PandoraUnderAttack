 /**
  *  Esta   es la  clase encargada de  crear un hilo   para que se puedan conectar multiples  y este extiende  del hilo de conexion 
  *  clientes al servidor
  *  
  *  
  * @author Jonathan Garcia Ugalde
  * @author Gustavo Hernadez Granera

  *@since 11/09/2015
  * 
  *@version  7.0
  */

package cr.ac.itcr.severthreads;

import java.io.*;
import org.json.*;

import java.net.*;
import java.util.logging.*;

public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    
    //constructor  que inicializar el socket y asigna  el id al numero de sesión 
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * metodo que se   encarga de  cerrar todas las  conexiones  que se  realizaron en el socket  
     * 
     * 
     */
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * el metodo  run  mediante  el override sobresccribe la acción
     * y  hace la asignacion al  archivo y   se lo envia al cliente que hizo esa  solicitud  
     
     */
    @Override
    public void run() { //corre el servidor
        String msg; 
        File archivo = new File("texto.txt"); //crea el archivo .txt
    	JSONObject json = new JSONObject(); //crea un nuevo objeto de la clase JSONObject
        while (true){ //ciclo infinito
        	try{
	            msg = dis.readUTF(); //asigna a una variable lo que el cliente envía
		        if(msg!=null){ //condición de si el "buzón" de mensajes de entrada está vacío entonces...
		        	json.put("Mensaje",msg); //asigna al objetivo JSON dos datos
		    		FileWriter escribir = new FileWriter(archivo); //crea un objeto escribe en archivo creado 
		    		escribir.write(json.toString()+" "); //escribe el objeto JSON en el archivo como string
		            System.out.println("Cliente #"+this.idSessio+" >>> "+msg);
		            dos.writeUTF("Mensaje recibido"); //envía al cliente un mensaje de recibido
		   			escribir.close(); //cierra el escritor del archivo
		   			leer();
	                }
        	}
	       	catch(Exception e){
	       	}
        }
    } 
        //catch (IOException ex) {
            //Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        //}
        //desconnectar();
    //}
    
    
    
    public void leer(){
    	String texto =""; 
    	
    	try{
    		FileReader lector = new FileReader("texto.txt");
    		BufferedReader contenido=new BufferedReader(lector);
    		while((texto=contenido.readLine())!=null){
    			System.out.println(texto);
    		}
    	}
    	catch(Exception e){
    		System.out.println("Error al leer");
    	}
    }
}
