package cr.ac.itcr.almacenamiento;

import org.json.JSONArray;
import org.json.JSONException;

public class ListaDoble {
	private NodoDoble inicio,fin;

	public ListaDoble(){
		inicio=fin=null;
	}
	
	//Método para saber cuando la lista está vacía
	public boolean estaVacia(){
		return inicio==null;
	}

	//Método para agregar nodos al final
	public void agregarFinal(String ele){
		if (!estaVacia()){
			fin= new NodoDoble(ele, null, fin);
			fin.anterior.siguiente=fin;
			}else{
				inicio=fin=new NodoDoble(ele);
			}
		}
	//Método para agregar nodos al inicio
	public void agregarInicio(String ele){
		if (!estaVacia()){
			inicio=new NodoDoble(ele, inicio, null);
			inicio.siguiente.anterior= inicio;
			}else{
				inicio=fin=new NodoDoble(ele);
			}
		}
	
	
	// Método para mostrar la lista de Inicio a Fin
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
	
	//Método para mostrar la lista de Fin a Inicio
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
	
	//Método para buscar un elemento
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
	

	//Método para borrar
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


