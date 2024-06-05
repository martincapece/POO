package Ejecucion;

import java.util.Scanner;
import Negocio_Autopartes.*;

public class Main {
	private static Negocio negocio;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		negocio = new Negocio();
		Usuario administrador= new Usuario(1, "administrador", "1234", "administrador@uade.edu.ar");
		negocio.CargarUsuario(administrador);
		String opcion = "";
		String funcionalidad = "";	
		String opcionCliente = "";
		String opcionPedido = "";
		Usuario usuario = null;
		
		while (true) {
			if (usuario == null) {
				usuario = iniciarSesion();				
			}
			
			System.out.println("¡Bienvenido " + usuario.getUsuario() + "!");
			System.out.println("1- Gestionar autopartes");
			System.out.println("2- Agregar clientes");
			System.out.println("3- Gestionar pedidos");
			System.out.println("4- Gestionar ventas");
			System.out.println("5- Salir");
			System.out.print("Introduzca una opcion: ");
			funcionalidad = sc.next();
			
			if(funcionalidad.equals("1")) {
				do {
					System.out.println();
					System.out.println("a- Agregar autoparte");
					System.out.println("b- Eliminar autoparte");
					System.out.println("c- Listar autopartes");
					System.out.println("d- Modificar autoparte");
					System.out.println("e- Modificar stock");
					System.out.println("f- Salir");
					System.out.print("Ingrese una opción: ");
					opcion = sc.next();
					System.out.println();
			
					if(opcion.equals("a")) {
						agregarAutoparte();
					}else if(opcion.equals("b")) {
						bajaAutoparte();
					}else if(opcion.equals("c")) {
						listarAutoparte();
					}else if(opcion.equals("d")){
						modificarAutoparte();
					}else if(opcion.equals("e")) {
						modificarStock();
					}
				} while(!opcion.equals("f"));
			} else if(funcionalidad.equals("2")) {
				do {
					System.out.println();
					System.out.println("a- Agregar un nuevo cliente");
					System.out.println("b- Listar clientes");
					System.out.println("c- Salir");
					System.out.print("Ingrese una opción: ");
					opcionCliente = sc.next();
					System.out.println();
					
					if(opcionCliente.equals("a")) {
						agregarCliente();
					}else if(opcionCliente.equals("b")) {
						listarCliente();
					}
				}while(!opcionCliente.equals("c"));
			} else if(funcionalidad.equals("3")) {
				do {
					System.out.println();
					System.out.println("a- Iniciar pedido");
					System.out.println("b- Cancelar pedido");
					System.out.println("c- Salir");
					System.out.print("Ingrese una opción: ");
					opcionPedido = sc.next();
					System.out.println();
					
					if(opcionPedido.equals("a")) {
						iniciarPedido();
					}else if(opcionPedido.equals("b")) {
						cancelarPedido();
					}
				}while(!opcionPedido.equals("c"));	
			}else if(funcionalidad.equals("4")) {
				gestionarVentas();
			} else {break;}
			
	}
	
