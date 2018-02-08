package act1;

import java.util.ArrayList;

/**
 * Esta clase permite realizar las funciones del Bibliotecario. Tales como mostrar la lista de
 * libros o socios, comprobar si existe un libro o socio, añadir/borrar libros o socios,
 * a obtener los objetos Libro o Socio necesitados y a atender las peticiones de los Socios de la 
 * Biblioteca.
 * @author Alejandro Delgado, Alberto González y Daniel Rey
 * @version 1.0
 */
public class Biblioteca {
	
	// Atributos
	
	/**
	 * Colección total de Libros de la Biblioteca (prestados o no)
	 */
	private ArrayList<Libro> totalLibros = new ArrayList<Libro>();
	
	/**
	 * Colección total de Socios de la Biblioteca
	 */
	private ArrayList<Socio> totalSocios = new ArrayList<Socio>();
	
	/**
	 * Libro utilizado en los métodos de esta clase
	 */
	private Libro libro;
	
	/**
	 * Socio utilizado en los métodos de esta clase
	 */
	private Socio socio;
	
	// Métodos
	
	/**
	 * Método para mostrar todos los libros registrados en la Biblioteca
	 * @return msg: String que muestra los libros de la Biblioteca
	 */
	public String librosRegistrados(){
		Libro libro;
		String msg = "";
		// Recorrer Array de Libros
		for (int i = 0; i < totalLibros.size(); i++) {
			libro = totalLibros.get(i);
			msg += " - "+libro.getTitulo()+" ("+libro.getAutor()+") [ISBN: "+libro.getISBN()+"]";
			if (libro.getPoseedor() != 0) {
				msg += " --> Prestado a "+libro.getPoseedor();
			}
			msg += "\n";
		}
		
		return msg;
	}
	
	/**
	 * Método para mostrar todos los socios registrados en la Biblioteca
	 * @return msg: String que muestra los libros  de la Biblioteca
	 */
	public String sociosRegistrados(){
		Socio socio;
		String msg = "";
		// Recorrer Array de Socios
		for (int i = 0; i < totalSocios.size(); i++) {
			socio = totalSocios.get(i);
			msg += " - "+socio.getNombre()+" ("+socio.getNumCarnet()+")";
			msg += "\n";
		}
		
		return msg;
	}
	
	/**
	 * Método utilizado para crear un Libro y meterlo en el Array de Libros de la Biblioteca
	 * @param isbn: isbn del Libro
	 * @param titulo: título del Libro
	 * @param autor: autor del Libro
	 * @return true: añade correctamente el Libro en el array utilizando el Constructor del 
	 * Libro para crearlo (y comprobando antes que no hay otro libro con el mismo ISBN). 
	 * false: no puede crear el Libro.
	 */
	public boolean addLibro(String isbn, String titulo, String autor) {
		if (existeLibro(isbn) == -1) {
			libro = new Libro(isbn, titulo, autor);
			totalLibros.add(libro);
			return true;
		}else{
			return false;
		}	
	}
	
	/**
	 * Método para comprobar si existe el Libro en el Array de "totalLibros" y obtener la posición
	 * del Libro en el ArrayList.
	 * @param isbn: ISBN del Libro
	 * @return respuesta: posición del Libro introducido (vía ISBN) en el Array. Si no está dará un
	 * -1.
	 */
	public int existeLibro(String isbn) {
		int respuesta;
		if (isbn.length() == 17) {
			
			if (totalLibros.size() == 0) {
				respuesta = -1;
			}else {
				int i = 0;
				do {
					if (isbn.equals(totalLibros.get(i).getISBN())) {
						respuesta = i;
						i = totalLibros.size();
					}else{
						respuesta = -1;
					}
					i++;
				}while(i < totalLibros.size());
			}
									
		}else {
			respuesta = -1;
		}
		return respuesta;
	}
	
	/**
	 * Método para sacar el Libro concreto deseado
	 * @param posicion: posición del Libro en el array "totalLibros".
	 * @return Libro deseado
	 */
	public Libro libroEscogido(int posicion){		
		return totalLibros.get(posicion);
	}
	
