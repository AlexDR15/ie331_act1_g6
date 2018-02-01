package act1;

public class Libro {
	
	// Atributos
	
	private String isbn, titulo, autor;
	private boolean accesoLibro=true;
	
	// Constructor
	
	public Libro(String isbn, String titulo, String autor) {
		this.isbn=isbn;
		this.titulo=titulo;
		this.autor=autor;
	}
	
	// Métodos
	
	public boolean serTomado() {
		if (accesoLibro) {
			accesoLibro = false;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean serDevuelto() {
		if (!accesoLibro) {
			accesoLibro = true;
			return true;
		}else{
			return false;
		}
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	
}
