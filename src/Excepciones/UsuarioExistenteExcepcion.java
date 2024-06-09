package Excepciones;

public class UsuarioExistenteExcepcion extends RuntimeException {
	public UsuarioExistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
