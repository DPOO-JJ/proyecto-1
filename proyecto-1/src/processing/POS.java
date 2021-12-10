package processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class POS {
	
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public ArrayList<Promocion> promociones = new ArrayList<Promocion>();
	public Inventario inventario;
	public Compra compra;
	
	public POS(){
		this.inventario = new Inventario();
		this.cargarClientes();
		this.cargarPromociones();
	}
	
	private void cargarPromociones() {
		this.cargarCombos();
		this.cargarDescuentos();
		this.cargarPuntosExtras();
		this.cargarRegalos();
	}
	
	private void cargarRegalos() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/promociones/regalos.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        
		        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		        Date fechaInicial = null;
		        Date fechaFinal = null;
				try {
					fechaInicial = formatter.parse(values[0]);
					fechaFinal = formatter.parse(values[1]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				Regalo regalo = new Regalo(
		        		fechaInicial,
		        		fechaFinal,
		        		Integer.parseInt(values[2]),
		        		Integer.parseInt(values[3]),
		        		Integer.parseInt(values[4]));
		        
		        promociones.add(regalo);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarPuntosExtras() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/promociones/puntosextra.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        
		        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		        Date fechaInicial = null;
		        Date fechaFinal = null;
				try {
					fechaInicial = formatter.parse(values[0]);
					fechaFinal = formatter.parse(values[1]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				PuntosExtra puntosExtra = new PuntosExtra(
		        		fechaInicial,
		        		fechaFinal,
		        		Integer.parseInt(values[2]));
		        
		        promociones.add(puntosExtra);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void cargarDescuentos() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/promociones/descuentos.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        
		        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		        Date fechaInicial = null;
		        Date fechaFinal = null;
				try {
					fechaInicial = formatter.parse(values[0]);
					fechaFinal = formatter.parse(values[1]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
				Descuento descuento = new Descuento(
		        		fechaInicial,
		        		fechaFinal,
		        		Integer.parseInt(values[2]),
		        		Float.parseFloat(values[3]));
		        
		        promociones.add(descuento);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cargarCombos() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/promociones/combos.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        
		        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		        Date fechaInicial = null;
		        Date fechaFinal = null;
				try {
					fechaInicial = formatter.parse(values[0]);
					fechaFinal = formatter.parse(values[1]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		        Combo combo = new Combo(
		        		fechaInicial,
		        		fechaFinal,
		        		Integer.parseInt(values[2]),
		        		values[3],
		        		Float.parseFloat(values[4]),
		        		values[5]);
		        
		        promociones.add(combo);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cargarClientes() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/clientes.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        
		        Cliente cliente = new Cliente(
		        		Integer.parseInt(values[0]), 
		        		values[1], 
		        		values[2], 
		        		Integer.parseInt(values[3]), 
		        		values[4],
		        		values[5],
		        		Integer.parseInt(values[6]));
		        
		        clientes.add(cliente);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Boolean hacerCompra(Producto producto, int peso, Cliente cliente)
	{
		if (compra.makePurchase(producto, peso))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Producto getProductByCode(int codigo)
	{
		
		Producto retorno = null;
		for (Producto producto: inventario.getProductos()) 
		{
			if (producto.getCodigoBarras() == codigo)
			{
				retorno = producto;
			}
		}
		return retorno;
		
	}
	
	public int anadirCliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboral) {

		int result = -1;
		
		for (Cliente cliente : getClientes())
		{
			if (cliente.getCedula() == cedula)
			{
				System.out.println("La cédula ya está registrada.");
				result = -2;
			}
		}
		
		if (result == -1) 
			{
			Cliente cliente = new Cliente(cedula, nombres, apellidos, edad, idenGenero, situacionLaboral, 0);
			
			clientes.add(cliente);
			
			String toAdd = cedula+","+nombres+","+apellidos+","+edad+","+idenGenero+","+situacionLaboral+","+"0";
			FileManager.addLineToCSV("data/clientes.csv",toAdd);
			}
		
		return result;
	}

	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public void newCompra() {
		this.compra = new Compra(inventario);
	}
	
	public void updatePoints(Cliente cliente, int viejosPuntos, int nuevosPuntos)
	{
		String oldLine = cliente.lineCSV()+viejosPuntos;
		System.out.println(oldLine);

		cliente.setPuntos(nuevosPuntos);

		String newLine = cliente.lineCSV()+nuevosPuntos;
		System.out.println(newLine);
		
		FileManager.cambiarLineaArchivo("data/clientes.csv", oldLine, newLine);
	
	}


	public ArrayList<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Inventario getInventario() {
		return inventario;
	}


	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	
	public Cliente getClient(int cedulaCliente)
	{
		Cliente retorno = null;
		
		for (Cliente i: clientes) 
		{
			if (i.getCedula() == cedulaCliente)
			{
				retorno = i;
			}
		}
		return retorno;
	}
}
