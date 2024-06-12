package Excepciones;

public class FechaNoValidaExcepcion extends RuntimeException {
	public FechaNoValidaExcepcion(String mensaje) {
		super(mensaje);
	}
}
