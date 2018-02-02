package act1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Principal {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("Hola, soy un programa POCO funcional");
		
		BufferedReader teclado = new BufferedReader (new InputStreamReader (System.in));
		int opcion = 0;
		boolean opcionbien;
		
		System.out.println("Bienvenido a la Biblioteca (G6)");
		System.out.println("Acceso al Sistema");
				
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
			
		}else if (opcion == 2) {
			
			// Socio
			
			do {
				opcionbien = true;
				System.out.print("\nIntroduzca su Numero de Carnet de Socio (0 para Cerrar el Programa): ");
				
				try{
					opcion = Integer.parseInt(teclado.readLine());
				}catch(NumberFormatException ex) {
					opcionbien = false;
				}
				
				if (opcionbien && (opcion == 0 || (opcion > 100000 && opcion < 1000000))) {
					opcionbien = true;
				}else {
					System.out.println("No se ha introducido un numero de carnet válido");
					opcionbien = false;
				}
				
			}while(!opcionbien);
			
			if (opcion == 0) {
				System.exit(0);
			}else if(opcion > 100000 && opcion < 1000000) {
				
				Biblioteca biblioteca = new Biblioteca();
				int num_carnet = opcion;
				Socio socio = biblioteca.socioEscogido(num_carnet);
				// Comprobación de la existencia de ese num_carnet
				if (biblioteca.existeSocio(num_carnet)) {
					
					// Socio Identificado
					System.out.println("¡Bienvenido "+socio.getNombre()+" ("+num_carnet+")!");
					
					//TODO: OPCIONES PARA EL SOCIO: 
					
					// Opciones de Socio:
					do {
						opcionbien = true;
						System.out.print("\n¿Qué desea hacer? ");
						System.out.print("\n1. Tomar Prestado Libro \n2. Devolver Libro \n3. Ver Libros En Prestamo \n0. Cerrar Sistema");
						
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
					}else {
						
						// Petición ISBN (13 nums y 4 guiones)(ISBN 978-3-16-148410-0)
						String isbn = teclado.readLine();
						if (opcion == 1) {
							System.out.println(biblioteca.atenderPeticion(true, num_carnet, isbn));
						}else if(opcion == 2) {
							System.out.println(biblioteca.atenderPeticion(false, num_carnet, isbn));
						}else if(opcion == 3) {
							
							// TODO: TERMINAR ESTO DE LOS LIBROS EN PRESTAMO, BUCLE PARA VOLVER AL MENU Y TODAS LAS OPCIONES DE LA BIBLIOTECARIA
							
							System.out.println("Libros Tomados Actualmente: ");
							for (int i = 0; i < socio.libroActualmenteTomados().size(); i++) {
								System.out.println(socio.libroActualmenteTomados().get(i));
							}
						}
																		
					}
					
					
					
					
				} else {
					System.out.println("ERROR: No se ha registrado en el Sistema. Contacte con un Bibliotecario.");
					System.exit(0);
				}
				
			}else {
				System.out.println("ERROR: Se ha producido un error inesperado");
			}
			
		}else {
			System.out.println("ERROR: Se ha producido un error inesperado");
		}
		
	}

}
