package Excepciones;

public class ObjetoInexistenteExcepcion extends RuntimeException {
	public ObjetoInexistenteExcepcion(String mensaje) {
		super(mensaje);
	}
}
