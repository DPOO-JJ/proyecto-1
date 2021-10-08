package pos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class POS {
	
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	
	void cargarClientes() {
		
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
	
	void imprimirClientes()
	{
		for (Cliente cliente: clientes) {
			System.out.println(cliente.getNombres());
		}
	}

}
