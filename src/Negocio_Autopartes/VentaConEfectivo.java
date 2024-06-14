package Negocio_Autopartes;

public class VentaConEfectivo extends Venta {

	public VentaConEfectivo(int id, String fecha) {
		super(id, fecha);
		}

	public double CalcularTotal() {
		return getMontoTotal() - (getMontoTotal() * 0.10);
	}

}
 