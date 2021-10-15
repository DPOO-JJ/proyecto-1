package processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class POS {
	
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public Inventario inventario = new Inventario();
	public Compra compra = new Compra();
	
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
	
	
	public Boolean hacerCompra(int codigo, int peso, Cliente cliente)
	{
		if (compra.makePurchase(codigo, peso))
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
		for (Producto producto: inventario.productos) 
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
		
		Cliente cliente = new Cliente(cedula, nombres, apellidos, edad, idenGenero, situacionLaboral, 0);
		
		clientes.add(cliente);
		
		String toAdd = cedula+","+nombres+","+apellidos+","+edad+","+idenGenero+","+situacionLaboral+","+"0";
		FileManager.addLineToCSV("data/clientes.csv",toAdd);
		
		return result;
	}

	public Compra getCompra() {
		return compra;
	}


	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public void newCompra() {
		this.compra = new Compra();
	}
	
	public void updatePoints(Cliente cliente)
	{
		String oldLine = cliente.lineCSV();

		cliente.setPuntos(cliente.getPuntos()+getCompra().getPuntos());

		String newLine = cliente.lineCSV();
		
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

}
