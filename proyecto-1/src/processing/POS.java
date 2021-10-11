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
	
	
	public Boolean hacerCompra(int codigo, int peso, Cliente cliente)
	{
		if (compra.makePurchase(codigo, peso))
			{
				cambiarLineaArchivo("data/clientes.csv", cliente.lineCSV(), cliente.lineForUpdate() + compra.getPuntos());
				return true;
			}
		else
			{
				return false;
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
	
	void cambiarLineaArchivo(String path, String oldFileLine, String newFileLine) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
		    String line;
		    ArrayList<String> lines = new ArrayList<String>();
		    while ((line = br.readLine()) != null) {
		    	if (line.equals(oldFileLine)) {
		    		line = newFileLine;
		    	}
		        lines.add(line);
		    }
		    
		    FileWriter writer = new FileWriter(path); 
		    for (int i=0;i<lines.size();i++) {
		    	String toSave = lines.get(i);
		    	if (i!=lines.size()-1) {
		    		toSave+=System.lineSeparator();
		    	}
		    	writer.write(toSave);
		    }
		    writer.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
