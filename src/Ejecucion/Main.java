package Ejecucion;

import java.util.Scanner;
import Negocio_Autopartes.*;

public class Main {
	private static Negocio negocio;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		negocio = new Negocio();
		String opcion = "";
		
		do {
			System.out.println("Ingrese una opción (a agregar autoparte, e eliminar autoparte, c calcular el costo individual, t calcular el total, s para salir):  ");
			opcion = sc.next();
			
			if(opcion.equals("a")) {
				agregarAutoparte();
			}else if(opcion.equals("e")) {
				modificarAutoparte();
			}else if(opcion.equals("c")) {
				listarAutoparte();
			}else if(opcion.equals("t")){
				bajaAutoparte();
			}
		}while(!opcion.equals("s"));
	}
	
	public static void agregarAutoparte() {
		System.out.print("Ingresar id de la autoparte: ");
		int id = sc.nextInt();
		
		System.out.print("Ingresar denominacion: ");
		String denominacion = sc.next();
		
		System.out.print("Ingresar descripcion: ");
		String descripcion = sc.next();
		
		System.out.print("Ingresar categoria: ");
		String categoria = sc.next();
		
		System.out.print("Ingresar marca: ");
		String marca = sc.next();
		
		System.out.print("Ingresar vehiculo: ");
		String vehiculo = sc.next();
		
		System.out.print("Ingresar modelo: ");
		String modelo = sc.next();
		
		System.out.print("Ingresar precio: ");
		double precio = sc.nextDouble();
		
		System.out.print("Ingresar stock: ");
		int stock = sc.nextInt();
		
		System.out.print("Ingresar stock minimo: ");
		int stockMinimo = sc.nextInt();
		
		System.out.print("Ingresar enlace: ");
		String enlace = sc.next();
		
		Autoparte autoparte = new Autoparte(id, denominacion, descripcion, categoria, marca, vehiculo, modelo, precio, stock, stockMinimo, enlace);
		
		negocio.CargarAutoparte(autoparte);
	}
	
	public static void modificarAutoparte() {
		
	}
	
	public static void listarAutoparte() {
		negocio.ListarAutopartes();
	}

	public static void bajaAutoparte() {
		System.out.print("Ingresar el id de la autoparte a eliminar: ");
		int id = sc.nextInt();
		
		negocio.EliminarAutoparte(id);
	}
}
