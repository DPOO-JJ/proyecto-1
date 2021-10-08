package pos;

import java.util.Scanner;



public class AplicacionPOS {

	public static void ejecutarAplicacion()
	{
		int option = 0;
		
		POS pos = new POS();
		
		boolean condition = true;
		while (condition) {
			System.out.println("------------------------------------------------");
			System.out.println("1. Hacer una compra");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
		    System.out.println("\nSeleccione una opción");
	
		    option = scanner.nextInt();
		    
		    pos.cargarClientes();
		    
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
