/**
 *Esta  es la clase  que se encarga de establecer los diferentes tipos de armas
 *     
 * @author Dilan  Castro Enriquez
 *@since 25/09/2015
 * 
 *@version  7.0
 * 
 * 
 * */
package cr.ac.itcr.ataque_defensa;

public class Armas {
	public int da�o;
	
	public void crearEspada(){
		Armas espada=new Armas();
		espada.da�o=200;
	}
	
	public void crearMazo(){
		Armas mazo=new Armas();
		mazo.da�o=400;
	}
	
	public void crearKatana(){
		Armas katana=new Armas();
		katana.da�o=500;
	}
	
	public void crearExcalibur(){
		Armas Excalibur=new Armas();
		Excalibur.da�o=1000;
	}
	
}
