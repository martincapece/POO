package Negocio_Autopartes;

public class Usuario {
	int id;
	String nombre;
	String contraseña;
	
	public Usuario(int id, String nombre, String contraseña) {
		setId(id);
		setNombre(nombre);
		setContraseña(contraseña);
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}
