package Negocio_Autopartes;

public class VentaConEfectivo extends Venta {

	public VentaConEfectivo(int id, String fecha, double montoTotal) {
		super(id, fecha, montoTotal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double CalcularTotal() {
		// TODO Auto-generated method stub
		return getMontoTotal() - (getMontoTotal() * 0.10);
	}

}
