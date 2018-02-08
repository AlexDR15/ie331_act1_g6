package act1;

/**
 * Esta clase permite comprobar si un Libro est� disponible para ser prestado o ya est� prestado.
 * Tambi�n tiene m�todos para devolver la informaci�n de sus atributos.
 * @author Alejandro Delgado, Alberto Gonz�lez y Daniel Rey
 * @version 1.0
 */
public class Libro {
	
	// Atributos
	
	/**
	 * isbn: n�mero de ISBN con guiones
	 * titulo: t�tulo del Libro
	 * autor: autor del Libro
	 */
	private String isbn, titulo, autor;
	
	/**
	 * Persona que actualmente tiene el libro prestado (0 si nadie lo tiene prestado)
	 */
	private int socio;
	
	/**
	 * Boolean que determina si el libro est� disponible para ser prestado
	 */
	private boolean accesoLibro=true;
	
	// Constructor
	
	/**
	 * Constructor de Libro
	 * @param isbn: se introduce el isbn del Libro
	 * @param titulo: se introduce el t�tulo del Libro
	 * @param autor: se introduce el autor del Libro
	 */
	public Libro(String isbn, String titulo, String autor) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor=autor;
		this.accesoLibro = true;
		this.socio = 0;
	}
	
	// M�todos
	
	/**
	 * M�todo para obtener el n�mero de carnet del Socio que tiene el Libro prestado
	 * @return n�mero de carnet (0 si nadie lo tiene prestado)
	 */
	public int getPoseedor() {
		return socio;
	}
	
	/**
	 * M�todo para comprobar si el Libro puede ser prestado y para cambiar su estado de accesoLibro;
	 * as� como para registrar qui�n va a tener el Libro prestado apartir de ahora.
	 * @param num_carnet del Socio que quiere coger el Libro prestado
	 * @return true: el Libro se prest� con �xito, cambiado el estado a "no poder ser prestado" y 
	 * estipular qui�n tom� el libro prestado. false: el Libro no pudo ser prestado.
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
	 * M�todo para comprobar si el Libro puede ser devuelto y para cambiar su estado de accesoLibro;
	 * as� como para registrar un 0 en el atributo "socio" para dejar claro que nadie tiene el libro
	 * prestado.
	 * @return true: el Libro se devolvi� con �xito, cambiado el estado a "accesible para ser 
	 * prestado" y estipular un "0" para dejar claro que est� en la Biblioteca. false: el Libro 
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
	 * M�todo para obtener el ISBN del Libro
	 * @return ISBN del Libro
	 */
	public String getISBN() {
		return isbn;
	}
	
	/**
	 * M�todo para obtener el T�tulo del Libro
	 * @return titulo del Libro
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * M�todo para obtener el Autor del Libro
	 * @return autor del Libro
	 */
	public String getAutor() {
		return autor;
	}
	
}
