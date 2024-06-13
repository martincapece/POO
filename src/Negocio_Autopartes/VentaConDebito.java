package Negocio_Autopartes;

public class VentaConDebito extends Venta {

	public VentaConDebito(int id, String fecha) {
		super(id, fecha);
	}

	public double CalcularTotal() {
		return getMontoTotal();
	}

}
