package act1;

import java.util.ArrayList;

public class Socio {
	
	// Atributos
		
	private static int num_carnet = 100000;
	private String nombre;
	private ArrayList<Libro> librosTomados = new ArrayList<Libro>();
	
	// Constructor
	
	public Socio(String nombre) {
		this.num_carnet = ++num_carnet;
		this.nombre = nombre;
	}
	
	// Métodos
	
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Libro> libroActualmenteTomados(){
		return librosTomados;
	}
	
	public boolean tomarPrestado(Libro librotomado) {
		if (librotomado.serTomado()) {
			librosTomados.add(librotomado);
			return true;
		}else {
			return false;
		}	
	}
	
	public boolean devolverPrestamo(Libro librotomado) {
		if (librotomado.serDevuelto()) {
			librosTomados.remove(librotomado);
			return true;
		}else {
			return false;
		}			
	}
	
}
