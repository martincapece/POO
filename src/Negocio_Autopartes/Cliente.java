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
	private ArrayList<Pedido> pedidos;
	
	
	public Cliente(int id, String nombre, String direccion, String telefono, String localidad, String provincia, String email) {
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