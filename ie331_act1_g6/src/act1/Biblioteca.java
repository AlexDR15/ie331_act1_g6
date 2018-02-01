package act1;

import java.util.ArrayList;

public class Biblioteca {
	
	// Atributos
	
	private ArrayList<Libro> totalLibros = new ArrayList<Libro>();
	private ArrayList<Socio> totalSocios = new ArrayList<Socio>();
	private Libro libro;
	private Socio socio;
	
	// Métodos
	
	public boolean addLibro(String isbn, String titulo, String autor) {
		if (totalLibros.indexOf(isbn) == -1) {
			this.libro= new Libro(isbn, titulo, autor);
			totalLibros.add(this.libro);
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean delLibro(String isbn) {
		if (isbn != "") {
			int idlibro = totalLibros.indexOf(isbn);
			if (idlibro > -1) {
				totalLibros.remove(idlibro);
				return true;
			}else{
				return false;
			}
		}else {
			return false;
		}
			
	}
	
	public boolean addSocio(String nombre) {
		if (nombre != "") {
			this.socio= new Socio(nombre);
			totalSocios.add(this.socio);
			return true;
		}else {
			return false;
		}
			
	}
	
	public boolean existeSocio(int num_carnet) {
		if (num_carnet > 100000) {
			int idsocio = totalSocios.indexOf(num_carnet);
			if (idsocio > -1) {
				return true;
			}else{
				return false;
			}
		}else {
			return false;
		}
	}
	
	public String borrarSocio(int num_carnet) {
		int idsocio = totalSocios.indexOf(num_carnet);
		Socio socio = totalSocios.get(idsocio);
		if (existeSocio(num_carnet)) {
			// Mostrar Libros:
			String msg = "Libros que tiene Actualmente el Socio: ";
			for (int i = 0; i < socio.libroActualmenteTomados().size(); i++) {
				msg = "\nLibro "+i+": "+socio.libroActualmenteTomados().get(i).getTitulo();	
			}
			
			// Establecer Libros del Socio como NO prestados
			for (int i = 0; i < socio.libroActualmenteTomados().size(); i++) {
				socio.devolverPrestamo(socio.libroActualmenteTomados().get(i));	
			}
			
			// Borrar Socio
			totalSocios.remove(idsocio);
			
			// Devuelve el MSG
			return msg;			
		}else{
			return "ERROR: El socio no existe";
		}
	}
	
	// Hemos tomado como referencia del libro el ISBN
	public String atenderPeticion(int num_carnet, String isbn) {
		int idlibro = totalLibros.indexOf(isbn);
		Libro libro = totalLibros.get(idlibro);
		int idsocio = totalSocios.indexOf(num_carnet);
		Socio socio = totalSocios.get(idsocio);
		if (idlibro == -1) {
			return "ERROR: Ese libro no existe en esta biblioteca";
		}else{
			if (totalLibros.get(idlibro).serTomado()) {
				if (socio.tomarPrestado(libro)) {
					return "Libro "+libro.getTitulo()+" prestado a "+socio.getNombre()+" satisfactoriamente.";
				}else {
					return "ERROR: No se ha podido prestar el Libro";
				}
				
			}else {
				return "ERROR: Ese libro ya está prestado a "+socio.getNombre();
			}
		}		
	}
	
}
