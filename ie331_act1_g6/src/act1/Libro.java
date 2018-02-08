package act1;

/**
 * Esta clase permite comprobar si un Libro está disponible para ser prestado o ya está prestado.
 * También tiene métodos para devolver la información de sus atributos.
 * @author Alejandro Delgado, Alberto González y Daniel Rey
 * @version 1.0
 */
public class Libro {
	
	// Atributos
	
	/**
	 * isbn: número de ISBN con guiones
	 * titulo: título del Libro
	 * autor: autor del Libro
	 */
	private String isbn, titulo, autor;
	
	/**
	 * Persona que actualmente tiene el libro prestado (0 si nadie lo tiene prestado)
	 */
	private int socio;
	
	/**
	 * Boolean que determina si el libro está disponible para ser prestado
	 */
	private boolean accesoLibro=true;
	
	// Constructor
	
	/**
	 * Constructor de Libro
	 * @param isbn: se introduce el isbn del Libro
	 * @param titulo: se introduce el título del Libro
	 * @param autor: se introduce el autor del Libro
	 */
	public Libro(String isbn, String titulo, String autor) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor=autor;
		this.accesoLibro = true;
		this.socio = 0;
	}
	
	// Métodos
	
	/**
	 * Método para obtener el número de carnet del Socio que tiene el Libro prestado
	 * @return número de carnet (0 si nadie lo tiene prestado)
	 */
	public int getPoseedor() {
		return socio;
	}
	
	/**
	 * Método para comprobar si el Libro puede ser prestado y para cambiar su estado de accesoLibro;
	 * así como para registrar quién va a tener el Libro prestado apartir de ahora.
	 * @param num_carnet del Socio que quiere coger el Libro prestado
	 * @return true: el Libro se prestó con éxito, cambiado el estado a "no poder ser prestado" y 
	 * estipular quién tomó el libro prestado. false: el Libro no pudo ser prestado.
	 */
	public boolean serTomado(int num_carnet) {
		if (accesoLibro) {
			socio = num_carnet;
			accesoLibro = false;
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Método para comprobar si el Libro puede ser devuelto y para cambiar su estado de accesoLibro;
	 * así como para registrar un 0 en el atributo "socio" para dejar claro que nadie tiene el libro
	 * prestado.
	 * @return true: el Libro se devolvió con éxito, cambiado el estado a "accesible para ser 
	 * prestado" y estipular un "0" para dejar claro que está en la Biblioteca. false: el Libro 
	 * no pudo ser devuelto.
	 */
	public boolean serDevuelto() {
		if (!accesoLibro) {
			socio = 0;
			accesoLibro = true;
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Método para obtener el ISBN del Libro
	 * @return ISBN del Libro
	 */
	public String getISBN() {
		return isbn;
	}
	
	/**
	 * Método para obtener el Título del Libro
	 * @return titulo del Libro
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Método para obtener el Autor del Libro
	 * @return autor del Libro
	 */
	public String getAutor() {
		return autor;
	}
	
}
