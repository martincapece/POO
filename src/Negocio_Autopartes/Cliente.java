package Negocio_Autopartes;

import java.util.ArrayList;
import Excepciones.*;

public class Cliente {
	
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String localidad;
	private String provincia;
	private String email;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Venta> ventas;
	
	
	public Cliente(int id, String nombre, String direccion, String telefono, String localidad, String provincia, String email) {
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(getTelefono());
		setLocalidad(localidad);
		setProvincia(provincia);
		setEmail(email);
		pedidos = new ArrayList<Pedido>();
		ventas = new ArrayList<Venta>();
	}
	
	
	public Pedido RetornoPedido(int id) {
		for(Pedido pedido: pedidos) {
			if(pedido.getId() == id) {
				return pedido;
			}
		}
		throw new ObjetoInexistenteExcepcion("Error: No se encontro ningun pedido con el ID: " + id);
	}
	
	public Venta RetornoVenta(int id) {
		for(Venta venta: ventas) {
			if(venta.getId()==id) {
				return venta;
			}else {
				System.out.println("la venta no existe");
				return null;
			}
		}
		return null;
	}
	
	public void CargarPedido(Pedido pedido) {
		pedidos.add(pedido);
		System.out.println("Se agrego el pedido a la lista del cliente.");
	}
	
	public void CargarVenta(Venta venta) {
		ventas.add(venta);
		System.out.println("Se agrego el pedido a la lista del cliente.");
	}
	
	public void EliminarPedido(Pedido pedido) {
		pedidos.remove(pedido);
		System.out.println("Se elimino el pedido del cliente.");
	}
	
	public boolean ComprobarPedido(int id) {
		boolean existe = true;
		
		for (Pedido pedido : pedidos) {
			if (pedido.getId() == id) {
				return existe;
			}
		}
		return !existe;
	}
	
	public void ListarPedidos() {
        if (pedidos.isEmpty()) {
        	throw new ListaVaciaExcepcion("Error: El cliente no ha realizado ningun pedido.");
        }

        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId() + ", Fecha: " + pedido.getFecha() + ", Total: " + pedido.getMontoTotal());
        }
        
    }
	
	public void ListarVentas() {
        if (ventas.isEmpty()) {
            throw new ListaVaciaExcepcion("Error: El cliente no ha realizado ninguna venta.");
        } else {
            for (Venta venta : ventas) {
                System.out.println("ID: " + venta.getId() + ", Fecha: " + venta.getFecha() + ", Total: " + venta.getMontoTotal());
            }
        }
    }
	
	public void SacarListaPedido(Pedido pedidox) {
	    boolean existe = this.ComprobarPedido(this.getId());
	    
	    if (!existe) {
	        throw new ObjetoInexistenteExcepcion("Error: La autoparte con ID: " + id + " no existe.");
	    } else {
	        Pedido elegido = null;
	        for (Pedido pedido : pedidos) {
	            if (pedido.getId() == id) {
	                elegido = pedido;
	                break;
	            }
	        }
	        if (elegido != null) {
	            pedidos.remove(elegido);
	            System.out.println("¡La autoparte fue eliminada con éxito!");
	        }
	    }
        
    }
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void ConvertirPedidoAVenta(Pedido pedido) {
	    Venta venta = pedido.convertirAVenta();
	    ventas.add(venta);
	    System.out.println("El pedido se ha convertido en una venta y se agregó a la lista del cliente.");
	}

	
	
}