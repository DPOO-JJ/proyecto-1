package processing;

import java.util.ArrayList;
import java.util.Date;

public class Compra {
	
	Inventario inventario;
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	int total = 0;
	int puntos = 0;
	int id_compra;
	
	
	public Compra(Inventario inventario) {
		this.id_compra = (int) (new Date().getTime()/1000);
		this.inventario = inventario;
	}
	
	public boolean makePurchase(Producto producto, int peso) {
				
		if (producto.isEmpacado())
		{
			int venta = inventario.venderUnidad(producto, 1);
			
			if (venta != 0)
			{
				productos.add(producto);
				total += venta;
				this.puntos += venta/1000;
				return true;
			}
		}
		else
		{
			int venta = inventario.venderUnidad(producto, peso);
			
			if (venta != 0)
			{
				
				productos.add(producto);
				total += venta;
				this.puntos += venta/1000;
				return true;
			}
		}

		return false;
	}

	public int getTotal() {
		return total;
	}

	public int getPuntos() {
		return puntos;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	private String generarTextoFactura(int puntos) // TODO Agregar restricción con puntos
	{
		Integer precio = 0;
		
		
		String factura = "------ Factura de Compras ------\n";
		for (Producto producto: productos)
		{
			for (Lote lote: inventario.getLotes()) 
			{
				if (lote.getProductoAsociado().equals(producto))
				{
					precio = lote.getPrecioVenta();
				}
			}
			factura += ("\n"+producto.getNombre()+" "+ precio.toString());
		}
		factura += ("\nTotal: " + getTotal());
		factura += ("\nPuntos utilizados: " + puntos);
		

		return factura;
	}
	
	public void guardarFactura(int puntos)
	{
		FileManager.guardarArchivo("data/facturas/"+Integer.toString(this.id_compra)+".txt",this.generarTextoFactura(puntos));
	}


	public int getId_compra() {
		return id_compra;
	}


	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}

}
