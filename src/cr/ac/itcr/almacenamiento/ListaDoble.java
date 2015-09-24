/**
 *Esta  es la clase  que se encarga de  crear   la lista doblemente enlazada, con sus metodos respectivos.
 *     
 * @author Dilan  castro Enriquez
 *@since 11/09/2015
 * 
 *@version  7.0
 * 
 * 
 * */

package cr.ac.itcr.almacenamiento;

import org.json.*;
import org.json.JSONArray;

public class ListaDoble {
	// se  declara  un nodo inicial de la lista  y uno final.
	private NodoDoble inicio,fin;

	public ListaDoble(){
		// le asigna por  defecto a los  nodos  creados  que apunteen a null
		inicio=fin=null;
	}
	
	/**
	 * metodo  que  analiza si la lista  esta  vacia,
	 * @return retorna  un  tipo de dato boolean   
	 * 
	 */
	public boolean estaVacia(){
		return inicio==null;
	}

	/**
	 * agregarFinal es   un  metodo  se  encarga  de   agregar  un elemto al final de  la lista.
	 * 
	 */
	public void agregarFinal(String ele){
		if (!estaVacia()){
			fin= new NodoDoble(ele, null, fin);// se hace  una instancia creando un nodo  al final de la lista. 
			fin.anterior.siguiente=fin;
			}else{
				inicio=fin=new NodoDoble(ele);
			}
		}
	/**
	 * agregarInicio es un metodo cua función es agregar un elemento al inicio de  la lista 
	 *  
	 */
	public void agregarInicio(String ele){
		if (!estaVacia()){//verifica que la lista no esté vacía 
			inicio=new NodoDoble(ele, inicio, null);
			inicio.siguiente.anterior= inicio;
			}else{
				inicio=fin=new NodoDoble(ele);
			}
		}
	
	
	/**
	 *  este  Método esta  encargado de mostrar todos  los elemento que tiene  la  lista desde  su inicio hasta el final de la misma 
	 * @return retorna  la lista entera de inicio  a fin 
	 */
	public String mostrarInicioFin(){
		String datos="<=>";
		if(!estaVacia()){
			NodoDoble auxiliar = inicio;
			while (auxiliar!=null){
				datos = datos + "{"+auxiliar.dato+"}"+"<=>";
				auxiliar=auxiliar.siguiente;
				
			}	
		}
		return datos;
	}
	
	/**
	 * este metodo  tiene  la funcion  de  mostrar  todos los elementos de la  lista de  una  forma  inversa    
	 * @return retorna  in String  con los elentos de la lista 
	 */
	public String mostrarFinInicio(){
		String datos="<=>";
		if(!estaVacia()){
			NodoDoble auxiliar = fin;
			while (auxiliar!=null){
				datos = datos + "{"+auxiliar.dato+"}"+"<=>";
				auxiliar=auxiliar.anterior;
				
			}		
		}
		return datos;
	}
	
	/**
	 *  la fun del Método  buscar  es tratar de encontrar en la lista el elemento que se desea encontrar
	 *  
	 * @return  si  se encuentra el  elemento se  retorna con un tipo de dato String 
	 */
	public String buscar(String dato) {
		NodoDoble buscado = null; 
		NodoDoble iterador = inicio;
		while ( buscado == null && iterador != null ) {
			if ( iterador.dato.compareTo(dato)==0 ) { 
				buscado = iterador; 
			} 
			iterador = iterador.siguiente; 
		 	}
		return buscado.dato;
	} 
	

	/**
	 *  la funcion de este metodo es borrar todos  los elementos de la lista y dejarla  vacia  para  volverla a utilizar.
	 *
	 */
	public void borrar(String dato){
		NodoDoble buscado = null; 
		NodoDoble iterador = inicio;
		if(inicio==fin){
			inicio=fin=null;
		}else{
			while ( buscado == null && iterador != null ) {
				if ( iterador.dato.compareTo(dato)==0) { 
					buscado = iterador; 
				} 
				iterador = iterador.siguiente; 
			 	}
			
			if (buscado!=fin && buscado!=inicio){
				buscado.anterior.siguiente=buscado.siguiente;
				buscado.siguiente.anterior=buscado.anterior;
			}
			if (buscado==inicio){
				inicio=inicio.siguiente;
				inicio.anterior=null;
			}if (buscado==fin){
				fin=fin.anterior;
				fin.siguiente=null;
				
				}
		}

		
	}
	
	/**
	 * metodo   se encarga de  asignar todos  los elemetos  de  los arreblos    JSONArray arrayString, JSONArray arrayNumeral   y asignarlos a una lista doblemente  enlazada   
	 * @return retorna una lista  enlazada de tipo String
	 * 
	 * @throws si  ocurre  algun tipo de error se  dispará  una excepción 
	 * 
	 */
	
	public ListaDoble enlistar_Array(JSONArray arrayString, JSONArray arrayNumeral ) throws JSONException{
		ListaDoble lista_array = new ListaDoble();
		if (arrayString == null && arrayNumeral!=null){
			for(int i=0; i < arrayNumeral.length(); i++){
				String dato = String.valueOf(arrayNumeral.getInt(i));
				lista_array.agregarInicio(dato);	
		}
		}if(arrayString!= null && arrayNumeral==null){
			for(int i=0; i < arrayString.length(); i++){
				lista_array.agregarInicio(arrayString.getString(i));
		}
		}
		return lista_array;
	}

}


