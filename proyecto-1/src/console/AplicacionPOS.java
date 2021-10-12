package console;

import java.io.File;
import java.util.Scanner;

import processing.Cliente;
import processing.Compra;
import processing.POS;



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
					System.out.println("\nCliente: " + cliente.getApellidos() + " " + cliente.getNombres());
					currentCliente = cliente;
				}
				
			}
			
			pos.newCompra();
				
			Boolean ejecutando = true;
			System.out.println("-- Inicio de proceso de compra--"+"\n Digite los códigos de los productos");
			while (ejecutando)
			{
				System.out.println("Código");
				int prod = scanner.nextInt();
				System.out.println("Digite el peso del producto, si no aplica digite 0");
				int peso = scanner.nextInt();
				Boolean seHizo = pos.hacerCompra(prod, peso, currentCliente);
				
				if (seHizo)
				{
					System.out.println("Producto agregado");
				}
				else
				{
					System.out.println("Imposible agregar producto");
				}
				
				System.out.println("Continuar? Digite cualquier tecla, de lo contrario digite 1");
				@SuppressWarnings("resource")
				Scanner scanner2 = new Scanner(System.in);
				String opt = scanner2.nextLine();
				
				if (opt.equals("1"))
				{
					ejecutando = false;
					
					pos.getCompra().guardarFactura();
				}
			}
			
			if (currentCliente != null)
			{
				//antes de setear puntos hacer un liveCSV con los datos viejos del cliente
				currentCliente.setPuntos(currentCliente.getPuntos()+pos.getCompra().getPuntos());
				//volver a hcer un liveCSV
				//cambiarLineaArchivo("data/clientes.csv", liveCSVOld, liveCSVNew);
				
				System.out.println("Puntos para " + currentCliente.getApellidos() +" "+ pos.getCompra().getPuntos());
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
