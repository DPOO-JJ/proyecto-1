package inventario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Inventario {
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Lote> lotes = new ArrayList<Lote>();
	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	void cargarProductos() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/productos.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");

		        Producto nuevoProducto = new Producto(
		        		Integer.parseInt(values[0]),
		        		values[1],
		        		Integer.parseInt(values[2]),
		        		values[3],
		        		values[5],
		        		Boolean.parseBoolean(values[6])
		        );

        		
        		for(Categoria categoria: categorias) {
		        	if (categoria.getNombre().equals(values[4])){
		        		nuevoProducto.setCategoria(categoria);
		        		break;
		        	}
		        }
		        
		        productos.add(nuevoProducto);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void cargarLotes() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/lotes.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");

		        Lote nuevoLote = new Lote(
		        		values[0],
		        		values[1],
		        		Integer.parseInt(values[2]),
		        		Boolean.parseBoolean(values[3]),
		        		Integer.parseInt(values[4]),
		        		Integer.parseInt(values[5]));
		        
		        for(Producto producto: productos) {
		        	if (producto.getCodigoBarras()==Integer.parseInt(values[6])){
		        		nuevoLote.setProductoAsociado(producto);
		        		break;
		        	}
		        }
		        
		        lotes.add(nuevoLote);
		        
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void cargarCategorias() { //TODO NO FUNCIONA BIEN PARA SUB SUB SUB
		try (BufferedReader br = new BufferedReader(new FileReader("data/categorias.csv"))) {
		    String line;
		    br.readLine();
		    
		    ArrayList<String[]> lines = new ArrayList<String[]>();
		    
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        lines.add(values);
		    }
		    
		    for(String[] lineRead:lines) {
		    	if (lineRead[1].equals("null")) {
		    		Categoria nuevaCategoria = new Categoria(lineRead[0]);
		    		categorias.add(nuevaCategoria);
		    	}
		    }
		    
		    for(String[] lineRead:lines) {
		    	if (!lineRead[1].equals("null")) {
		    		for(Categoria categoria: categorias) {
			        	if (categoria.getNombre().equals(lineRead[1])){
			        		Categoria nuevaCategoria = new Categoria(lineRead[0]);
			        		nuevaCategoria.setSuperCategoria(categoria);
				    		categorias.add(nuevaCategoria);
			        		break;
			        	}
			        }
		    	}
		    }
	        
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void cargarDatos() {
		this.cargarCategorias();
		this.cargarProductos();
		this.cargarLotes();
	}
	
}
