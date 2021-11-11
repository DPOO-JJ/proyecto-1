package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPOS extends JPanel implements PanelOpciones{
	
	private VentanaSistemaPOS padre;
	
	public static final String HACER_COMPRA = "Hacer compra";
	public static final String REGISTRO_CLIENTE = "Registrar un cliente";
	public static final String CONSULTA_CLIENTE = "Consultar un cliente";

	public PanelPOS(VentanaSistemaPOS padre) {
		this.padre = padre;
		GridLayout layout = new GridLayout(1,3);
		layout.setHgap(20);
		layout.setVgap(20);
		
		setLayout(layout);
		
		MenuButton compra = new MenuButton(this, HACER_COMPRA, "carrito.jpeg");
		add(compra);
		
		MenuButton cliente = new MenuButton(this, REGISTRO_CLIENTE, "cliente.jpg");
		add(cliente);
		
		MenuButton consulta = new MenuButton(this, CONSULTA_CLIENTE, "mglass.jpeg");
		add(consulta);
		
		setPreferredSize(new Dimension(750, 186));
	}
	
	@Override
	public void ejecutarOpcion(String opcion) {
		padre.ejecutar(opcion);
		
	}

	
}
