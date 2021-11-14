package interfaz;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import processing.Categoria;
import processing.Inventario;

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
			System.out.println("Función detalles producto");	
		}
		else if (opcion.equals(PanelInventario.CHEQUEAR_LOTES)) {
			System.out.println("Función chequear lotes");
		}
		else if (opcion.equals(PanelInventario.ELIMINAR_LOTE)) {
			System.out.println("Función eliminar lote");
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
