package Excepciones;

public class EmailVacioExcepcion extends RuntimeException {
	public EmailVacioExcepcion(String mensaje) {
		super(mensaje);
	}
}
