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
	public int daño;
	
	public void crearEspada(){
		Armas espada=new Armas();
		espada.daño=200;
	}
	
	public void crearMazo(){
		Armas mazo=new Armas();
		mazo.daño=400;
	}
	
	public void crearKatana(){
		Armas katana=new Armas();
		katana.daño=500;
	}
	
	public void crearExcalibur(){
		Armas Excalibur=new Armas();
		Excalibur.daño=1000;
	}
	
}
