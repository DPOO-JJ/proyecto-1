package console;

import java.util.Scanner;

import processing.Cliente;

import processing.POS;

import processing.Producto;



public class AplicacionPOS {
	
	
	static POS pos = null;

	public static void ejecutarAplicacion()
	{
		int option = 0;
		
		pos = new POS();
		
		
		boolean condition = true;
		while (condition) {
			System.out.println("------------------------------------------------");
			System.out.println("1. Hacer una compra"
					+ "\n2. Registrar un cliente"
					+ "\n3. Salir");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
	
		    System.out.println("\nSeleccione una opción");
	
		    option = scanner.nextInt();
		    
		    pos.cargarClientes();
		    
		    ejecutarOpcion(option);
		    
		    if (option == 3)
			{
				condition = false;
			}
		}
	}
	
	public static void ejecutarOpcion(int opcionSeleccionada)
	{	
		if (opcionSeleccionada == 1) //TODO TERMINAR Y AJUSTAR A VENTA POR UNIDAD Y POR GRAMAJE
		{
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			Cliente currentCliente = null;
			
			System.out.println("\nIngrese la cédula del cliente:");
			
			int cedula = scanner.nextInt();
			
			for (Cliente cliente : pos.clientes) {
				if (cliente.getCedula() == cedula)
				{
					System.out.println("\nCliente: " + cliente.getApellidos() + cliente.getNombres());
					currentCliente = cliente;
				}
				
			}
			
			if (currentCliente == null)
			{
			    System.out.println("\nSeleccione el producto a vender:");
			    
			    int i = 0;
			    
			    for(Producto producto: pos.inventario.getProductos()) {
					System.out.println((i+1)+". "+producto.getNombre());
					i++;
				}
			    
			    Producto productoSeleccionado = pos.inventario.getProductos().get(scanner.nextInt()-1);
			    
			    int unidadesAVender = 35;
			    
			    int resultado = pos.inventario.venderUnidad(productoSeleccionado,unidadesAVender);
			    if (resultado>0) {
			    	System.out.println("La unidad se pudo vender.");
			    	System.out.println("A este precio "+resultado);
			    }
			    else {
			    	System.out.println("No se encontró unidad disponible para vender.");
			    }
			}
		    
		}
		else if (opcionSeleccionada == 2)
		{
			
			System.out.println("\n----NUEVO REGISTRO DE CLIENTE----");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Ingrese la cédula del cliente");
			int cedula = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Ingrese los nombres del cliente");
			String nombres = scanner.nextLine();
			
			System.out.println("Ingrese los apellidos del cliente");
			String apellidos = scanner.nextLine();
			
			System.out.println("Ingrese la edad del cliente");
			int edad = Integer.parseInt(scanner.nextLine());
			
			System.out.println("Ingrese la identidad de género del cliente");
			String idenGenero = scanner.nextLine();
			
			System.out.println("Ingrese situación laboral del cliente");
			String situacionLaboral = scanner.nextLine();
			
			pos.anadirCliente(cedula, nombres, apellidos, edad, idenGenero, situacionLaboral);
			
		}
		
	}
	
	public static void main(String[] args)
	{
		
		ejecutarAplicacion();
	}
	
}
