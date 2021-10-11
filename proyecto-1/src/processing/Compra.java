package processing;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Compra {
	
	Inventario inventario = new Inventario();
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Producto> disponibles = inventario.getProductos();
	ArrayList<Lote> lotes = inventario.getLotes();
	int total = 0;
	int puntos = 0;
	

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
			factura += (producto.getNombre()+" "+ precio.toString());
		}

		return factura;
	}
	
	public void guardarFactura(File archivo)
	{
		try { 
		      if (archivo.createNewFile()) {
		        FileWriter writer = new FileWriter(archivo);
		        writer.write(this.generarTextoFactura());
		        writer.close();
		      }
		      else
		      {
		        System.out.println("El archivo ya existe.");
		        return;
		      }
		}
		catch (IOException e)
		{
		      System.out.println("Ocurrió un error.");
		      e.printStackTrace();
		}
		return;
		
	}
}
