package cr.ac.itcr.almacenamiento;

import org.json.simple.JSONObject;

public class FabJSON {
	public JSONObject original = new JSONObject();
	
	public void addElement(String titulo, String elemento){
		original.put(titulo, elemento);
		System.out.print(original);
		
	
	}

	

}
