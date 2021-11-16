package processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
	
	//crea un archivo que contendrá la info en 'texto'.
	public static void guardarArchivo(String nombreArchivo, String texto)
	{
		File archivo = new File(nombreArchivo);
		try { 
		      if (archivo.createNewFile()) {
		        FileWriter writer = new FileWriter(archivo);
		        writer.write(texto);
		        writer.close();
		      }
		      else
		      {
		        System.out.println("El archivo ya existe.");
		        return;
		      }
		}
		catch (IOException e)
		{
		      System.out.println("Ocurrió un error.");
		      e.printStackTrace();
		}
		return;
	}
	
	//añade una nueva linea a un archivo.
	public static void addLineToCSV(String file, String line){
		 
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
	
	//reemplaza una linea de un archivo por una nueva linea, dejando lo demás intacto.
	public static int cambiarLineaArchivo(String path, String oldFileLine, String newFileLine) {
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
		    return 0;
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
	}
	
	public static ArrayList<ArrayList<String>> guardarInfoMes(String mes, String cedula){
		
		ArrayList<ArrayList<String>> retorno = new ArrayList<ArrayList<String>>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("data/compras.csv")))
		{
			String line;
			line = br.readLine();
			line = br.readLine();
		    while (line != null) {
		    	
		        String[] info = line.split(",");
		        String a = info[2];
		        String[] fecha = a.split("\\.");
		        Boolean uno = fecha[1].equals(mes);
		        Boolean dos = info[0].equals(cedula);
		        if (fecha[1].equals(mes) && info[0].equals(cedula))
		        {
		        	ArrayList<String> tupla = new ArrayList<String>();
		        	tupla.add(mes);
		        	tupla.add(info[1]);
		        	retorno.add(tupla);
		        }
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	public static HashMap<Integer,ArrayList<String>> cargarImagenes() {
		HashMap<Integer,ArrayList<String>> hm = new HashMap<Integer,ArrayList<String>>();
		try (BufferedReader br = new BufferedReader(new FileReader("data/imagenes.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        
		        String[] values = line.split(",");
		        
		        ArrayList<String> al = new ArrayList<String>();
		        al.add(values[0]);
		        al.add(values[1]);
		        al.add(values[2]);
		        
		        hm.put(Integer.parseInt(values[0]),al);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;
		
	}
}
