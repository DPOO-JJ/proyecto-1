package processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class POS {
	
	public ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	public Compra compra = new Compra();
	public Inventario inventario = new Inventario();
	
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
		        		values[5]);
		        
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
	
	void imprimirClientes()
	{
		for (Cliente cliente: clientes) {
			System.out.println(cliente.getNombres());
		}
	}
	
	void hacerCompra(ArrayList<Integer> codigos)
	{
		for (Integer codigo: codigos) {
			if (compra.addProduct(codigo)) {};
		}
		
	}
	
	public int anadirCliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboral) {

		int result = -1;
		
		Cliente cliente = new Cliente(cedula, nombres, apellidos, edad, idenGenero, situacionLaboral);
		
		clientes.add(cliente);
		
		
		String toAdd = cedula+","+nombres+","+apellidos+","+edad+","+idenGenero+","+situacionLaboral+","+"0";
		addLineToCSV("data/clientes.csv",toAdd);
		
		
		return result;
	}

	private void addLineToCSV(String file, String line){
 
		FileWriter fstream;
		try {
			fstream = new FileWriter(file, true);
			BufferedWriter out = new BufferedWriter(fstream);
			 
			out.newLine();
			out.write(line);
	 
			//close buffer writer
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
