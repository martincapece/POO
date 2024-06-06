package Negocio_Autopartes;

public class VentaReserva extends Venta {
	
	public VentaReserva(int id, String fecha, double montoTotal) {
		super(id, fecha, montoTotal);
	}
	
	public void CancelarPedido() {
		for(int i = 0; i < autopartepedido.size(); i++) {
			if(autoparteCantidad.get(i) < autopartepedido.get(i).getStock()) {
				autopartepedido.get(i).sumarStock(autoparteCantidad.get(i));
			}
		}
	}
}
