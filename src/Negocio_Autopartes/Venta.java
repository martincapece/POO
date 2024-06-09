package Negocio_Autopartes;

import java.util.ArrayList;

public abstract class Venta {
	private int id;
	private String fecha;
	private double montoTotal;
	ArrayList<Autoparte> autopartepedido;
	ArrayList<Integer> autoparteCantidad;
	
	public Venta(int id, String fecha, double montoTotal) {
		setId(id);
		setFecha(fecha);
		setMontoTotal(montoTotal);
		autopartepedido = new ArrayList<Autoparte>();
		autoparteCantidad = new ArrayList<>();
	}
	

	public void CargarAutopartePed(Autoparte autoparte) {
		autopartepedido.add(autoparte);
	}
	
	public void CargarCantidadPed(int cant) {
		autoparteCantidad.add(cant);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	public boolean DisminuirStock() {
	    for (int i = 0; i < autopartepedido.size(); i++) {
	    	Autoparte autoparte = autopartepedido.get(i);
	    	int cantidad = autoparteCantidad.get(i);
	    	
	        if (VerificarStock(autoparte, cantidad)) {
	            autoparte.RestarStock(cantidad);
	        } else {
	            System.out.println("No había suficiente stock para: " + autoparte.getModelo());
	            return false;
	        }
	    }
	    return true;
	}


	public boolean VerificarStock(Autoparte autoparte, int cant) {
	    return (autoparte.getStock() >= cant);
	}
	
	public abstract double CalcularTotal();
	
}