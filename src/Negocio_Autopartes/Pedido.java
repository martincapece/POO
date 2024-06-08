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
	
	public void CancelarPedido() {
		for(int i = 0; i < autopartepedido.size(); i++) {
				autopartepedido.get(i).sumarStock(autoparteCantidad.get(i));
	
		}
		System.out.println("Se devolvio el stock a su origen");
	}
	
	public Venta convertirAVenta() {
	    // Solicitar método de pago
	    System.out.print("Ingrese metodo de pago: td para tarjeta de debito, tc para tarjeta de credito, ef para efectivo: ");
	    String metodopago = sc.next();

	    // Solicitar cuotas si el método de pago es "td" o "tc"
	    int cuotas;
	    if (metodopago.equals("td") || metodopago.equals("tc")) {
	        System.out.print("Ingrese cuotas a pagar 2 3 o 6: ");
	        cuotas = sc.nextInt();
	    } else {
	        cuotas = 1;
	    }

	    // Crear la venta con los datos ingresados por el usuario
	    Venta venta = new VentaReserva(this.id, this.fecha, this.montoTotal, metodopago, cuotas);

	    // Cargar las autopartes y cantidades a la venta
	    for (int i = 0; i < autopartepedido.size(); i++) {
	        venta.CargarAutopartePed(autopartepedido.get(i));
	        venta.CargarCantidadPed(autoparteCantidad.get(i));
	    }
	    return venta;
	}


}
