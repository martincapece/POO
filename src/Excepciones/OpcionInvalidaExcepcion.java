package Excepciones;

public class OpcionInvalidaExcepcion extends RuntimeException {
	public OpcionInvalidaExcepcion(String mensaje) {
		super(mensaje);
	}
}
