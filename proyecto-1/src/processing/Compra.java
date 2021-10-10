package processing;

import java.util.ArrayList;

public class Compra {
	
	ArrayList<Producto> productos = new ArrayList<Producto>();
	ArrayList<Producto> disponibles = new Inventario().getProductos();
	int total = 0;
	int puntos = 0;
	
	Boolean addProduct (int codigo)
	{
		for (Producto x: disponibles) {
			if (x.getCodigoBarras() == codigo) {
				productos.add(x);
				if (x.isEmpacado() == false)
				{
					
				}
				return true;
			}
		}
		return false;
	}
	
}
