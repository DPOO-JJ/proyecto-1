package inventario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventario {

	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Lote> lotes = new ArrayList<Lote>();
	ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	public Inventario() {
		super();
		this.cargarDatos();
	}
	
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
		        		Integer.parseInt(values[3]),
		        		Integer.parseInt(values[4]),
		        		Boolean.parseBoolean(values[6]),
		        		Integer.parseInt(values[7]));
		        
		        for(Producto producto: productos) {
		        	if (producto.getCodigoBarras()==Integer.parseInt(values[5])){
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
	
	int anadirCategoria(String nombreCategoria, String superCategoria) {

		int result = -1;
		
		Categoria nuevaCategoria = new Categoria(nombreCategoria);
		
		if(superCategoria.equals("null")) {
			result = 0;
		}
		else {
			for(Categoria categoria: categorias) {
	        	if (categoria.getNombre().equals(superCategoria)){
	        		nuevaCategoria.setSuperCategoria(categoria);
	        		result = 0;
	        	}
	        }
		}
		
		if (result==0) {
			categorias.add(nuevaCategoria);
			
			String toAdd = nombreCategoria+","+superCategoria;
			addLineToCSV("data/categorias.csv",toAdd);
		}
		
		return result;
	}
	
	int cargarNuevosLotes(String nombreArchivo) {
		int result = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
			
		    String line;
		    boolean loadingProductos = false;
		    boolean loadingLotes = false;
		    while ((line = br.readLine()) != null) {
		        if (line.equals("---Productos---")) {
		        	loadingProductos = true;
		        	br.readLine();
		        	continue;
		        }
		        else if (line.equals("---Lotes---")) {
		        	loadingLotes = true;
		        	loadingProductos = false;
		        	br.readLine();
		        	continue;
		        }
		        
		        if (loadingProductos) {
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
					addLineToCSV("data/productos.csv",line);
		        }
		        else if (loadingLotes) {
		        	String[] values = line.split(",");

			        Lote nuevoLote = new Lote(
			        		values[0],
			        		values[1],
			        		Integer.parseInt(values[2]),
			        		Integer.parseInt(values[3]),
			        		Integer.parseInt(values[4]),
			        		false,
			        		Integer.parseInt(values[4]));
			        
			        for(Producto producto: productos) {
			        	if (producto.getCodigoBarras()==Integer.parseInt(values[5])){
			        		nuevoLote.setProductoAsociado(producto);
			        		break;
			        	}
			        }
			        
			        String toSave = nuevoLote.toFileLine();
			        
			        lotes.add(nuevoLote);
					addLineToCSV("data/lotes.csv",toSave);
		        }
		    }
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			result = 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = 2;
		}
		return result;
	}
	
	void addLineToCSV(String file, String line){
 
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
	
	int revisarExistencia(Producto productoSeleccionado) {
		int unidadesRestantes = 0;
		
		for (Lote lote:lotes) {
			if (lote.getProductoAsociado().equals(productoSeleccionado)
					&& !lote.isLoteEliminado()) {
				unidadesRestantes+=lote.getUnidadesRestantes();
			}
		}
		
		return unidadesRestantes;
	}
	
	HashMap<String,Integer> revisarDesempeno(Producto productoSeleccionado) {
		HashMap<String,Integer> desempeno = new HashMap<String,Integer>();
		int ganancias = 0;
		int perdidas = 0;
		
		for (Lote lote:lotes) {
			if (lote.getProductoAsociado().equals(productoSeleccionado)) {
				int precioCompraPorUnidad=(int)lote.getPrecioCompra()/lote.getUnidades();

				ganancias+= (lote.getPrecioVenta()-precioCompraPorUnidad)*(lote.getUnidades()-lote.getUnidadesRestantes());
				
				if(lote.isLoteEliminado()) {
					perdidas+= precioCompraPorUnidad*lote.getUnidadesRestantes();
				}
			}
		}
		
		desempeno.put("ganancias",ganancias);
		desempeno.put("perdidas",perdidas);
		
		return desempeno;
	}
	
	ArrayList<Lote> obtenerLotesProducto(Producto productoSeleccionado) {
		
		ArrayList<Lote> lotesFiltrados = new ArrayList<Lote>();
		
		for (Lote lote:lotes) {
			if (lote.getProductoAsociado().equals(productoSeleccionado)) {
				lotesFiltrados.add(lote);
			}
		}
		
		return lotesFiltrados;
	}
	
	void eliminarLote(Lote loteAEliminar) {
		for (int i = 0;i<lotes.size();i++) {
			if (lotes.get(i).equals(loteAEliminar)) {
				Lote newLote = lotes.get(i);
				String oldFileLine = newLote.toFileLine();
				newLote.setLoteEliminado(true);
				String newFileLine = newLote.toFileLine();
				lotes.set(i,newLote);
				this.cambiarLineaArchivo("data/lotes.csv",oldFileLine,newFileLine);
				break;
			}
		}
	}
	
	boolean venderUnidad(Producto productoAVender) {
		boolean sold = false;
		for (int i = 0;i<lotes.size();i++) {
			Lote newLote = lotes.get(i);
			if (newLote.getProductoAsociado().equals(productoAVender)
					&& newLote.getUnidadesRestantes()>0
					&& !newLote.isLoteEliminado()) {
				String oldFileLine = newLote.toFileLine();
				newLote.setUnidadesRestantes(newLote.getUnidadesRestantes()-1);
				String newFileLine = newLote.toFileLine();
				lotes.set(i,newLote);
				this.cambiarLineaArchivo("data/lotes.csv",oldFileLine,newFileLine);
				sold = true;
				break;
			}
		}
		return sold;
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

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(ArrayList<Lote> lotes) {
		this.lotes = lotes;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}
}
