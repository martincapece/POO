package Negocio_Autopartes;
import java.util.ArrayList;

public class Cliente {
	
	private int id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String localidad;
	private String provincia;
	private String email;
	private ArrayList<Venta> pedidos;
	
	
	public Cliente(int id, String nombre, String direccion, String telefono, String localidad, String provincia, String email) {
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(getTelefono());
		setLocalidad(localidad);
		setProvincia(provincia);
		setEmail(email);
		pedidos = new ArrayList<Venta>();
	}
	
	
	public Venta RetornoPedido(int id) {
		for(Venta pedido: pedidos) {
			if(pedido.getId()==id) {
				return pedido;
			}else {
				System.out.println("El pedido no existe");
				return null;
			}
		}
		return null;
	}
	
	
	public void CargarPedido(Venta pedido) {
		pedidos.add(pedido);
		System.out.println("Se agrego el pedido a la lista del cliente.");
	}
	
	public void EliminarPedido(int id) {
		for (Venta pedido : pedidos) {
			if (pedido.getId() == id) {
				pedidos.remove(pedido);
			}
		}
		System.out.println("Se elimino el pedido del cliente.");
	}
	
	public void ListarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos por parte del cliente.");
        } else {
            for (Venta pedido : pedidos) {
                System.out.println("ID: " + pedido.getId() + ", Fecha: " + pedido.getFecha() + ", Total: " + pedido.getMontoTotal());
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
}