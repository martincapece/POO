package Negocio_Autopartes;

public class Usuario {
	private int id;
	private String usuario;
	private String contraseña;
	private String email;
	
	public Usuario(int id, String usuario, String contraseña, String email) {
		setId(id);
		setUsuario(usuario);
		setContraseña(contraseña);
		setEmail(email);		
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return this.usuario;
	}
}
