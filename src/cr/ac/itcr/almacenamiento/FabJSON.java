/**
 * clase   que  se encarga de  getionar  los  datos de tipo JSON  y   hace inserciones y obtenciones de estos  tipos de datos que han sido  creados.
 * 
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

public class FabJSON {

	public JSONObject original = new JSONObject();
	
	//Método para agregar un elemento al JSON
	
	/**
	 * este metodo se  encarga   insertar  al JSON 
	 * @param identificador   es un    parametro  de  tipo  String 
	 * @param elemento  es un parametro de  tipo String  que  al igual  que el identificador  será  asignado al JSON
	 */
	public void addElement(String identificador, String elemento){
		try {
			original.put(identificador, elemento);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// Método para agregar un JSON a una lista
	/**
	 * metodo  de  tipo listaDoble  que asigna el JSON  creado a una lista  doblemente  enlazada.
	 * @return retorna  la lista  enlazada  con los respectivos  elementos de tipo JSON  que  se  insertaron 
	 * 
	 */
	public ListaDoble enlistar(){
		ListaDoble lista = new ListaDoble();
		lista.agregarInicio(original.toString());
		return lista;
	}
	
/**
 * Este es un  metodo  que obtiene  el identificador  asignado a la lista doblemente enlazada
 * @return retorna  el identificador   que se   asigno al JSON.
 */

	public String getElement(String identificador){
		String elemento_obtenido = null;
		try {
			elemento_obtenido = (String) original.get(identificador);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return elemento_obtenido;
	}
	
	/**
	 * metodo que retorna   un arreglo d tipo JSONArray dependiendo de identificador que se haya creado.
	 * @return  retorna  un  arreglo  donde  ubica  al  identificador
	 */
	public JSONArray getArray(String identificador){
		JSONArray Array_obtenido = null;
		try {
			Array_obtenido = original.getJSONArray(identificador);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Array_obtenido;
	}

	/**
	 * metodo  que inserta  enla lista doblemente  enlazadda  el arreglo de tipo JSONArray
	 * 
	 */
	public void addArray(String identificador, JSONArray array) {
		try {
			original.put(identificador, array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
			
	}
	

}
