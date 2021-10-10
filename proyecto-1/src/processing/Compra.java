package processing;

import java.util.ArrayList;

public class Compra {
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Producto> disponibles = new Inventario().getProductos();
	
	Boolean addProduct (int codigo)
	{
		for (Producto x: disponibles) {
			if (x.getCodigoBarras() == codigo) {
				productos.add(x);	
				return true;
			}
		}
		return false;
	}
}
