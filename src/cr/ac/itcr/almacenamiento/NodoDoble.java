package cr.ac.itcr.almacenamiento;

public class NodoDoble {
	public String dato;
	NodoDoble siguiente,anterior;
	
	//Constructor para cuando aun no hay nodos
	public NodoDoble(String ele){
		this(ele,null,null);
	}
	
	//Constructor para cuando ya hay nodos
	public NodoDoble(String ele, NodoDoble sig, NodoDoble ant) {
		dato=ele;
		siguiente=sig;
		anterior=ant;
	}
	
}
