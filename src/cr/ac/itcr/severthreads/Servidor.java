/**
 * Esta es  la clase  encarga de  de  levantar el servidor 
 *  implementar  los hilos
 *  
 *  
 *  @author Jonathan Garcia Ugalde
 * @author Gustavo Hernadez Granera
 * 
 *@since 11/09/2015
 * 
 *@version  7.0
 *
 */
package cr.ac.itcr.severthreads;

import java.io.*;
import java.net.*;
import java.util.logging.*;


public class Servidor {
    public static void main(String args[]) throws IOException {
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(8080);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}