package Excepciones;

public class UsuarioNoEncontradoExcepcion extends RuntimeException {
	public UsuarioNoEncontradoExcepcion(String mensaje) {
		super(mensaje);
	}
}
