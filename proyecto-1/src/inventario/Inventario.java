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
		        		values[4],
		        		values[5],
		        		Boolean.parseBoolean(values[6])
		        );
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
	
	void cargarCategorias() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/categorias.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");

		        Categoria nuevaCategoria = new Categoria(values[0]);
		        if (!values[1].equals("null")) {
		        	for(Categoria categoria: categorias) {
			        	if (categoria.getNombre().equals(values[1])){
			        		nuevaCategoria.setSuperCategoria(categoria);
			        		break;
			        	}
			        }
		        }
		        categorias.add(nuevaCategoria);
		        
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
		this.cargarProductos();
		this.cargarLotes();
		this.cargarCategorias();
		
		for(Categoria categoria: categorias) {
			System.out.println(categoria.getNombre());
			if (categoria.getSuperCategoria()!=null) {
				System.out.println("Y tiene supercategoria:");
				System.out.println(categoria.getSuperCategoria().getNombre());
			}
        }
	}
	
}
