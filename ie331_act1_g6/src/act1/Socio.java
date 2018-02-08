package act1;

import java.util.ArrayList;

/**
 * Esta clase permite realizar las funciones que har�a el Socio de una Biblioteca en relaci�n a
 * retirar o devolver Libros. Tambi�n tiene m�todos para devolver la informaci�n de sus atributos.
 * @author Alejandro Delgado, Alberto Gonz�lez y Daniel Rey
 * @version 1.0
 */
public class Socio {
	
	// Atributos
	
	/**
	 * Contador est�tico para determinar el n�mero de carnet del Socio
	 */
	private static int socioconta = 100001;
	
	/**
	 * N�mero de carnet del Socio
	 */
	private int num_carnet;
	
	/**
	 * Nombre (y apellidos) del Socio
	 */
	private String nombre;
	
	/**
	 * Colecci�n de libros actualmente tomados prestados por el Socio
	 */
	private ArrayList<Libro> librosTomados = new ArrayList<Libro>();
	
	// Constructor
	
	/**
	 * Constructor de Socio
	 * @param nombre: se introduce el nombre del socio
	 */
	public Socio(String nombre) {
		this.num_carnet = socioconta;
		this.nombre = nombre;
		socioconta++;
	}
	
	// M�todos
	
	/**
	 * M�todo para obtener el nombre del Socio
	 * @return nombre del Socio
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * M�todo para obtener el n�mero de carnet del Socio
	 * @return n�mero de carnet del Socio
	 */
	public int getNumCarnet() {
		return num_carnet;
	}
	
	/**
	 * M�todo para obtener el array de libros actualmente prestados al Socio
	 * @return array de libros tomados
	 */
	public ArrayList<Libro> libroActualmenteTomados(){
		return librosTomados;
	}
	
	/**
	 * M�todo para tomar prestado un Libro
	 * @param librotomado: introduce el objeto Libro que quiere retirar el Socio
	 * @return true: el libro fue prestado con �xito (tambi�n se a�ade al array del Socio de sus
	 * libros prestados actualmente). false: no se pudo prestar el libro.
	 */
	public boolean tomarPrestado(Libro librotomado) {
		if (librotomado.serTomado(num_carnet)) {
			librosTomados.add(librotomado);
			return true;
		}else {
			return false;
		}	
	}
	
	/**
	 * M�todo para devolver un Libro
	 * @param librotomado: introduce el objeto Libro que quiere retirar el Socio
	 * @return true: se devuelve el Libro con �xito (tambi�n se elimina del Array del Socio de sus
	 * libros prestados actualmente). false: no se pudo devolver el libro
	 */
	public boolean devolverPrestamo(Libro librotomado) {
		int i = 0;
		boolean respuesta = false;
		if (librosTomados.size() > 0) {
			do {
				if(librotomado == librosTomados.get(i)) {
					
					if (librotomado.serDevuelto()) {
						librosTomados.remove(librotomado);
						respuesta = true;
					}
					
					i = librosTomados.size();
				}
				i++;
			}while(i < librosTomados.size());
		}
		
		return respuesta;					
	}
	
}
