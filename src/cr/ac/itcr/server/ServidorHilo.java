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

package cr.ac.itcr.server;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

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
    public void run() {
        String msg;
        File archivo = new File("texto.txt");
        while (true){
        	try{
	            msg = dis.readUTF();
		        if(msg!=null){
		        	System.out.println("Cliente #"+this.idSessio+" >>> "+msg);
		        	dos.writeUTF("Mensaje recibido");
		        	if (msg.equals("Registrarse")){
		        		registrarUsuario();	
		        	}
		        }
		    }
        	catch(Exception e){
 	       	}
        }
    }

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
    
    public void registrarUsuario(){
    	String dato = "";
    	JSONParser parser = new JSONParser();
    	try{
    		while(true){
    			dato = dis.readUTF();
    			String usuario ="";
    			String contraseña ="";
    			String contra = "";
    			if (dato.equals("Usuario")){
    				while(true){
    					usuario = dis.readUTF();
    					if (usuario!=null){
    						JSONObject user = new JSONObject();
    						user.put(usuario, null);
    						FileWriter escribir = new FileWriter("texto.txt");
    			    		BufferedWriter bw = new BufferedWriter(escribir);
    			    		PrintWriter pw = new PrintWriter(bw);
    			    		pw.write(user.toJSONString());
    			    		pw.close();
    			    		bw.close();
	    			    	while(true){ 
	    			    		contra = dis.readUTF();
	    			    		if (contra.equals("Contraseña")){
	    			    			while(true){
	    			    				contraseña = dis.readUTF();
	    			    				if (contraseña!=null){
		    			    				Object obj = parser.parse(new FileReader("texto.txt"));
		    					    		JSONObject asignaPass = (JSONObject) obj;
		    					    		asignaPass.put(usuario, contra);
		    					    		FileWriter escribir2 = new FileWriter("texto.txt");
		    					    		BufferedWriter bw2 = new BufferedWriter(escribir2);
		    					    		PrintWriter pw2 = new PrintWriter(bw2);
		    					    		pw2.write(asignaPass.toJSONString());
		    					    		bw2.newLine();
		    					    		pw2.close();
		    					    		bw2.close();
		    					    		break;
	    			    				}
	    			    			}
	    			    			break;
	    			    		}
	    			    	}
	    			    break;
    					}
    				}
    			break;
    			}
    		}
    	}
    	catch (Exception e){
    		
    	}
    }
}
