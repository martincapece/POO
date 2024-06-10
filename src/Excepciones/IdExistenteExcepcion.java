package Excepciones;

public class IdExistenteExcepcion extends RuntimeException {
	public IdExistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
