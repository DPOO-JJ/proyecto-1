package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class ventanaSistemaPOS extends JFrame{
	private PanelFechaYHora reloj;


	public ventanaSistemaPOS() {
		
		setLayout(new BorderLayout());
		

		reloj = new PanelFechaYHora();
		add(reloj, BorderLayout.CENTER);
		
		setTitle("POS");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setResizable(true);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ventanaSistemaPOS();
	}
}
