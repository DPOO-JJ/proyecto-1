package interfaz;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import processing.Cliente;
import processing.FileManager;
import processing.POS;
import processing.Producto;


public class AplicacionPOS{
	
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
		    
		    ejecutarOpcion(option);
		    
		    if (option == 3)
			{
		    	System.out.println("Aplicación cerrada.");
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
			
			Cliente currentCliente = null;
			
			System.out.println("\nIngrese la cédula del cliente:");
			
			int cedula = scanner.nextInt();
			Boolean found = false;
			
			for (Cliente cliente : pos.getClientes())
			{
				if (cliente.getCedula() == cedula)
				{
					currentCliente = cliente;
					found = true;
				}
			}
			
			if (found == false)
			{
				System.out.println("No se encontró el cliente.");
			}
			else
			{
				System.out.println("\nCliente: " + currentCliente.getApellidos() + " " + currentCliente.getNombres());
			}
			
			pos.newCompra();
				
			Boolean ejecutando = true;
			System.out.println("\n-- Inicio de proceso de compra--"+"\nDigite los códigos de los productos");
			while (ejecutando)
			{
				System.out.println("Código: ");
				int prod = scanner.nextInt();
				
				Producto producto = pos.getProductByCode(prod);
				
				if (producto != null)
				{
					int peso = 0;
					
					if (producto.isEmpacado() == false)
					{
						System.out.println("Digite el peso del producto en gramos: ");
						peso = scanner.nextInt();
					}
					
					Boolean seHizo = pos.hacerCompra(producto, peso, currentCliente);
					
					if (seHizo)
					{
						System.out.println(producto.getNombre()+" agregado");
					}
					else
					{
						System.out.println("Imposible agregar "+producto.getNombre());
					}
					
					System.out.println("Para continuar digite cualquier tecla, de lo contrario digite 0");
					@SuppressWarnings("resource")
					Scanner scanner2 = new Scanner(System.in);
					String opt = scanner2.nextLine();
					
					if (opt.equals("0"))
					{
						ejecutando = false;
						pos.getCompra().guardarFactura();
					}
					
				}
				else
				{
					System.out.println("Producto no identificado.");
				}
			}
			
			if (currentCliente != null)
			{
				
				pos.updatePoints(currentCliente);
				
				System.out.println("Puntos acumulados en esta compra: " + pos.getCompra().getPuntos());
				System.out.println("Puntos totales para " + currentCliente.getApellidos().toString() + ": " + currentCliente.puntos);
				
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
				String line = Integer.toString(currentCliente.getCedula()) + "," + Integer.toString(pos.getCompra().getTotal()) + "," + timeStamp;
				FileManager.addLineToCSV("data/compras.csv", line);
			}
			
			
		}
		else if (opcionSeleccionada == 2)
		{
			
			System.out.println("\n----NUEVO REGISTRO DE CLIENTE----");
			
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Ingrese la cédula del cliente");
			int cedula = Integer.parseInt(scanner.nextLine());
			
			for (Cliente cliente : pos.getClientes())
			{
				if (cliente.getCedula() == cedula)
				{
					System.out.println("La cédula ya está registrada.");
					return;
				}
			}
			
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
