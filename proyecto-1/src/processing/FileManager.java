package processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
	public static void cambiarLineaArchivo(String path, String oldFileLine, String newFileLine) {
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