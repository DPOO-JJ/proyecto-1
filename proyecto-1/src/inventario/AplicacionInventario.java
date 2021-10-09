package inventario;

import java.util.ArrayList;
import java.util.HashMap;
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
			System.out.println("1. Cargar nuevo lote de productos\n"
					+ "2. Añadir categorías al sistema\n"
					+ "3. Revisar desempeño de un producto\n"
					+ "4. Chequear existencia de producto\n"
					+ "5. Eliminar lote de producto del inventario\n"
					+ "6. Salir de la aplicación");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
		    System.out.println("\nSeleccione una opción");
	
		    option = scanner.nextInt();
		    
		    ejecutarOpcion(option);
		    
		    if (option == 6)
		    {
		    	condition = false;
		    }
		}
	}
	
	public static void ejecutarOpcion(int opcionSeleccionada)
	{	
		if (opcionSeleccionada == 1)
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nIngrese el nombre del archivo:");

		    String nombreArchivo = scanner.nextLine();
		    
		    nombreArchivo = "data/nuevolote.txt";
		    
			int result = inventario.cargarNuevosLotes(nombreArchivo);
			if (result==0) {
				System.out.println("Lote de productos cargados exitosamente.");
			}
			else if (result==1) {
				System.out.println("El archivo no existe.");
			}
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
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nSeleccione el producto a revisar desempeño:");
		    
		    int i = 0;
		    
		    for(Producto producto: inventario.getProductos()) {
				System.out.println((i+1)+". "+producto.getNombre());
				i++;
			}
		    
		    Producto productoSeleccionado = inventario.getProductos().get(scanner.nextInt()-1);

		    HashMap<String,Integer> desempeno = inventario.revisarDesempeno(productoSeleccionado);
		    System.out.println(productoSeleccionado.getNombre()+" ha generado:");
		    System.out.println("Ganancias: "+desempeno.get("ganancias"));
		    System.out.println("Perdidas: "+desempeno.get("perdidas"));
		}
		else if (opcionSeleccionada == 4)
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nSeleccione el producto a revisar:");
		    
		    int i = 0;
		    
		    for(Producto producto: inventario.getProductos()) {
				System.out.println((i+1)+". "+producto.getNombre());
				i++;
			}
		    
		    Producto productoSeleccionado = inventario.getProductos().get(scanner.nextInt()-1);

		    int unidadesRestantes = inventario.revisarExistencia(productoSeleccionado);
		    System.out.println("Quedan "+unidadesRestantes+" unidades de "+productoSeleccionado.getNombre()+".");
		}
		else if (opcionSeleccionada == 5)
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nSeleccione el producto del lote a eliminar:");
		    
		    int i = 0;
		    
		    for(Producto producto: inventario.getProductos()) {
				System.out.println((i+1)+". "+producto.getNombre());
				i++;
			}
		    
		    Producto productoSeleccionado = inventario.getProductos().get(scanner.nextInt()-1);
		    ArrayList<Lote> lotesFiltrados = inventario.obtenerLotesProducto(productoSeleccionado);
		    
		    System.out.println("\nLotes de "+productoSeleccionado.getNombre()+" en el inventario:");
		    
		    System.out.println("Fecha recibido \t| Fecha de Vencimiento \t| Precio de Compra \t|"
		    		+ " Precio de Venta \t| Unidades originales \t|"
		    		+ " Unidades restantes \t| ¿El lote fue eliminado? \t| Producto del lote");
		    
		    i = 0;
		    
		    for(Lote lote: lotesFiltrados) {
				System.out.println((i+1)+". "+lote.toString());
				i++;
			}
		    
		    System.out.println("\nSeleccione el lote a eliminar (escriba no si no desea eliminar algún lote)");
		    
		    scanner = new Scanner(System.in);
		    
		    String eleccion = scanner.nextLine();
		    if(eleccion.toLowerCase().equals("no")) {
		    	System.out.println("\nNingún lote fue eliminado.");
		    	return;
		    }

		    Lote loteSeleccionado = lotesFiltrados.get(Integer.parseInt(eleccion)-1);
		    inventario.eliminarLote(loteSeleccionado);
		    System.out.println("\nLote eliminado satisfactoriamente.");
		}
		else if (opcionSeleccionada == 10)
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);  

		    System.out.println("\nSeleccione el producto a vender:");
		    
		    int i = 0;
		    
		    for(Producto producto: inventario.getProductos()) {
				System.out.println((i+1)+". "+producto.getNombre());
				i++;
			}
		    
		    Producto productoSeleccionado = inventario.getProductos().get(scanner.nextInt()-1);
		    boolean resultado = inventario.venderUnidad(productoSeleccionado);
		    if (resultado) {
		    	System.out.println("La unidad se pudo vender.");
		    }
		    else {
		    	System.out.println("No se encontró unidad disponible para vender.");
		    }
		    
		}
	}
	
	public static void main(String[] args)
	{
		ejecutarAplicacion();
	}

}
