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
	
	public void disminuirStock() {
		for(int i = 0; i < autopartepedido.size(); i++) {
			if(verificarStock(autopartepedido.get(i),autoparteCantidad.get(i))) {
				if(autoparteCantidad.get(i)<autopartepedido.get(i).getStock()) {
					autopartepedido.get(i).restarStock(autoparteCantidad.get(i));
				}
			}else {
				System.out.println("No habia suficiente stock");
				return;
			}
		}
	}
	
	public boolean verificarStock(Autoparte autoparte,int cant) {
		if(autoparte.getStock()>cant) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	
}