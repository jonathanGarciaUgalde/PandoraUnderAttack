package cr.ac.itcr.server;
import java.util.StringTokenizer;


public class ClaseAsignaRequerimientosArreglo {

	
	
	
	public int [] insertar(){
		 int datos[];
		 
// consulta si el server  crea recursos  y los separa con  un espacio				   
	String nombre="hola e e e e ee e e e e er r t t yu u  i i i ii i o k   v f   x x x x x x x x x x x x x xx  ";
				    StringTokenizer tokens=new StringTokenizer(nombre);
			        while(tokens.hasMoreTokens()){
			            System.out.println(tokens.nextToken());
			        }

				    String strDatos= "1+2+2+2+3+4+5+6+7+8+8+9+0+0+9+9+97+6+4+3";
				    tokens=new StringTokenizer(strDatos, "+");
			    int  nDatos=tokens.countTokens();
			       datos=new int  [nDatos];
			        int i=0;
			        while(tokens.hasMoreTokens()){
			            int elemento =Integer.parseInt( tokens.nextToken());
			            datos[i]=elemento;
			            System.out.println(datos[i]);
			            i++;
			        }

			        try  {
			//espera la pulsación de una tecla y luego RETORNO
			            System.in.read();
			        }
			        catch (Exception e) {  }
	
	return datos;
	}
	
	public static void main(String arv[]){
		ClaseAsignaRequerimientosArreglo c= new ClaseAsignaRequerimientosArreglo();
		System.out.println(c.insertar());
		
	}		        
			        }

	
	/**
	 * @param args
	 */
	

