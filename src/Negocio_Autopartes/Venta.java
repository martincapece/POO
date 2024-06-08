package Negocio_Autopartes;

import java.util.ArrayList;

public abstract class Venta {
	private int id;
	private String fecha;
	private double montoTotal;
	private String metodopago;
	private int cuotas;
	ArrayList<Autoparte> autopartepedido;
	ArrayList<Integer> autoparteCantidad;
	
	public Venta(int id, String fecha, double montoTotal, String metodopago, int cuotas) {
		setId(id);
		setFecha(fecha);
		setMontoTotal(montoTotal);
		setMetodopago(metodopago);
		setCuotas(cuotas);
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
	
	public boolean disminuirStock() {
	    for (int i = 0; i < autopartepedido.size(); i++) {
	        if (verificarStock(autopartepedido.get(i), autoparteCantidad.get(i))) {
	            autopartepedido.get(i).restarStock(autoparteCantidad.get(i));
	        } else {
	            System.out.println("No había suficiente stock para: " + autopartepedido.get(i).getModelo());
	            return false;
	        }
	    }
	    return true;
	}


	public boolean verificarStock(Autoparte autoparte, int cant) {
	    return autoparte.getStock() >= cant;
	}


	public String getMetodopago() {
		return metodopago;
	}


	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}
	
	public double CobroTotal() {
	    if (this.metodopago.equals("td")) {
	        return this.montoTotal;
	    } else if (this.metodopago.equals("ef")) {
	        return this.montoTotal - (this.montoTotal * 0.10);
	    } else if (this.metodopago.equals("tc")) {
	        return CobroCuotas();
	    } else {
	        throw new IllegalArgumentException("Método de pago desconocido: " + this.metodopago);
	    }
	}



	private double CobroCuotas() {
	    if (this.cuotas == 2) {
	        return this.montoTotal + (this.montoTotal * 0.06); // 0.6 sería 60%, probablemente quisiste decir 0.06
	    } else if (this.cuotas == 3) {
	        return this.montoTotal + (this.montoTotal * 0.12);
	    } else if (this.cuotas == 6) {
	        return this.montoTotal + (this.montoTotal * 0.20);
	    } else {
	        throw new IllegalArgumentException("Número de cuotas no válido: " + this.cuotas);
	    }
	}



	public int getCuotas() {
		return cuotas;
	}


	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	
	
}