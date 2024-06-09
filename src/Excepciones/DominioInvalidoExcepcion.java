package Excepciones;

public class DominioInvalidoExcepcion extends RuntimeException {
	public DominioInvalidoExcepcion(String mensaje) {
		super(mensaje);
	}
}
