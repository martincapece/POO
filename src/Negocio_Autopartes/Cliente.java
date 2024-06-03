package Negocio_Autopartes;
import java.util.ArrayList;

public class Cliente {
	
	int id;
	String nombre;
	String direccion;
	int telefono;
	String localidad;
	String provincia;
	String email;
	ArrayList<Pedido> pedidos;
	
	
	public Cliente(int id, String nombre, String direccion, int telefono, String localidad, String provincia, String email) {
		setId(id);
		setNombre(nombre);
		setDireccion(direccion);
		setTelefono(getTelefono());
		setLocalidad(localidad);
		setProvincia(provincia);
		setEmail(email);
		pedidos = new ArrayList<Pedido>();
	}
	
	
	public Pedido retornoPedido(int id) {
		for(Pedido pedido: pedidos) {
			if(pedido.getId()==id) {
				return pedido;
			}else {
				System.out.println("El pedido no existe");
				return null;
			}
		}
		return null;
	}
	
	
	public void CargarPedido(Pedido pedido) {
		pedidos.add(pedido);
		System.out.println("Se agrego el pedido a la lista del cliente.");
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}