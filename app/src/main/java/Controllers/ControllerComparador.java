package Controllers;

import java.util.ArrayList;

import Models.Capitulo;
import Models.Serie;
/**
 * Clase que comparara y devoldera listas
 * @author alvar
 *
 */
public class ControllerComparador {
	
	public ControllerComparador() {
		
	}
	
	/**
	 * Compara dos listas, la lista antigua con la lista nueva y devuelve una lista con los valores diferentes
	 * @param antigua
	 * @param nueva
	 * @return
	 */
	public ArrayList<Capitulo> comparacap(ArrayList<Capitulo> antigua,ArrayList<Capitulo> nueva){
		ArrayList<Capitulo> restante= new ArrayList<>();
		boolean encontrado=false;
		for (int i=0;i<nueva.size();i++) {
			for (int o=0;o<antigua.size();o++) {
				if (nueva.get(i).getSerie().equals(antigua.get(o).getSerie()) && encontrado==false) {
						encontrado=true;
					}
					
				} //fin bucle inerno
				if (encontrado == false ) {
					restante.add(nueva.get(i));
				}
				encontrado=false;	
					
			}
			
		return restante;
		
	}
	
	/**
	 * Compara dos listas, la lista antigua con la lista nueva y devuelve una lista con los valores diferentes
	 * @param antigua
	 * @param nueva
	 * @return
	 */
	public ArrayList<Serie> comparaserie(ArrayList<Serie> antigua,ArrayList<Serie> nueva){
		ArrayList<Serie> restante= new ArrayList<>();
		boolean encontrado=false;
		
		for (int i=0;i<nueva.size();i++) {
			for (int o=0;o<antigua.size();o++) {
				if (nueva.get(i).getNombre().equals(antigua.get(o).getNombre()) && encontrado==false) {
					encontrado=true;
				} 
				} //fin bucle interior
			if (encontrado==false) {
				restante.add(nueva.get(i));
			}
				encontrado=false;
			
			
			
			}
		return restante;
		
	}

}
