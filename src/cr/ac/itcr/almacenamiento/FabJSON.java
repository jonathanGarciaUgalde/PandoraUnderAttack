package cr.ac.itcr.almacenamiento;

import org.json.JSONException;
import org.json.JSONObject;

public class FabJSON {

	public JSONObject original = new JSONObject();
	
	//Método para agregar un elemento al JSON
	public void addElement(String titulo, String elemento){
		try {
			original.put(titulo, elemento);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	// Método para agregar un JSON a una lista
	public ListaDoble addJsonList(){
		ListaDoble lista = new ListaDoble();
		lista.agregarInicio(original.toString());
		return lista;
	}
	
	//Método para agregar un elemento tipo lista a una lista
	public void addListJson(String cabecera,ListaDoble listajson){
		String datos=listajson.mostrarInicioFin();
		try {
			original.put(cabecera, datos);
			System.out.print(datos);
			System.out.println();
			System.out.println(original);
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	

}
