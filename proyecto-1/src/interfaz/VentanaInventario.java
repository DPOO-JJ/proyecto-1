package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class VentanaInventario extends JFrame {
	
	private PanelInventario pInventario;

	public VentanaInventario(){
		
		setLayout(new BorderLayout());
		
//		pConfig = new PanelConfig();
//		add(pConfig, BorderLayout.NORTH);
		
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
			System.out.println("Función cargar lote");
		}
		else if (opcion.equals(PanelInventario.ANADIR_CATEGORIA)) {
			System.out.println("Función añadir categoria");
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
	
	public static void main(String[] args) {
		new VentanaInventario();
	}
}
