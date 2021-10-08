package inventario;

import java.util.Scanner;

public class AplicacionInventario {
	
	static Inventario inventario = null;
	
	public static void ejecutarAplicacion()
	{
		int option = 0;
		
		inventario = new Inventario();
		
		boolean condition = true;
		while (condition) {
			System.out.println("------------------------------------------------");
			System.out.println("1. Cargar inventario\n"
					+ "2. Añadir categorías\n"
					+ "3. Revisar desempeño producto\n"
					+ "4. Chequear existencia de productos\n"
					+ "5. Salir de la aplicación");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
		    System.out.println("\nSeleccione una opción");
	
		    option = scanner.nextInt();
		    
		    ejecutarOpcion(option);
		    
		    if (option == 5)
		    {
		    	condition = false;
		    }
		}
	}
	
	public static void ejecutarOpcion(int opcionSeleccionada)
	{	
		if (opcionSeleccionada == 1)
		{
			
		}
		else if (opcionSeleccionada == 2)
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nIngrese el nombre de la categoria:");

		    String nombreCategoria = scanner.nextLine();
		    
		    System.out.println("Ingrese la super categoria de su categoria (si no tiene use null):");

		    String superCategoria = scanner.nextLine();
			
			int result = inventario.anadirCategoria(nombreCategoria, superCategoria);
			if (result==0) {
				System.out.println("Categoría creada exitosamente.");
			}
			else if (result==-1) {
				System.out.println("La supercategoría no ha sido registrada en el sistema, elija una de las siguientes:");
				for(Categoria categoria: inventario.getCategorias()) {
					System.out.println("-"+categoria.getNombre());
				}
			}
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
