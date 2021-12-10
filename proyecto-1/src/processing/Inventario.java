package processing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	
	private void cargarProductos() {
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
	
	private void cargarHistoriaProductos() {
		try (BufferedReader br = new BufferedReader(new FileReader("data/histproductos.csv"))) {
		    String line;
		    br.readLine();
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");

		        String fecha = values[0];
		        int codigoProducto = Integer.parseInt(values[1]);
		        int cantidad = Integer.parseInt(values[2]);
		        		
		        for(Producto producto: productos) {
		        	if (producto.getCodigoBarras()==codigoProducto){
		        		producto.getHistorial().cargarDato(fecha, cantidad);
		        		break;
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
	
	private void cargarLotes() {
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
	
	private void cargarCategorias() { //TODO NO FUNCIONA BIEN PARA SUB SUB SUB
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
	
	public void cargarDatos() {
		this.cargarCategorias();
		this.cargarProductos();
		this.cargarLotes();
		this.cargarHistoriaProductos();
		
//		for(Producto producto: productos) {
//        	if (producto.getCodigoBarras()==1){
//        		
////        		producto.getHistorial().getDatos().forEach(
////        	            (key, value)
////        	                -> System.out.println(key + " = " + value));
//        		
//        		producto.getHistorial().actualizarCantidad(1);
//        		ArrayList cosas = producto.getHistorial().obtenerUltimaFecha();
//        		System.out.println("la última es: ");
//        		System.out.println(cosas.get(0));
//        		System.out.println(cosas.get(1));
//        		
//        		break;
//        	}
//        }
		
	}
	
	public int anadirCategoria(String nombreCategoria, String superCategoria) {

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
			FileManager.addLineToCSV("data/categorias.csv",toAdd);
		}
		
		return result;
	}
	
	public int cargarNuevosLotes(String nombreArchivo) {
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
			        FileManager.addLineToCSV("data/productos.csv",line);
		        }
		        else if (loadingLotes) {
		        	String[] values = line.split(",");
		        	
		        	int numUnidades= Integer.parseInt(values[4]);

			        Lote nuevoLote = new Lote(
			        		values[0],
			        		values[1],
			        		Integer.parseInt(values[2]),
			        		Integer.parseInt(values[3]),
			        		numUnidades,
			        		false,
			        		numUnidades);
			        
			        for(Producto producto: productos) {
			        	if (producto.getCodigoBarras()==Integer.parseInt(values[5])){
			        		nuevoLote.setProductoAsociado(producto);
			        		
			        		//las añado al historial
			        		producto.getHistorial().actualizarCantidad(numUnidades);
			        		break;
			        	}
			        }
			        
			        String toSave = nuevoLote.toFileLine();
			        
			        lotes.add(nuevoLote);
			        FileManager.addLineToCSV("data/lotes.csv",toSave);
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
	
	public int revisarExistencia(Producto productoSeleccionado) {
		int unidadesRestantes = 0;
		
		for (Lote lote:lotes) {
			if (lote.getProductoAsociado().equals(productoSeleccionado)
					&& !lote.isLoteEliminado()) {
				unidadesRestantes+=lote.getUnidadesRestantes();
			}
		}
		
		return unidadesRestantes;
	}
	
	public HashMap<String,Integer> revisarDesempeno(Producto productoSeleccionado) {
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
	
	public int cambiarImagen(ArrayList<String> dataProducto, String nombreImagen) {
		
		String id = dataProducto.get(0);
		String nombre = dataProducto.get(1);
		String imagePath = dataProducto.get(2);
		String oldFileLine = id+","+nombre+","+imagePath;
		String newFileLine = id+","+nombre+","+nombreImagen;
		
		return FileManager.cambiarLineaArchivo("data/imagenes.csv",oldFileLine,newFileLine);
		
	}
	
	public ArrayList<Lote> obtenerLotesProducto(Producto productoSeleccionado) {
		
		ArrayList<Lote> lotesFiltrados = new ArrayList<Lote>();
		
		for (Lote lote:lotes) {
			if (lote.getProductoAsociado().equals(productoSeleccionado)) {
				lotesFiltrados.add(lote);
			}
		}
		
		return lotesFiltrados;
	}
	
	public void eliminarLote(Lote loteAEliminar) {
		for (int i = 0;i<lotes.size();i++) {
			if (lotes.get(i).equals(loteAEliminar)) {
				Lote newLote = lotes.get(i);
				String oldFileLine = newLote.toFileLine();
				newLote.setLoteEliminado(true);
				String newFileLine = newLote.toFileLine();
				lotes.set(i,newLote);
				FileManager.cambiarLineaArchivo("data/lotes.csv",oldFileLine,newFileLine);
				//las elimino del historial
				int unidadesRestantes = loteAEliminar.getUnidadesRestantes();
				loteAEliminar.getProductoAsociado().getHistorial().actualizarCantidad(-unidadesRestantes);
				break;
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int venderUnidad(Producto productoAVender, int unidadesAVender) {
		int price = 0;
		int unidadesRestantes = unidadesAVender; //unidades que faltan por vender
		
		HashMap<Integer, ArrayList> tempChanges = new HashMap<Integer, ArrayList>();
		
		for (int i = 0;i<lotes.size();i++) {
			Lote newLote = lotes.get(i);
			if (newLote.getProductoAsociado().equals(productoAVender)
					&& newLote.getUnidadesRestantes()>0
					&& !newLote.isLoteEliminado()) {
				
				int unidadesVendidas = unidadesRestantes;
				
				String oldFileLine = newLote.toFileLine();
				newLote.setUnidadesRestantes(newLote.getUnidadesRestantes()-unidadesRestantes);
				
				if (newLote.getUnidadesRestantes()<0) {
					unidadesVendidas -= Math.abs(newLote.getUnidadesRestantes());
					unidadesRestantes = Math.abs(newLote.getUnidadesRestantes());
					newLote.setUnidadesRestantes(0);
				}
				else {
					unidadesRestantes = 0;
				}
				
				ArrayList tempArray = new ArrayList();
				tempArray.add(newLote);
				tempArray.add(oldFileLine);
				tempChanges.put(i,tempArray);
				
				price += newLote.getPrecioVenta()*unidadesVendidas;
				
				if(unidadesRestantes==0) {
					break;
				}
			}
		}
		
		if(unidadesRestantes>0) {
			price = 0;
		}
		else if(unidadesRestantes==0) {
			for (int key : tempChanges.keySet()) {
				
				ArrayList tempArray = tempChanges.get(key);
				Lote newLote = (Lote)tempArray.get(0);
				String oldFileLine = (String)tempArray.get(1);
				lotes.set(key,newLote);
				FileManager.cambiarLineaArchivo("data/lotes.csv",oldFileLine,newLote.toFileLine());
			}
			
			//las elimino del historial
			productoAVender.getHistorial().actualizarCantidad(-unidadesAVender);
		}
		
		return price;
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
