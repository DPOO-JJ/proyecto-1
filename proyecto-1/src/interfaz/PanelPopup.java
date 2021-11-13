package interfaz;

public interface PanelPopup {
	//titulo es el nombre de la ventana creada, aceptada es true si se presionó aceptar sino se presionó la x
	public void aceptar(String titulo, boolean aceptada);
	public void error(String titulo);
}