	System.out.println("¡Hasta la proxima!");
		
}
	public static Usuario iniciarSesion() {
		System.out.println("1- Registrarse");
		System.out.println("2- Iniciar Sesion");
		System.out.print("Seleccione una opcion: ");
		int opcion = sc.nextInt();
		if (opcion == 1) {
			System.out.print("Ingresar id: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Ingresar nombre de usuario: ");
			String username = sc.nextLine();
			
			System.out.print("Ingresar contraseña: ");
			String contraseña = sc.next();
			boolean estadoContraseña = validarContraseña(contraseña);
			while (!estadoContraseña) {
				System.out.println("La contraseña es invalida.");
				System.out.print("Ingresar una contraseña valida: ");
				contraseña = sc.next();
				estadoContraseña = validarContraseña(contraseña);
			}
			
			System.out.print("Ingresar email: ");
			String email = sc.next();
			boolean estadoEmail = validarEmail(email);
			while (!estadoEmail) {
				System.out.println("La contraseña es invalida.");
				System.out.print("Ingresar una contraseña valida: ");
				email = sc.next();
				estadoEmail = validarContraseña(email);
			}
			
			Usuario user = new Usuario(id, username, contraseña, email);
			
			negocio.CargarUsuario(user);
			
			return user;
		} else if (opcion == 2) {
			System.out.println("Ingresar id: ");
			int Id = sc.nextInt();
			
			Usuario usuario = negocio.RetornoUsuario(Id);
			if (usuario == null) {
				System.out.println("No se encontro ningun usuario con el ID: " + Id);
				iniciarSesion();
			} else {
				return usuario;
			}
		} else {
			System.out.println("La opcion ingresada no es valida.");
			System.out.println();
			iniciarSesion();
		}
		return null;
	}
	
	public static void agregarAutoparte() {
		System.out.print("Ingresar id de la autoparte: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Ingresar denominacion: ");
		String denominacion = sc.nextLine();
		
		System.out.print("Ingresar descripcion: ");
		String descripcion = sc.nextLine();
		
		System.out.print("Ingresar categoria: ");
		String categoria = sc.nextLine();
		
		boolean estadoCat = valirdarLongitud(categoria);
		while (!estadoCat) {
			System.out.println("La categoria debe contener menos de 50 caracteres.");
			System.out.println("Ingrese nuevamente la categoria: ");
			categoria = sc.nextLine();
			estadoCat = valirdarLongitud(categoria);
		}
		
		System.out.print("Ingresar marca: ");
		String marca = sc.nextLine();
		
		boolean estadoMar = valirdarLongitud(marca);
		while (!estadoMar) {
			System.out.println("La categoria debe contener menos de 50 caracteres.");
			System.out.println("Ingrese nuevamente la categoria: ");
			marca = sc.nextLine();
			estadoMar = valirdarLongitud(marca);
		}
		
		System.out.print("Ingresar vehiculo: ");
		String vehiculo = sc.nextLine();
		
		System.out.print("Ingresar modelo: ");
		String modelo = sc.nextLine();
		
		System.out.print("Ingresar precio: $");
		double precio = sc.nextDouble();
		
		System.out.print("Ingresar stock: ");
		int stock = sc.nextInt();
		
		System.out.print("Ingresar stock minimo: ");
		int stockMinimo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Ingresar enlace: ");
		String enlace = sc.nextLine();
		
		Autoparte autoparte = new Autoparte(id, denominacion, descripcion, categoria, marca, vehiculo, modelo, precio, stock, stockMinimo, enlace);
		
		negocio.CargarAutoparte(autoparte);
	}
	
	public static void modificarAutoparte() {		
		System.out.print("Indicar ID de la autoparte a modificar: ");
		int idAutoparte = sc.nextInt();
		
		Autoparte autoparte = negocio.RetornoAutoparte(idAutoparte);
		
		System.out.println("1- Id");
		System.out.println("2- Denominacion");
		System.out.println("3- Descripcion");
		System.out.println("4- Categoria");
		System.out.println("5- Marca");
		System.out.println("6- Vehiculo");
		System.out.println("7- Modelo");
		System.out.println("8- Precio");
		System.out.println("9- Stock");
		System.out.println("10- Stock Minimo");
		System.out.println("11- Enlace");
		System.out.print("Inidicar que desea modificar: ");
		String tipo = sc.next();
		
		
		
		switch (tipo) {
		case "1":
			System.out.print("Indicar nuevo id: ");
			int nuevoId = sc.nextInt();
			
			do {
				System.out.println("Ese id ya esta siendo utilizado por otra autoparte.");
				System.out.print("Intente con otro id: ");
				nuevoId = sc.nextInt();
			} while (negocio.CorroborarExistencia(nuevoId));
			
			autoparte.setId(nuevoId);
			break;
		case "2":
			System.out.print("Ingresar nueva denominacion: ");
			String nuevaDenominacion = sc.next();
			
			autoparte.setDenominacion(nuevaDenominacion);
			break;
		case "3":
			System.out.print("Ingresar descripcion: ");
			String nuevaDescripcion = sc.next();
			
			autoparte.setDescripcion(nuevaDescripcion);
			break;
		case "4":
			System.out.print("Ingresar categoria: ");
			String nuevaCategoria = sc.next();
			
			boolean estadoNuevaCat = valirdarLongitud(nuevaCategoria);
			while (!estadoNuevaCat) {
				System.out.println("La categoria debe contener menos de 50 caracteres.");
				System.out.println("Ingrese nuevamente la categoria: ");
				nuevaCategoria = sc.nextLine();
				estadoNuevaCat = valirdarLongitud(nuevaCategoria);
			}
			
			autoparte.setCategoria(nuevaCategoria);
			break;
		case "5":
			System.out.print("Ingresar marca: ");
			String nuevaMarca = sc.next();
			
			boolean estadoNuevaMar= valirdarLongitud(nuevaMarca);
			while (!estadoNuevaMar) {
				System.out.println("La categoria debe contener menos de 50 caracteres.");
				System.out.println("Ingrese nuevamente la categoria: ");
				nuevaMarca = sc.nextLine();
				estadoNuevaMar = valirdarLongitud(nuevaMarca);
			}
			
			autoparte.setMarca(nuevaMarca);
			break;
		case "6":
			System.out.print("Ingresar vehiculo: ");
			String nuevoVehiculo = sc.next();
			
			autoparte.setVehiculo(nuevoVehiculo);
			break;
		case "7":
			System.out.print("Ingresar modelo: ");
			String nuevoModelo = sc.next();
			
			autoparte.setModelo(nuevoModelo);
			break;
		case "8":
			System.out.print("Ingresar precio: $");
			double nuevoPrecio = sc.nextDouble();
			
			autoparte.setPrecio(nuevoPrecio);
			break;
		case "9":
			System.out.print("Ingresar stock: ");
			int nuevoStock = sc.nextInt();
			
			autoparte.setStock(nuevoStock);
			break;
		case "10":
			System.out.print("Ingresar stock minimo: ");
			int nuevoStockMinimo = sc.nextInt();
			
			autoparte.setStockMinimo(nuevoStockMinimo);
			break;
		case "11":
			System.out.print("Ingresar enlace: ");
			String nuevoEnlace = sc.next();
			
			autoparte.setEnlace(nuevoEnlace);
			break;
		}
		System.out.print("¡El cambio fue aplicado con exito!");
		
	}
	
	public static void listarAutoparte() {
		negocio.ListarAutopartes();
	}

	public static void bajaAutoparte() {
		System.out.print("Ingresar el id de la autoparte a eliminar: ");
		int id = sc.nextInt();	
		negocio.EliminarAutoparte(id);
	}
	
	public static void modificarStock() {
		int id = 0;
		int cantidad = 0;
		System.out.println("1- Cargar stock");
		System.out.println("2- ELiminar stock");
		System.out.print("Ingrese la opcion que desee: ");
		int modificacion = sc.nextInt();
		
		switch (modificacion) {
		case 1:
			System.out.print("Ingresar el id de la autoparte para cargar stock: ");
			id = sc.nextInt();	
			
			System.out.print("Ingresar la cantidad que desea agregar: ");
			cantidad = sc.nextInt();	
			
			negocio.AgregarStock(id,cantidad);
			break;
		case 2:
			System.out.print("Ingresar el id de la autoparte para eliminar stock: ");
			id = sc.nextInt();	
			
			System.out.print("Ingresar la cantidad que desea eliminar: ");
			cantidad = sc.nextInt();
			
			negocio.QuitarStock(id,cantidad);
			break;
		default:
			System.out.println("Debe ingresar una de las dos opciones.");
			modificarStock();
		}
	}
	
	public static void agregarCliente() {
		System.out.print("Ingrese id del cliente: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Ingrese nombre del cliente: ");
		String nombre = sc.nextLine();
		
		System.out.print("Ingrese direccion del cliente: ");
		String direccion = sc.nextLine();
		
		System.out.print("Ingrese telefono del cliente: ");
		String telefono = sc.nextLine();
		boolean estadoTel = contieneGuion(telefono);
		while (!estadoTel) {
			System.out.println("El telefono debe contener un guion luego de la caracteristica.");
			System.out.print("Introduzca un numero de telefono valido: ");
			telefono = sc.nextLine();
			estadoTel = contieneGuion(telefono);
		} 
		
		System.out.print("Ingrese localidad del cliente: ");
		String localidad = sc.nextLine();
		boolean estadoLoc = valirdarLongitud(localidad);
		while (!estadoLoc) {
			System.out.println("La localidad debe contener menos de 50 caracteres.");
			System.out.println("Ingrese nuevamente la localidad: ");
			localidad = sc.nextLine();
			estadoLoc = valirdarLongitud(localidad);
		}
		
		System.out.print("Ingrese provincia del cliente: ");
		String provincia = sc.nextLine();
		
		System.out.print("Ingrese email del cliente: ");
		String email = sc.nextLine();
		boolean estado = validarEmail(email);
		while (!estado) {
			System.out.println("El email no es valido.");
			System.out.print("Introduzca un email valido: ");
			email = sc.next();
			estado = validarEmail(email);
		}
		
		Cliente cliente = new Cliente(id,nombre,direccion,telefono,localidad,provincia,email);
		negocio.AgregarCliente(cliente);
	}
	
	public static void listarCliente() {
		negocio.ListarClientes();
	}
	
	public static void iniciarPedido() {
		System.out.print("Ingrese id del cliente para cargarle su pedido: ");
		int idcliente = sc.nextInt();
		Cliente cliente = negocio.RetornoCliente(idcliente);
		System.out.print("Ingrese id del pedido: ");
		int id = sc.nextInt();
		System.out.print("Ingrese fecha del pedido: ");
		String fecha = sc.next();
		System.out.print("Ingrese monto total del pedido: ");
		Double monto = sc.nextDouble();
		Pedido pedido = new Pedido(id,fecha,monto);

		System.out.println("Ingrese id de la autoparte, ingrese -1 para salir");
		int idautoparte = sc.nextInt();
		while(idautoparte!=-1) {
			Autoparte autoparte = negocio.RetornoAutoparte(idautoparte);
			pedido.CargarAutopartePed(autoparte);
			System.out.println("Ingrese cantidad de la autoparte");
			int cantidad = sc.nextInt();
			pedido.CargarCantidadPed(cantidad);
			System.out.println("Ingrese id de la autoparte, ingrese -1 para salir");
			idautoparte = sc.nextInt();
		}
		pedido.disminuirStock();
	}
	
	public static void cancelarPedido() {
		System.out.println("Ingrese id del cliente para eliminar el pedido de su lista");
		int idcliente = sc.nextInt();
		System.out.println("Ingrese id del pedido para eliminarlo");
		int idpedido = sc.nextInt();
		Cliente cliente = negocio.RetornoCliente(idcliente);
		Pedido pedido = cliente.retornoPedido(idpedido);
		pedido.CancelarPedido();
	}
	
	public static boolean valirdarLongitud(String cadena) {
		boolean valido = true;
		
		if (cadena.length() >= 1 && cadena.length() <= 50) {
			return valido;
		}
		
		return !valido;
	}
	
	public static boolean validarContraseña(String contraseña){
		boolean valido = true;
		
		boolean resultado = contieneCaracterEspecial(contraseña);
		if ((contraseña.length() < 8 || contraseña.length() > 32) || resultado) {
			return !valido;
		}
		
		return valido;
	}
	
	public static boolean contieneCaracterEspecial(String cadena) {
		boolean contiene = true;
		char[] caracteres = cadena.toCharArray();
		
		for (char caracter : caracteres) {
			if (!Character.isLetterOrDigit(caracter)) {
				return contiene;
			}
		}
		
		return !contiene;
	}
	
	public static boolean validarEmail(String email) {
		boolean valido = true;
		
		String[] partes = email.split("@");

		if (email == null || !email.contains("@") || !email.contains(".") || partes.length != 2) {
			return !valido;
		}
		
		String direccionCorreo = partes[0];
		String servidor = partes[1];	
		String[] partesServidor = servidor.split("\\.");
		
		if ((direccionCorreo.isEmpty() && servidor.isEmpty()) || (partesServidor.length < 1 || partesServidor.length > 3)|| contieneNumero(servidor)) {
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
	
	public static boolean contieneGuion(String cadena) {
		boolean contiene = true;
		char[] caracteres = cadena.toCharArray();
		
		for (int i = 0; i < caracteres.length; i++) {
			char c = caracteres[i];
			if (c == '-' && (i == 2 || i == 3)) {
				return contiene;
			}
		}
		return !contiene;
	}
	
	public static void gestionarVentas() {
		
	}

}