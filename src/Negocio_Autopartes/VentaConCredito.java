package Negocio_Autopartes;

public class VentaConCredito extends Venta {
	private int cuotas;
	
	public VentaConCredito(int id, String fecha, double montoTotal, int cuotas) {
		super(id, fecha, montoTotal);
		// TODO Auto-generated constructor stub
		setCuotas(cuotas);
	}
		
	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	@Override
	public double CalcularTotal() {
		// TODO Auto-generated method stub
		 if (this.cuotas == 2) {
		        return getMontoTotal() + (getMontoTotal() * 0.06);
			} else if (this.cuotas == 3) {
			    return getMontoTotal() + (getMontoTotal() * 0.12);
			} else {
			    return getMontoTotal() + (getMontoTotal() * 0.20);
			}
		}

}
