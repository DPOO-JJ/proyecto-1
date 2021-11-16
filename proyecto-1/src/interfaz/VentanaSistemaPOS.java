package interfaz;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import processing.Cliente;
import processing.POS;
import processing.Producto;


@SuppressWarnings("serial")
public class VentanaSistemaPOS extends JFrame implements PanelPopup{
	
	private PanelFechaYHora reloj;
	private PanelPOS opciones;
	static POS pos = null;


	public VentanaSistemaPOS() {
		
		setLayout(new BorderLayout());
		
		pos = new POS();
		

		reloj = new PanelFechaYHora();
		add(reloj, BorderLayout.NORTH);
		

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
		
		if (opcion.equals("Registrar un cliente"))
		{
			new VentanaRegistroCliente(this);
		}
		else if (opcion.equals("Consultar un cliente")) 
		{
			new VentanaConsultaCliente(this);	
		}
		else if (opcion.equals("Hacer compra")) 
		{
			new VentanaNuevaCompra(this);
			
		}
		
	}
	
	public void anadirCliente(int cedula, String nombres, String apellidos, int edad, String idenGenero, String situacionLaboral) 
	{
		int retorno = pos.anadirCliente(cedula,  nombres,  apellidos,  edad,  idenGenero,  situacionLaboral);
		if (retorno == -1) 
		{
			new VentanaExitosa(this,"Añadir cliente","Cliente agregado exitosamente.");
		}
		else if (retorno == -2)
		{
			new VentanaError(this,"Añadir cliente","La cédula ingresada ya se encuentra registrada.");
		}
	}
	
	public void getInfoCliente(int cedula)
	{
		Cliente cliente = pos.getClient(cedula);
		new VentanaCliente(this, cliente);
	}
	

	public void newCompra(int cedula) {
		// TODO Auto-generated method stub
		
		Cliente cliente = pos.getClient(cedula);
		new VentanaCompra(this, cliente);
		
	}
	
	public Boolean hacerCompra(int codigo, int peso, Cliente cliente) {
		Producto producto = pos.getProductByCode(codigo);
		return pos.hacerCompra(producto, peso, cliente);
	}
	
	public static void main(String[] args) {
		new VentanaSistemaPOS();
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
