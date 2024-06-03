package Testing;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String entrada = sc.next();
		while (entrada != "-1") {
			System.out.println(validarEmail(entrada));
			entrada = sc.next();		}
	}
	
	public static boolean validarEmail(String email) {
		boolean valido = true;
		
		String[] partes = email.split("@");

		if (email == null || !email.contains("@") || !email.contains(".") || partes.length != 2) {
			return !valido;
		}
		
		System.out.println("Mail actual: " + email);
		
		String direccionCorreo = partes[0];
		System.out.println("DireccionCorreo: " + direccionCorreo);
		
		String servidor = partes[1];
		System.out.println("Servidor: " + servidor);
		
		String[] partesServidor = servidor.split("\\.");
		
		System.out.println(partesServidor.length);

		for (String partesq : partesServidor) {
			System.out.println("Array partes del sv: " + partesq);			
		}
		
		if ((direccionCorreo.isEmpty() && servidor.isEmpty()) || partesServidor.length != 2 || contieneNumero(servidor)) {
			return !valido;
		}
		
		return valido;
	}
	
	public static boolean contieneNumero(String cadena) {
		boolean contiene = true;
		
		for (char c : cadena.toCharArray()) {
			if (Character.isDigit(c)) {
				return contiene;
			}
		}
		
		return !contiene;
	}
}
