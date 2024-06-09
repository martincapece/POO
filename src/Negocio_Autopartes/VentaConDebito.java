package Negocio_Autopartes;

public class VentaConDebito extends Venta {

	public VentaConDebito(int id, String fecha, double montoTotal) {
		super(id, fecha, montoTotal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double CalcularTotal() {
		// TODO Auto-generated method stub
		return getMontoTotal();
	}

}
