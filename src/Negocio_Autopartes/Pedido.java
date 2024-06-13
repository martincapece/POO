package Negocio_Autopartes;

import java.util.ArrayList;

import java.util.Scanner;

import Excepciones.AccionImposibleExcepcion;
import Excepciones.CuotasInvalidasExcepecion;
import Excepciones.ListaVaciaExcepcion;
import Excepciones.MetodoNoReconocidoExcepcion;

public class Pedido {
	public static Scanner sc = new Scanner(System.in);

	private int id;
	private String fecha;
	private double montoTotal;
	ArrayList<Autoparte> autopartePedido;
	ArrayList<Integer> autoparteCantidad;
	
	public Pedido(int id, String fecha) {
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
	
	public void CancelarPedido() {
		for(int i = 0; i < autopartePedido.size(); i++) {
				autopartePedido.get(i).sumarStock(autoparteCantidad.get(i));
		}
		System.out.println("Se devolvio el stock a su origen");
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
	
	public Venta convertirAVenta() {
		System.out.println("Opcion 'tc' para tarjeta de credito.");
		System.out.println("Opcion 'td' para tarjeta de debito.");
		System.out.println("Opcion 'ef' para efectivo.");
		String metodopago = "";
		boolean valido = false;
		
		while (!valido) {
			try {
				System.out.print("Ingrese metodo de pago: ");
				metodopago = sc.nextLine();
				metodopago = metodopago.toLowerCase();
				this.ValidarMetodoPago(metodopago);
				valido = true;
			} catch (MetodoNoReconocidoExcepcion e) {
				System.err.println(e.getMessage());
			}
		}
		
		Venta venta = null;
		switch (metodopago) {
		case "tc":
			int cuotas = 0;
			while (!valido) {
				try {
					System.out.print("Ingrese cuotas a pagar: ");
					cuotas = sc.nextInt();
					this.validarCuotas(cuotas);
					valido = true;
				} catch (CuotasInvalidasExcepecion e) {
					System.err.println(e.getMessage());
				}
			}
			venta = new VentaConCredito(this.id, this.fecha, cuotas);							
			break;
		case "td":
			venta = new VentaConDebito(this.id, this.fecha);
			break;
		case "ef":
			venta = new VentaConDebito(this.id, this.fecha);
			break;
		}
		
	    for (int i = 0; i < autopartePedido.size(); i++) {
	        venta.CargarAutopartePed(autopartePedido.get(i));
	        venta.CargarCantidadPed(autoparteCantidad.get(i));
	    }
	    
	    return venta;
	}
	
	private boolean VerificarStock(Autoparte autoparte, int cant) {
	    return (autoparte.getStock() >= cant);
	}
	
	private void ValidarMetodoPago(String metodo) {
		if (metodo.equals("tc") && metodo.equals("td") && metodo.equals("ef")) {
			throw new MetodoNoReconocidoExcepcion("Error: El metodo de pago es incorrecto. Solo puede pagar mediante 'tc', 'td' o 'ef'");
		}
	}
	
	private void validarCuotas(int cuotas) {
		if (!(cuotas == 2) && !(cuotas == 3) && !(cuotas == 6)) {
			throw new CuotasInvalidasExcepecion("Error: No puede pagar en " + cuotas + " cuotas. Debe elegir entre 2, 3 o 6 cuotas.");
		}
	}
}
