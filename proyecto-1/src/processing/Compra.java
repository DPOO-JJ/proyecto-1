package processing;

import java.util.ArrayList;
import java.util.Date;

public class Compra {
	
	Inventario inventario = new Inventario();
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Producto> disponibles = inventario.getProductos();
	ArrayList<Lote> lotes = inventario.getLotes();
	int total = 0;
	int puntos = 0;
	int id_compra;
	
	
	public Compra() {
		this.id_compra = (int) (new Date().getTime()/1000);
	}
	

	public boolean makePurchase(int codigo, int peso) {
		
		for (Producto i : disponibles) {
			if (i.getCodigoBarras() == codigo)
			{
				
				if (i.isEmpacado())
				{
					int venta = inventario.venderUnidad(i, 1);
					
					if (venta != 0)
					{
						productos.add(i);
						total += venta;
						this.puntos += venta/1000;
						return true;
					}
				}
				else
				{
					int venta = inventario.venderUnidad(i, peso);
					
					if (venta != 0)
					{
						
						productos.add(i);
						total += venta;
						this.puntos += venta/1000;
						return true;
					}
				}	
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
	
	private String generarTextoFactura()
	{
		Integer precio = 0;
		
		
		String factura = "------ Factura de Compras ------\n";
		for (Producto producto: productos)
		{
			for (Lote lote: lotes) 
			{
				if (lote.getProductoAsociado().equals(producto))
				{
					precio = lote.getPrecioVenta();
				}
			}
			factura += ("\n"+producto.getNombre()+" "+ precio.toString());
		}
		factura += ("\nTotal: " + getTotal());

		return factura;
	}
	
	public void guardarFactura()
	{
		FileManager.guardarArchivo("data/facturas/"+Integer.toString(this.id_compra)+".txt",this.generarTextoFactura());
	}


	public int getId_compra() {
		return id_compra;
	}


	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}

}
