package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import processing.Categoria;
import processing.FileManager;
import processing.Inventario;
import processing.Lote;
import processing.Producto;

@SuppressWarnings("serial")
public class VentanaInventario extends JFrame implements PanelPopup {
	
	private PanelInventario pInventario;
	private Inventario inventario;

	public VentanaInventario(){
		
		inventario = new Inventario();
		
		setLayout(new BorderLayout());

		PanelFechaYHora reloj = new PanelFechaYHora();
		add(reloj, BorderLayout.NORTH);
		
		pInventario = new PanelInventario(this);
		pInventario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(pInventario, BorderLayout.CENTER);
		
		pack();
		
		setTitle("Inventario");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(true);
		setVisible(true);
	}
	
	public void ejecutar(String opcion) {
		if (opcion.equals(PanelInventario.CARGAR_LOTE)) {
			new VentanaCargarLote(this);
		}
		else if (opcion.equals(PanelInventario.ANADIR_CATEGORIA)) {
			new VentanaCategoria(this);
		}
		else if (opcion.equals(PanelInventario.DETALLES_PRODUCTO)) {
			new VentanaDetalles(this);
		}
		else if (opcion.equals(PanelInventario.ELIMINAR_LOTE)) {
			new VentanaEliminarLote(this);
		}
		return;
	}
	
	public void anadirCategoria(String nombreCategoria, String superCategoria) {
		
		System.out.println(nombreCategoria+" "+superCategoria);

		int result = inventario.anadirCategoria(nombreCategoria, superCategoria);
		if (result==0) {
			new VentanaExitosa(this,"Añadir categoria","Categoría creada exitosamente.");
		}
		else if (result==-1) {
			
			String error = "<html>La supercategoría no ha sido registrada en el sistema, elija una de las siguientes:<br><br>";
			for(Categoria categoria: inventario.getCategorias()) {
				error+="-"+categoria.getNombre()+"<br>";
			}
			error+="</html>";
			new VentanaError(this,"Añadir categoria",error);
		}
	}
	
	public void cargarLote(String nombreArchivo) {
		
		System.out.println(nombreArchivo);
	    
	    //nombreArchivo = "data/nuevolote.txt";
	    
		int result = inventario.cargarNuevosLotes(nombreArchivo);
		if (result==0) {
			new VentanaExitosa(this,"Cargar lote","Lote de productos cargados exitosamente.");
		}
		else if (result==1) {
			new VentanaError(this,"Cargar lote","El archivo no existe.");
		}
	}
	
	public void lanzarVentanaImagen(int idProducto) {
		
		HashMap<Integer,ArrayList<String>> hm = FileManager.cargarImagenes();
		
		for (ArrayList<String> dataProducto : hm.values()) {
			if (Integer.parseInt(dataProducto.get(0))==idProducto) {
				new VentanaCambiarImagen(this, dataProducto);
				break;
			}
		}
	}
	
	public void modificarImagen(ArrayList<String> dataProducto, String nombreImagen) {
		System.out.println(nombreImagen+" "+dataProducto.get(0));
		
		int result = inventario.cambiarImagen(dataProducto, nombreImagen);
		if (result==0) {
			new VentanaExitosa(this,"Cambiar imagen","Cambio exitoso.");
		}
		else if (result==1) {
			new VentanaError(this,"Modificar imagen","Archivo no encontrado.");
		}
	}

	public void revisarDetallesProducto(int idProducto) {
		
		Producto productoSeleccionado = null;
		
		for(Producto producto: inventario.getProductos()) {
			if(idProducto == producto.getCodigoBarras()){
				productoSeleccionado = producto;
				break;
			}
		}
		
		if (productoSeleccionado != null) {
			HashMap<String,Integer> desempeno = inventario.revisarDesempeno(productoSeleccionado);
		    int unidadesRestantes = inventario.revisarExistencia(productoSeleccionado);
		    
		    new VentanaProducto(productoSeleccionado, desempeno, unidadesRestantes);
		}
		else {
			new VentanaError(this,"Revisar desempeño","No hay data de este producto entre los lotes.");
		}
	}
	
	public void lanzarVentanaEliminar(int idProducto) {
		
		Producto productoSeleccionado = null;
		
		for(Producto producto: inventario.getProductos()) {
			if(idProducto == producto.getCodigoBarras()){
				productoSeleccionado = producto;
				break;
			}
		}
		
		if (productoSeleccionado != null) {
			
		    ArrayList<Lote> lotesFiltrados = inventario.obtenerLotesProducto(productoSeleccionado);
		    
		    new VentanaLotes(this, lotesFiltrados);
		}
		else {
			new VentanaError(this,"Eliminar lote","No hay data de este producto entre los lotes.");
		}
	}
	
public void eliminarLote(ArrayList<Lote> lotesFiltrados, int lote) {
		
		Lote loteSeleccionado = lotesFiltrados.get(lote);
	    inventario.eliminarLote(loteSeleccionado);
	    new VentanaExitosa(this,"Eliminar lote","Lote eliminado satisfactoriamente.");
	}
	
	public static void main(String[] args) {
		new VentanaInventario();
	}

	@Override
	public void aceptar(String titulo, boolean aceptada) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String titulo) {
		// TODO Auto-generated method stub
		
	}
}
