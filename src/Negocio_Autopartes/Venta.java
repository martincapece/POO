package Negocio_Autopartes;

import java.util.ArrayList;
import Excepciones.*;

public abstract class Venta {
	private int id;
	private String fecha;
	private double montoTotal;
	ArrayList<Autoparte> autopartePedido;
	ArrayList<Integer> autoparteCantidad;
	
	public Venta(int id, String fecha) {
		setId(id);
		setFecha(fecha);
		setMontoTotal(0);
		autopartePedido = new ArrayList<Autoparte>();
		autoparteCantidad = new ArrayList<>();
	}
	

	public void CargarAutopartePed(Autoparte autoparte) {
		autopartePedido.add(autoparte);
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
	
	public void CalcularMontoTotal() {
		if (autopartePedido.isEmpty()) {
			throw new ListaVaciaExcepcion("Error: El pedido no contiene autopartes.");			
		} else {
			for (int i = 0; i < autopartePedido.size(); i++) {
				Autoparte autoparte = autopartePedido.get(i);
				int cantidad = autoparteCantidad.get(i);
				
				this.montoTotal += (autoparte.getPrecio() * cantidad);
			}			
		}
	}
	
	public void DisminuirStock() {    
		if (autopartePedido.isEmpty()) {
			throw new ListaVaciaExcepcion("Error: El pedido no contiene autopartes.");			
		} else {
			for (int i = 0; i < autopartePedido.size(); i++) {
				Autoparte autoparte = autopartePedido.get(i);
				int cantidad = autoparteCantidad.get(i);
				
				if (VerificarStock(autoparte, cantidad)) {
					autoparte.RestarStock(cantidad);
				} else {
					throw new AccionImposibleExcepcion("No había suficiente stock para: " + autoparte.getModelo());
				}
			}			
		}
	}


	public boolean VerificarStock(Autoparte autoparte, int cant) {
	    return (autoparte.getStock() >= cant);
	}
	
	public abstract double CalcularTotal();
	
}