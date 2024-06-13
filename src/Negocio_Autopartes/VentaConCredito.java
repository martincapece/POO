package Negocio_Autopartes;

public class VentaConCredito extends Venta {
	private int cuotas;
	
	public VentaConCredito(int id, String fecha, int cuotas) {
		super(id, fecha);
		setCuotas(cuotas);
	}
		
	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}

	public double CalcularTotal() {
		 if (this.cuotas == 2) {
		        return (getMontoTotal() + (getMontoTotal() * 0.06));
			} else if (this.cuotas == 3) {
			    return (getMontoTotal() + (getMontoTotal() * 0.12));
			} else {
			    return (getMontoTotal() + (getMontoTotal() * 0.20));
			}
		}

}
