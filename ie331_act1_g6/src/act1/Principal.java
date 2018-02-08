package act1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Esta clase es la Principal dónde se van a desarrollar los métodos de las otras clases a través de 
 * una serie de menús y submenús.
 * @author Alejandro Delgado, Alberto González y Daniel Rey
 * @version 1.0
 */
public class Principal {
	
	/**
	 * Crear un objeto BufferedReader para utilizar la entrada de datos mediante teclado
	 */
	static BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));
	
	/**
	 * Variable int utilizada en los menús
	 */
	static int opcion = 0;
	
	/**
	 * Variable booleana utilizada para prevenir la mala introducción de datos
	 */
	static boolean opcionbien;
	
	/**
	 * Biblioteca instanciada para poder utilzar todos sus métodos
	 */
	static Biblioteca biblioteca = new Biblioteca();
	
	/**
	 * Socio utilizado en distintos métodos de esta clase
	 */
	static Socio socio;
	
	// BIBLIOTECARIO #07 (Eliminar Libro - Pedir ISBN)
	
	/**
	 * Método para desarrollar el menú usado para eliminar Libros
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_libros_del() throws NumberFormatException, IOException {
		
		// Recoger ISBN Libro
		String isbn = "-1";
		do {
			opcionbien = true;
			System.out.print("\nIntroduzca el ISBN (Con Guiones) del Libro a Eliminar (0 para Cerrar el Programa o 1 para Volver Atrás): ");
			
			isbn = teclado.readLine();
			
			if (isbn.equals("0") || isbn.equals("1") || (isbn.length() == 17 && biblioteca.existeLibro(isbn) != -1)) {
				opcionbien = true;
			}else {
				System.out.println("No se ha introducido un ISBN válido");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (isbn.equals("0")) {
			System.exit(0);
		}else if (isbn.equals("1")) {
			bibliotecario_admin_libros_menu();
		}else if (biblioteca.existeLibro(isbn) != -1){
			System.out.println("Eliminando Libro...");
			
			// Proceso de Eliminar Socio
			if (biblioteca.borrarLibro(isbn)) {
				System.out.println("¡Libro Eliminado con Éxito!");
				bibliotecario_admin_libros_menu();
			}else {
				System.out.println("ERROR: No se ha podido eliminar el libro. Por favor, inténtelo de nuevo...");
				bibliotecario_admin_libros_del();
			}

		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			bibliotecario_admin_libros_del();
		}
		
	}

	// BIBLIOTECARIO #06 (Añadir Nuevo Libro - Pedir Todos los Datos)
	
	/**
	 * Método para desarrollar el menú usado para añadir Libros
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_libros_add() throws NumberFormatException, IOException {
		
		System.out.print("\nPara registrar un Libro debe introducir los siguientes datos (En cualquiera de los campos, 0 para Cerrar el Programa o 1 para Volver Atrás): ");

		// Recoger ISBN del libro
		long isbn = -1;
		do {
			opcionbien = true;
			System.out.print("\n - ISBN del Libro (Sin guiones): ");
			
			try{
				isbn = Long.parseLong(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (!opcionbien || isbn == -1) {
				System.out.println("Debe Introducir un ISBN (Recordatorio: Sin guiones)...");
				opcionbien = false;
			}			
		}while(!opcionbien);
		
		if (isbn == 0) {
			System.exit(0);
		}else if (isbn == 1) {
			bibliotecario_admin_libros_menu();
		}else if (isbn == -1 || String.valueOf(isbn).length() != 13){
			System.out.println("Debe Introducir un ISBN...");
			bibliotecario_admin_libros_add();
		}else {
			
			// Recoger Titulo del Libro
			String titulo = "";
			System.out.print("\n - Título del Libro: ");
			titulo = teclado.readLine();
			
			if (titulo.equals("0")) {
				System.exit(0);
			}else if (titulo.equals("1")) {
				bibliotecario_admin_libros_menu();
			}else if (titulo.equals("")){
				System.out.println("Debe Introducir un Nombre...");
				bibliotecario_admin_libros_add();
			}else {
				
				// Recoger Autor Libro
				String autor = "";
				System.out.print("\n - Autor/es del Libro: ");
				autor = teclado.readLine();
				
				if (autor.equals("0")) {
					System.exit(0);
				}else if (autor.equals("1")) {
					bibliotecario_admin_libros_menu();
				}else if (autor.equals("")){
					System.out.println("Debe Introducir uno o varios Autores...");
					bibliotecario_admin_libros_add();
				}else {
					
					// Convirtiendo ISBN en String:
					String isbnbien, isbnfin;
					isbnbien = String.valueOf(isbn);
					isbnfin = isbnbien.substring(0,3)+"-"+isbnbien.substring(3,4)+"-"+isbnbien.substring(4,6)+"-"+isbnbien.substring(6,12)+"-"+isbnbien.substring(12);
										
					// Proceso de Registro del Libro
					System.out.println("Resgistrando Libro ("+isbnfin+")...");
					if (biblioteca.addLibro(isbnfin, titulo, autor)) {
						System.out.println("¡Libro Registrado con Éxito!");
					}else {
						System.out.println("No se ha podido registrar ese libro. Compruebe que no exista ya en la Biblioteca...");
					}
					bibliotecario_admin_libros_menu();
					
				}	
				
			}
			
		}
		
	}
	
	// BIBLIOTECARIO #05 (Opciones de Adminsitración de Libros para el Bibliotecario)
	
	/**
	 * Método para desarrollar el menú usado para que el Bibliotecario haga distintas funciones
	 * con los Libros
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_libros_menu() throws NumberFormatException, IOException {
		
		// Opciones de Adminsitración Libros:
		do {
			opcionbien = true;
			System.out.print("\n¿Qué desea hacer sobre los Libros? ");
			System.out.print("\n1. Añadir Libros \n2. Eliminar Libros \n3. Ver Libros Registrados \n4. Atras (Volver al Menú de Administración General) \n0. Cerrar Sistema\n");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion >= 0 && opcion <= 4)) {
				opcionbien = true;
			}else {
				System.out.println("La opción introducida no es válida");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.out.println("¡Adios!");
			System.exit(0);
		}else if(opcion == 1) {
			// Añadir Libro (Llevar a pedir todos los Datos)
			bibliotecario_admin_libros_add();
		}else if(opcion == 2) {
			// Eliminar Libro (Pedir el ISBN)
			bibliotecario_admin_libros_del();
		}else if(opcion == 3) {
			System.out.println("Libros Registrados: \n"+biblioteca.librosRegistrados());
			bibliotecario_admin_libros_menu();
		}else if(opcion == 4) {
			System.out.println("Volviendo al Menú de Administración...");
			bibliotecario_logged_menu();
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			bibliotecario_admin_libros_menu();
		}
		
	}
	
	// BIBLIOTECARIO #04 (Eliminar Socio - Pedir Num_Carnet)
	
	/**
	 * Método para desarrollar el menú usado para eliminar Socios
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_socios_del() throws NumberFormatException, IOException {
		
		// Recoger Num_Carnet Socio
		do {
			opcionbien = true;
			System.out.print("\nIntroduzca el Numero de Carnet del Socio a Eliminar (0 para Cerrar el Programa o 1 para Volver Atrás): ");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion == 0 || opcion == 1 || biblioteca.existeSocio(opcion) != -1)) {
				opcionbien = true;
			}else {
				System.out.println("No se ha introducido un numero de carnet válido");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.exit(0);
		}else if (opcion == 1) {
			bibliotecario_admin_socios_menu();
		}else if (opcion > 100000 && opcion < 1000000 && biblioteca.existeSocio(opcion) != -1){
			System.out.println("Eliminando Socio...");
			// Proceso de Añadir Socio
			System.out.println(biblioteca.borrarSocio(opcion));
			bibliotecario_admin_socios_menu();
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			bibliotecario_admin_socios_del();
		}
		
	}

	// BIBLIOTECARIO #03 (Añadir Nuevo Socio - Pedir Nombre)
	
	/**
	 * Método para desarrollar el menú usado para añadir Socios
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_socios_add() throws NumberFormatException, IOException {
		
		// Recoger Nombre Socio
		String nombre;
		System.out.print("\nIntroduzca el Nombre del Socio a Registrar (0 para Cerrar el Programa o 1 para Volver Atrás): ");
		nombre = teclado.readLine();
		
		if (nombre.equals("0")) {
			System.exit(0);
		}else if (nombre.equals("1")) {
			bibliotecario_admin_socios_menu();
		}else if (nombre.equals("")){
			System.out.println("Debe Introducir un Nombre...");
			bibliotecario_admin_socios_add();
		}else {
			
			// Proceso de Añadir Socio
			int num_carnet = biblioteca.addSocio(nombre);
			if (num_carnet != 0) {
				System.out.println("¡Socio Añadido con Éxito!"); 
				System.out.println("Datos del Socio: \n - Numero de Carnet: "+num_carnet+"\n - Nombre: "+nombre);
				System.out.println("Llevando al Menú de Administración de Socios...");
				bibliotecario_admin_socios_menu();
			}else {
				System.out.println("ERROR: Se ha producido un error inesperado.");
				bibliotecario_admin_socios_add();
			}
		}
		
	}
	
	// BIBLIOTECARIO #02 (Opciones de Adminsitración de Socios para el Bibliotecario)
	
	/**
	 * Método para desarrollar el menú usado para que el Bibliotecario haga distintas funciones
	 * con los Socios
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_admin_socios_menu() throws NumberFormatException, IOException {
		
		// Opciones de Adminsitración Socio:
		do {
			opcionbien = true;
			System.out.print("\n¿Qué desea hacer sobre los Socios? ");
			System.out.print("\n1. Añadir Socios \n2. Eliminar Socio \n3. Ver Lista de Socios Registrados \n4. Atras (Volver al Menú de Administración General) \n0. Cerrar Sistema\n");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion >= 0 && opcion <= 4)) {
				opcionbien = true;
			}else {
				System.out.println("La opción introducida no es válida");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.out.println("¡Adios!");
			System.exit(0);
		}else if(opcion == 1) {
			// Añadir Socio (Llevar a pedir el Nombre)
			bibliotecario_admin_socios_add();
		}else if(opcion == 2) {
			// Eliminar Socio (Pedir el num_carnet)
			bibliotecario_admin_socios_del();
		}else if(opcion == 3) {
			System.out.println("Socios Regsitrados: \n"+biblioteca.sociosRegistrados());
			bibliotecario_admin_socios_menu();
		}else if(opcion == 4) {
			System.out.println("Volviendo al Menú de Administración...");
			bibliotecario_logged_menu();
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			bibliotecario_admin_socios_menu();
		}
		
	}	
	
	// BIBLIOTECARIO #01 (Opciones para el Bibliotecario Identificado (Adminsitrar o Libros o Socios))
	
	/**
	 * Método para desarrollar el menú usado para que el Bibliotecario elija si quiere trabajar
	 * con Socios o con Libros
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void bibliotecario_logged_menu() throws NumberFormatException, IOException {
		
		// Opciones de Socio:
		do {
			opcionbien = true;
			System.out.print("\n¿Qué desea hacer? ");
			System.out.print("\n1. Administrar Socios \n2. Administrar Libros \n3. Desconectarse (Salir de la cuenta de Bibliotecario) \n0. Cerrar Sistema\n");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion >= 0 && opcion <= 3)) {
				opcionbien = true;
			}else {
				System.out.println("La opción introducida no es válida");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.out.println("¡Adios!");
			System.exit(0);
		}else if(opcion == 1) {
			// Llevar al menu para Administrar Socios
			bibliotecario_admin_socios_menu();
		}else if(opcion == 2) {
			// Llevar al menu para Administrar Libros
			bibliotecario_admin_libros_menu();
		}else if(opcion == 3) {
			System.out.println("Desconectado! Yendo al Menú Principal...");
			inicio();
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			bibliotecario_logged_menu();
		}
		
	}
	
	// SOCIO #02 (Opciones para el Socio Identificado)
	
	/**
	 * Método para desarrollar el menú usado para que el Socio haga distintas funciones
	 * con los Libros
	 * @param num_carnet: Identificador del Usuario (Obligatorio para controlar sobre que Socio se realizan las acciones)
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void socio_logged_menu(int num_carnet) throws NumberFormatException, IOException {
		// Opciones de Socio:
		do {
			opcionbien = true;
			System.out.print("\n¿Qué desea hacer? ");
			System.out.print("\n1. Tomar Prestado Libro \n2. Devolver Libro \n3. Ver Libros En Prestamo \n4. Desconectarse (Salir de la cuenta de Socio) \n0. Cerrar Sistema\n");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion >= 0 && opcion <= 4)) {
				opcionbien = true;
			}else {
				System.out.println("La opción introducida no es válida");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.out.println("¡Adios!");
			System.exit(0);
		} else if(opcion == 3) {
			
			System.out.println("Libros Tomados Actualmente: ");
			for (int i = 0; i < socio.libroActualmenteTomados().size(); i++) {
				System.out.println("- "+socio.libroActualmenteTomados().get(i).getTitulo()+" ("+socio.libroActualmenteTomados().get(i).getAutor()+") [ISBN: "+socio.libroActualmenteTomados().get(i).getISBN()+"]");
			}
			socio_logged_menu(num_carnet);
			
		}else if(opcion == 4) {
			System.out.println("Saliendo de su cuenta...");
			inicio();
		}else {

			
			// Petición ISBN (13 nums y 4 guiones)(ISBN 978-3-16-148410-0)			
			String isbn = "";
			do {
				opcionbien = true;
				System.out.print("\nIntroduzca el ISBN (Con Guiones) del Libro (1 para Volver Atrás): ");
				isbn = teclado.readLine();
				
				if (opcionbien && !isbn.equals("")) {
					opcionbien = true;
				}else {
					System.out.println("ERROR: El ISBN introducido no es correcto");
					opcionbien = false;
				}
				
			}while(!opcionbien);
			
			if (isbn.equals("1")) {
				socio_logged_menu(num_carnet);
			}else if (biblioteca.existeLibro(isbn) != -1) {
				if (opcion == 1) {
					// Tomar Prestado
					System.out.println(biblioteca.atenderPeticion(true, num_carnet, isbn));
					socio_logged_menu(num_carnet);
				}else if(opcion == 2) {
					// Devolver Libro
					System.out.println(biblioteca.atenderPeticion(false, num_carnet, isbn));
					socio_logged_menu(num_carnet);
				}else {
					System.out.println("ERROR: Se ha producido un error inesperado");
					socio_logged_menu(num_carnet);
				}
			}else {
				System.out.println("ERROR: El libro introducido no existe");
				socio_logged_menu(num_carnet);
			}
																		
		}
		
	}
	
	// SOCIO #01 (Introducir Num_carnet)
	
	/**
	 * Método para desarrollar el menú usado para que el Socio se registre utilizando su número
	 * de carnet
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void socio() throws NumberFormatException, IOException {
		// Socio
		do {
			opcionbien = true;
			System.out.print("\nIntroduzca su Numero de Carnet de Socio (0 para Cerrar el Programa o 1 para Volver al Menú de Identificarse): ");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion == 0 || opcion == 1 || (opcion > 100000 && opcion < 1000000))) {
				opcionbien = true;
			}else {
				System.out.println("No se ha introducido un numero de carnet válido");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		if (opcion == 0) {
			System.exit(0);
		}else if (opcion == 1) {
			inicio();
		}else if(opcion > 100000 && opcion < 1000000) {
			
			int num_carnet = opcion;
						
			// Comprobación de la existencia de ese num_carnet
			int sociopos = biblioteca.existeSocio(num_carnet);
			if (sociopos > -1) {
				
				socio = biblioteca.socioEscogido(sociopos);
				
				// Socio Identificado
				System.out.println("¡Bienvenido "+socio.getNombre()+" ("+num_carnet+")!");
				
				socio_logged_menu(num_carnet);
				
			} else {
				System.out.println("ERROR: No se ha registrado en el Sistema. Contacte con un Bibliotecario.");
				socio();
			}
			
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			socio();
		}
		
	}
	
	// INICIO (Menu Principal)
	
	/**
	 * Método para desarrollar el menú de inicio de sesión dónde tendremos la opción de
	 * identificarnos como Bibliotecario o como Socio
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void inicio() throws NumberFormatException, IOException {
				
		do {
			opcionbien = true;
			System.out.println("\n¿Cómo desea Identificarse?");
			System.out.println("1. Bibliotecario (Admin) \n2. Socio (Solo YA registrados en el Sistema)");
			
			try{
				opcion = Integer.parseInt(teclado.readLine());
			}catch(NumberFormatException ex) {
				opcionbien = false;
			}
			
			if (opcionbien && (opcion == 2 || opcion == 1)) {
				opcionbien = true;
			}else {
				System.out.println("No se ha escrito una opción válida");
				opcionbien = false;
			}
			
		}while(!opcionbien);
		
		// Identificando
		if (opcion == 1) {
			
			// Bibliotecario
			System.out.println("¡Bienvenido Bibliotecario!");
			bibliotecario_logged_menu();
			
		}else if (opcion == 2) {
			
			// Socio
			socio();
			
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
			inicio();
		}
		
	}
	
	// MAIN (Ejecucion)
	
	/**
	 * Main del Principal
	 * @param args: Array de Strings que debe aparecer obligatoriamente como argumento del método MAIN
	 * @throws NumberFormatException: Controla que el valor introducido por teclado sea un Número.
	 * @throws IOException: Significa que se ha producido un error en la entrada/salida.
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		// MAIN
		
		System.out.println("Bienvenido a la Biblioteca (G6)");
		System.out.println("Acceso al Sistema");
		
		inicio();
	}

}
