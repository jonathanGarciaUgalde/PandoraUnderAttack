/**
 *Esta  es la clase  que se encarga de establecer los diferentes tipos de escudos
 *     
 * @author Dilan  Castro Enriquez
 *@since 25/09/2015
 * 
 *@version  7.0
 * 
 * 
 * */
package cr.ac.itcr.ataque_defensa;

public class Escudo {
	public int resistencia;

	public void crearEscudo_madera(){
		Escudo escudo_madera= new Escudo();
		escudo_madera.resistencia=500;
	}
	
	public void crearCampo_Fuerza(){
		Escudo campo_fuerza= new Escudo();
		campo_fuerza.resistencia=850;
	}
	
	public void crearEscudo_Supremo(){
		Escudo escudo_supremo= new Escudo();
		escudo_supremo.resistencia=2500;
	}
}
