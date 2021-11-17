package interfaz;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import processing.Cliente;
import processing.Compra;
import processing.FileManager;
import processing.POS;
import processing.Producto;


@SuppressWarnings("serial")
public class AplicacionSistemaPOS extends JFrame implements IPopup{
	
	private PanelFechaYHora reloj;
	private PanelPOS opciones;
	static POS pos = null;


	public AplicacionSistemaPOS() {
		
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
	

	public void nuevaCompra(int cedula) {
		
		Cliente cliente = pos.getClient(cedula);
		pos.newCompra();
		
		new VentanaAnadirProducto(this, cliente);
	}
	
	public void lanzarVentanaAñadirProducto(Cliente cliente) {
		
		new VentanaAnadirProducto(this, cliente);
	}
	
	public int anadirProducto(int codigo, int peso, Cliente cliente) {
		
		int result = 0;
		
		Producto producto = pos.getProductByCode(codigo);
		
		if (producto == null) {
			result = -1;
		}
		else {
			result = (pos.hacerCompra(producto, peso, cliente)?0:-2);
		}
		
		return result;
	}
	
	public int mostrarCompra(Cliente cliente) {
		ArrayList<Producto> productos = pos.getCompra().getProductos();
		Compra compra = pos.getCompra();
		
		if(compra.getTotal()!=0){
			new VentanaMostrarCompra(this, cliente, compra, productos);
			return 0;
		}
		else {
			//si no se ha agregado ningún producto al carrito
			new VentanaError(this,"Compra","El carrito de compra está vacío.");
			return -1;
		}
	}
	
	public void finalizarCompra(Cliente cliente) {
		pos.getCompra().guardarFactura();
		
		if (cliente != null)
		{
			pos.updatePoints(cliente);
			
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
			String line = Integer.toString(cliente.getCedula()) + "," + Integer.toString(pos.getCompra().getTotal()) + "," + timeStamp;
			FileManager.addLineToCSV("data/compras.csv", line);
			
			String mensaje = "<html>¡Compra exitosa!<br>"
					+"Puntos acumulados en esta compra: "+ pos.getCompra().getPuntos()+"<br>"
					+"Puntos totales para "+cliente.getApellidos()+": "+cliente.puntos+"</html>";
					
			new VentanaExitosa(this,"Nueva compra",mensaje);
		}
		else {
			new VentanaExitosa(this,"Nueva compra","¡Compra exitosa!");
		}
		
	}
	
	public static void main(String[] args) {
		new AplicacionSistemaPOS();
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
