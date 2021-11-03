package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelInventario extends JPanel implements PanelOpciones{
	
	private VentanaInventario padre;
	
	public static final String CARGAR_LOTE = "Cargar lote";
	public static final String ANADIR_CATEGORIA = "Añadir categoria";
	public static final String DETALLES_PRODUCTO = "Detalles producto";
	public static final String CHEQUEAR_LOTES = "Chequear lotes";
	public static final String ELIMINAR_LOTE = "Eliminar lote";

	public PanelInventario(VentanaInventario padre) {
		this.padre = padre;
		
		GridLayout layout = new GridLayout(2,3);
		layout.setHgap(20);
		layout.setVgap(20);
		
		setLayout(layout);
		
		MenuButton lote = new MenuButton(this, CARGAR_LOTE, "load.jpeg");
		add(lote);
		
		MenuButton categoria = new MenuButton(this, ANADIR_CATEGORIA, "+.jpeg");
		add(categoria);
		
		MenuButton producto = new MenuButton(this, DETALLES_PRODUCTO, "tag.jpeg");
		add(producto);
		
		MenuButton chequear = new MenuButton(this, CHEQUEAR_LOTES, "mglass.jpeg");
		add(chequear);
		
		MenuButton eliminar = new MenuButton(this, ELIMINAR_LOTE, "x.jpeg");
		add(eliminar);
		
		setPreferredSize(new Dimension(750, 375));
	}
	
	public void ejecutarOpcion(String opcion) {
		padre.ejecutar(opcion);
		return;
	}

}