	/**
	 * Método para eliminar Libro. Comprueba que el Libro no está prestado y luego lo borra.
	 * @param isbn: ISBN del Libro.
	 * @return true: Libro borrado con éxito (también se elimina del Array de "totalLibros" de sus
	 * libros prestados actualmente. false: no pudo borrarse el Libro.
	 */
	public boolean borrarLibro(String isbn) {
		if (isbn.length() == 17) {
			int posicion = existeLibro(isbn);
			if (posicion > -1) {
				if (totalLibros.get(posicion).getPoseedor() == 0) {
					totalLibros.remove(posicion);
					return true;
				}else {
					return false;
				}				
			}else{
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * Método para añadir Socio y meterlo en el Array de Socios de la Biblioteca
	 * @param nombre: nombre del Socio
	 * @return devuelve el número de carnet del Socio
	 */
	public int addSocio(String nombre) {
		if (nombre != "") {
			socio = new Socio(nombre);
			totalSocios.add(socio);
			return socio.getNumCarnet();
		}else {
			return 0;
		}
			
	}
	
	/**
	 * Método para comprobar si existe el Socio en el Array de "totalSocios" y obtener la posición
	 * del Socio en el ArrayList.
	 * @param num_carnet: Identificación del Socio
	 * @return respuesta: posición del Socio introducido (vía número de carnet) en el Array. 
	 * Si no está dará un -1.
	 */
	public int existeSocio(int num_carnet) {
		int respuesta;
		if (num_carnet > 100000) {
			int i = 0;
			do {
				if (num_carnet == totalSocios.get(i).getNumCarnet()) {
					respuesta = i;
					i = totalSocios.size();
				}else{
					respuesta = -1;
				}
				i++;
			}while(i < totalSocios.size());
						
		}else {
			respuesta = -1;
		}
		return respuesta;
	}
	
	/**
	 * Método para sacar el Socio concreto deseado
	 * @param posicion: posición del Socio en el array "totalSocios".
	 * @return Socio deseado
	 */
	public Socio socioEscogido(int posicion){		
		return totalSocios.get(posicion);
	}
	
	/**
	 * Método para eliminar Socio. 
	 * @return true: Libro borrado con éxito (también se elimina del Array de "totalLibros" de sus
	 * libros prestados actualmente. false: no pudo borrarse el Libro.
	 * @param num_carnet: número de carnet del Socio
	 * @return msg: da un String de los libros que tiene el Socio antes de ser borrado (o en su
	 * defecto anuncia que no tenía libros prestados).
	 */
	public String borrarSocio(int num_carnet) {
		int idsocio = existeSocio(num_carnet);
		socio = socioEscogido(idsocio);
		if (existeSocio(num_carnet) >= 0) {
			
			String msg = "";
			if (socio.libroActualmenteTomados().size() > 0) {
				
				// Mostrar Libros:
				msg += "Libros que tiene Actualmente el Socio: ";
				for (int i = 0; i < socio.libroActualmenteTomados().size(); i++) {
					msg += "\n - Libro "+i+": "+socio.libroActualmenteTomados().get(i).getTitulo();	
				}
				
				// Establecer Libros del Socio como NO prestados
				do {
					socio.devolverPrestamo(socio.libroActualmenteTomados().get(0));
				}while(socio.libroActualmenteTomados().size() > 0);
				
			}else {
				msg += "No tiene libros en prestamo...";
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
	/**
	 * Método para prestar Libros al Socio (comprobando si existe el Libro y si nadie lo tiene
	 * actualemente prestado)
	 * @param tomar: true para tomar el libro y false para devolverlo
	 * @param num_carnet: número de carnet del Socio
	 * @param isbn: ISBN del Libro
	 * @return distintos Strings para decir que se ha prestado al Socio o para mostrar los 
	 * distintos errores cuando no se le pueda prestar el Libro
	 */
	public String atenderPeticion(boolean tomar, int num_carnet, String isbn) {
		Libro libro = totalLibros.get(existeLibro(isbn));
		Socio socio = totalSocios.get(existeSocio(num_carnet));
		if (existeLibro(isbn) == -1) {
			return "ERROR: Ese libro no existe en esta biblioteca";
		}else{
			if (tomar) {
				if (socio.tomarPrestado(libro)) {
					return "Libro "+libro.getTitulo()+" prestado a "+socio.getNombre()+" satisfactoriamente.";
				}else {
					int poseedor = libro.getPoseedor();
					return "ERROR: No se ha podido prestar el Libro. Ya está prestado al socio "+totalSocios.get(existeSocio(poseedor)).getNombre()+" ("+poseedor+")";
				}
			}else {
				if (socio.devolverPrestamo(libro)) {
					return "Libro "+libro.getTitulo()+" devuelto por "+socio.getNombre()+" satisfactoriamente.";
				}else {
					return "ERROR: Ese libro NO se te ha prestado";
				}
			}
		}		
	}
	
}
