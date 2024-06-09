package Negocio_Autopartes;

import java.util.ArrayList;

import java.util.Scanner;

public class Pedido {
	public static Scanner sc = new Scanner(System.in);

	private int id;
	private String fecha;
	private double montoTotal;
	ArrayList<Autoparte> autopartepedido;
	ArrayList<Integer> autoparteCantidad;
	
	public Pedido(int id, String fecha, double montoTotal) {
		setId(id);
		setFecha(fecha);
		setMontoTotal(montoTotal);
		autopartepedido = new ArrayList<Autoparte>();
		autoparteCantidad = new ArrayList<>();
	}
	
	
	public ArrayList<Autoparte> getAutopartes() {
	    return autopartepedido;
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
	
	public void CancelarPedido() {
		for(int i = 0; i < autopartepedido.size(); i++) {
				autopartepedido.get(i).sumarStock(autoparteCantidad.get(i));
	
		}
		System.out.println("Se devolvio el stock a su origen");
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


	private boolean VerificarStock(Autoparte autoparte, int cant) {
	    return (autoparte.getStock() >= cant);
	}
	
	public Venta convertirAVenta() {
		System.out.println("Opcion 'tc' para tarjeta de credito.");
		System.out.println("Opcion 'td' para tarjeta de debito.");
		System.out.println("Opcion 'ef' para efectivo.");
		System.out.print("Ingrese metodo de pago: ");
		String metodopago = sc.nextLine();

		Venta venta = null;
		
		switch (metodopago) {
		case "tc":
			System.out.print("Ingrese cuotas a pagar (2, 3 o 6): ");
			int cuotas = sc.nextInt();
			venta = new VentaConCredito(this.id, this.fecha, this.montoTotal, cuotas);
			break;
		case "td":
			venta = new VentaConDebito(id, fecha, this.montoTotal);
			break;
		case "ef":
			venta = new VentaConDebito(id, fecha, this.montoTotal);
			break;
		default:
			System.err.println("Metodo de pago no reconocido.");
			convertirAVenta();
			break;
		}
		
	    for (int i = 0; i < autopartepedido.size(); i++) {
	        venta.CargarAutopartePed(autopartepedido.get(i));
	        venta.CargarCantidadPed(autoparteCantidad.get(i));
	    }
	    
	    return venta;
	}


}
