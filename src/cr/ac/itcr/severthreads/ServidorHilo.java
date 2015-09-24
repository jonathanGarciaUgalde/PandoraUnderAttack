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
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void run() {
        String msg;
        File archivo = new File("texto.txt");
    	JSONObject json = new JSONObject();
        while (true){
        	try{
	            msg = dis.readUTF();
		        if(msg!=null){
		        	json.put("Mensaje",msg);
		    		FileWriter escribir = new FileWriter(archivo);
		    		escribir.write(json.toString()+" ");
		            System.out.println("Cliente #"+this.idSessio+" >>> "+msg);
		            dos.writeUTF("Mensaje recibido"); 
		   			escribir.close();
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
