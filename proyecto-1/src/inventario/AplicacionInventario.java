package inventario;

import java.util.Scanner;

public class AplicacionInventario {
	
	public static void ejecutarAplicacion()
	{
		int option = 0;
		
		Inventario inventario = new Inventario();
		inventario.cargarDatos();
		
		boolean condition = true;
		while (condition) {
			System.out.println("------------------------------------------------");
			System.out.println("1. Cargar inventario\n"
					+ "3. Añadir categorías\n"
					+ "3. Revisar desempeño producto\n"
					+ "4. Chequear existencia de productos\n"
					+ "5. Salir de la aplicación");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
		    System.out.println("\nSeleccione una opción");
	
		    option = scanner.nextInt();
		    
		    ejecutarOpcion(option);
		}
	}
	
	public static void ejecutarOpcion(int opcionSeleccionada)
	{	
		if (opcionSeleccionada == 1)
		{
			System.out.println("Opción 1");
		}
		else if (opcionSeleccionada == 2)
		{
			System.out.println("Opción 2");
		}
		else if (opcionSeleccionada == 3)
		{
			System.out.println("Opción 3");
		}
		else if (opcionSeleccionada == 4)
		{
			System.out.println("Opción 4");
		}
	}
	
	public static void main(String[] args)
	{
		ejecutarAplicacion();
	}

}
