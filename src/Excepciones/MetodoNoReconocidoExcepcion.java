package Excepciones;

public class MetodoNoReconocidoExcepcion extends RuntimeException {
	public MetodoNoReconocidoExcepcion(String mensaje) {
		super(mensaje);
	}
}
