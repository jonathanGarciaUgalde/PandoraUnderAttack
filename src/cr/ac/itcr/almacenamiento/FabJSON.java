package cr.ac.itcr.almacenamiento;

import org.json.*;

public class FabJSON {

	public JSONObject original = new JSONObject();
	
	//Método para agregar un elemento al JSON
	public void addElement(String identificador, String elemento){
		try {
			original.put(identificador, elemento);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// Método para agregar un JSON a una lista
	public ListaDoble enlistar(){
		ListaDoble lista = new ListaDoble();
		lista.agregarInicio(original.toString());
		return lista;
	}
	


	public String getElement(String identificador){
		String elemento_obtenido = null;
		try {
			elemento_obtenido = (String) original.get(identificador);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return elemento_obtenido;
	}
	
	public JSONArray getArray(String identificador){
		JSONArray Array_obtenido = null;
		try {
			Array_obtenido = original.getJSONArray(identificador);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Array_obtenido;
	}

	public void addArray(String identificador, JSONArray array) {
		try {
			original.put(identificador, array);
		} catch (JSONException e) {
			e.printStackTrace();
		}
			
	}
	

}
