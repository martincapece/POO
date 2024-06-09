package Excepciones;

public class EmailInvalidoExcepcion extends RuntimeException {
	public EmailInvalidoExcepcion(String mensaje) {
		super(mensaje);
	}
}
