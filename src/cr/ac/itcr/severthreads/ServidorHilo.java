package cr.ac.itcr.severthreads;

import java.io.*;
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
        String accion = "";
        while (true){
        	try{
	            accion = dis.readUTF();
		        if(accion!=null){
		            File archivo = new File("texto.txt");
		    		FileWriter escribir = new FileWriter(archivo,true);
		    		escribir.write(accion+" ");
		   			escribir.close();
		            System.out.println("Cliente #"+this.idSessio+" >>> "+accion);
		            dos.writeUTF("Mensaje recibido"); 
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
