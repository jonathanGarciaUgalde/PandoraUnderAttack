package cr.ac.itcr.almacenamiento;

import org.json.simple.JSONObject;

public class FabJSON {

	public JSONObject original = new JSONObject();
	
	//M�todo para agregar un elemento al JSON
	public void addElement(String titulo, String elemento){
		original.put(titulo, elemento);
	}
	
	// M�todo para agregar un JSON a una lista
	public ListaDoble addJsonList(){
		ListaDoble lista = new ListaDoble();
		lista.agregarInicio(original.toJSONString());
		return lista;
	}
	
	//M�todo para agregar un elemento tipo lista a una lista
	public void addListJson(String cabecera,ListaDoble listajson){
		String datos=listajson.mostrarInicioFin();
		original.put(cabecera, datos);
		System.out.print(datos);
		System.out.println();
		System.out.println(original);
	}

	

}
