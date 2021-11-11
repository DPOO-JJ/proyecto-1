package interfaz;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import processing.POS;


@SuppressWarnings("serial")
public class VentanaSistemaPOS extends JFrame{
	
	private PanelFechaYHora reloj;
	private PanelPOS opciones;
	static POS pos = null;


	public VentanaSistemaPOS() {
		
		setLayout(new BorderLayout());
		
		pos = new POS();
		

		reloj = new PanelFechaYHora();
		add(reloj, BorderLayout.NORTH);
		
		// TODO Agregar panel POS
		opciones = new PanelPOS(this);
		opciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(opciones);
		
		pack();
		setTitle("POS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(false);
		setVisible(true);
	}
	
	public void ejecutar(String opcion) {
		System.out.println(opcion);
		
		if (opcion == "Registrar un cliente")
			new VentanaRegistroCliente(this);
		
		
	}
	
	public void anadirCliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboral) 
	{
		pos.anadirCliente(cedula,  nombres,  apellidos,  edad,  idenGenero,  situacionLaboral);
	}
	
	public static void main(String[] args) {
		new VentanaSistemaPOS();
	}
	
}
