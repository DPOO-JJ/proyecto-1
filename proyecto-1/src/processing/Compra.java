package processing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Compra {
	
	Inventario inventario;
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	HashMap<Integer,Integer> totalPrecio = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> totalItems = new HashMap<Integer,Integer>();
	int puntos = 0;
	int id_compra;
	public int descuento;
	String sDescuentos = "";
	int multiplicador = 1;
	
	public Compra(Inventario inventario) {
		this.id_compra = (int) (new Date().getTime()/1000);
		this.inventario = inventario;
		this.descuento = 0;
	}
	
	public void anadirMultiplicador(Producto producto, PuntosExtra puntosExtra) {
		this.multiplicador = puntosExtra.getMultiplicador();
		sDescuentos += ("\nSe añadio un multiplicador x"+multiplicador+" por "+producto.getNombre());
	}
	
	public void anadirDescuento(Producto producto, Descuento desc) {
		float descuento = desc.getDescuento();
		int codigoBarras = producto.getCodigoBarras();
		int totalHastaAhora = totalPrecio.get(codigoBarras);
		totalPrecio.put(codigoBarras, (int)(totalHastaAhora*(1-descuento)));
		sDescuentos += ("\nDescuento a "+producto.getNombre()+" del "+(descuento*100)+"%");
	}
	
	public void anadirRegalo(Producto producto, Regalo regalo) {
		int unidadesExtras = 0;
		int codigoBarras = producto.getCodigoBarras();
		int itemsHastaAhora = totalItems.get(codigoBarras);
		
		//sólo si tiene la cantidad requerida de unidades se añaden las unidades extras
		if(itemsHastaAhora>=regalo.getCantidadRequerida()) {
			unidadesExtras = regalo.getUnidadesRegalo();
			totalItems.put(codigoBarras, itemsHastaAhora + unidadesExtras);
			sDescuentos += ("\nRecibes "+unidadesExtras+(unidadesExtras==1?"unidad extra de ":" unidades extras de ")+producto.getNombre());
		}
	}
	
	public boolean venderProducto(Producto producto, int peso) {
		
		int venta = inventario.venderUnidad(producto, peso);
		
		if (venta != 0)
		{
			productos.add(producto);
			int codigoBarras = producto.getCodigoBarras();
			
			if (totalPrecio.containsKey(codigoBarras)) {
				int totalHastaAhora = totalPrecio.get(codigoBarras);
				totalPrecio.put(codigoBarras, totalHastaAhora+venta);
			}
			else {
				totalPrecio.put(codigoBarras, venta);
			}
			
			if (totalItems.containsKey(codigoBarras)) {
				int itemsHastaAhora = totalItems.get(codigoBarras);
				totalItems.put(codigoBarras, itemsHastaAhora+peso);
			}
			else {
				totalItems.put(codigoBarras, peso);
			}
			
			this.puntos += venta/1000;
			return true;
		}

		return false;
	}

	public int getTotal() {
		int totalCompra = 0;
		for (int value : totalPrecio.values()) {
			totalCompra+=value;
		}
		return totalCompra - descuento;
	}

	public int getPuntos() {
		return puntos*multiplicador;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	private String generarTextoFactura(ArrayList<Integer> puntos) // TODO Agregar restricción con puntos
	{
		
		String factura = "--------- Factura de Compras ---------\n\n";
		factura += " Unid |      Producto      | Precio";
		for (int idProducto : totalItems.keySet()) {
			for(Producto producto: productos) {
				if(producto.getCodigoBarras()==idProducto) {
					factura += ("\n   "+totalItems.get(idProducto)+"  |"+producto.getNombre()+"|  "+totalPrecio.get(idProducto));
					break;
				}
			}
		}
		
		factura += "\n\n--------------------------------------";
		factura += ("\nTotal: " + getTotal()+"$");
		factura += ("\nDescuento en puntos: " + getDescuento()+"$");
		factura += "\n--------------------------------------";
		factura+=sDescuentos;
		factura += "\n--------------------------------------";
		
		if (puntos.size()>1) {
			factura += "\nPuntos del cliente antes de la compra: " + puntos.get(0);
			factura += "\nPuntos utilizados en esta compra: " + puntos.get(1);
			factura += "\nPuntos acumulados en esta compra: "+ puntos.get(2);
			factura += "\nPuntos totales disponibles: "+puntos.get(3);
		}
		
		return factura;
	}
	
	public void guardarFactura(ArrayList<Integer> puntosList)
	{
		FileManager.guardarArchivo("data/facturas/"+Integer.toString(this.id_compra)+".txt",this.generarTextoFactura(puntosList));
	}

	public int getId_compra() {
		return id_compra;
	}

	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

}
