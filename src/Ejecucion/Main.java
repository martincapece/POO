package Ejecucion;

import java.util.InputMismatchException;
import java.util.Scanner;
import Negocio_Autopartes.*;
import Excepciones.*;

public class Main {
	private static Negocio negocio;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		negocio = new Negocio();
		Usuario administrador= new Usuario(1, "administrador", "1234", "administrador@uade.edu.ar");
		negocio.CargarUsuario(administrador);
		String opcion = "";
		int funcionalidad = 0;	
		String opcionCliente = "";
		String opcionPedido = "";
		Usuario usuario = null;
		boolean continuar = true;
		
		while (continuar) {
			
			if (usuario == null) {
				usuario = iniciarSesion();
				System.out.println();
				System.out.println("¡Bienvenido " + usuario + "!");
			}
			
			try {
				System.out.println("1- Gestionar autopartes");
				System.out.println("2- Agregar clientes");
				System.out.println("3- Gestionar pedidos");
				System.out.println("4- Gestionar ventas");
				System.out.println("5- Cambiar usuario");
				System.out.println("6- Salir");
				System.out.print("Introduzca una opcion: ");
				funcionalidad = sc.nextInt();
				validarOpcion1(funcionalidad);
				
				switch (funcionalidad) {
				case 1:
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
					break;
				case 2:
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
					break;
				case 3:
					do {
						System.out.println();
						System.out.println("a- Iniciar pedido");
						System.out.println("b- Cancelar pedido");
						System.out.println("c- Listar pedidos");
						System.out.println("d- Salir");
						System.out.print("Ingrese una opción: ");
						opcionPedido = sc.next();
						System.out.println();
						
						if(opcionPedido.equals("a")) {
							iniciarPedido();
						}else if(opcionPedido.equals("b")) {
							cancelarPedido();
						}else if(opcionPedido.equals("c")) {
							listarPedidos();
						}
					}while(!opcionPedido.equals("d"));
					break;
				case 4:
					gestionarVentas();
					break;
				case 5:
					usuario = null;
					System.out.println();
					break;
				case 6:
					System.out.println("¡Hasta la proxima " + usuario + "!");
					continuar = false;
					break;
				}
			} catch (OpcionInvalidaExcepcion e) {
				System.err.println(e.getMessage());
				System.out.println();
			} catch (InputMismatchException e) {
				System.err.println("Error: No puede ingresar una cadena de caracteres");
				sc.nextLine();
				System.out.println();
			}
		}
	}
	
	public static Usuario iniciarSesion() {
		try {
			System.out.println("1- Registrarse");
			System.out.println("2- Iniciar Sesion");
			System.out.print("Seleccione una opcion: ");
			int opcion = sc.nextInt();
			boolean valido;
			validarOpcion2(opcion);
			
			if (opcion == 1) {
				int id = 0;
				valido = false;
				
				while (!valido) {
					try {
						System.out.print("Ingresar id: ");
						id = sc.nextInt();
						numeroPositivo(id);
						valido = true;
					} catch (NumeroNegativoExcepcion e) {
						System.err.println(e.getMessage());
					}					
				}
				sc.nextLine();
				
				System.out.print("Ingresar nombre de usuario: ");
				String username = sc.nextLine();
				
				valido = false;
				String contraseña = "";
				
				while (!valido) {
					try {
						System.out.print("Ingresar contraseña: ");
						contraseña = sc.next();
						validarContraseña(contraseña);
						valido = true;
					} catch (LongitudInvalidaExcepcion e) {
						System.err.println(e.getMessage());
					} catch (CaracterEspecialExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				
				valido = false;
				String email = "";
				while (!valido) {
					try {
						System.out.print("Ingresar email: ");
						email = sc.next();
						validarEmail(email);
						valido = true;
					} catch (EmailInvalidoExcepcion e) {
						System.err.println(e.getMessage());
					} catch (EmailVacioExcepcion e) {
						System.err.println(e.getMessage());
					} catch (DominioInvalidoExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				
				Usuario user = new Usuario(id, username, contraseña, email);
				
				negocio.CargarUsuario(user);
				
				return user;
			} else if (opcion == 2) {
				System.out.print("Ingresar id: ");
				int id = sc.nextInt();
				Usuario usuario = negocio.RetornoUsuario(id);
				return usuario;
			}
			return null;	
		} catch (OpcionInvalidaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println("Error: No puede ingresar una cadena de caracteres");
			sc.nextLine();
		} catch (UsuarioExistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (UsuarioNoEncontradoExcepcion e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println();
		iniciarSesion();
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
		sc.nextLine();
		
		System.out.print("Ingresar stock: ");
		int stock = sc.nextInt();
		sc.nextLine();
		
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
		negocio.MostrarIdAutos();
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
		/*boolean estado = validarEmail(email);
		while (!estado) {
			System.out.println("El email no es valido.");
			System.out.print("Introduzca un email valido: ");
			email = sc.next();
			estado = validarEmail(email);
		}
		
		Cliente cliente = new Cliente(id,nombre,direccion,telefono,localidad,provincia,email);
		negocio.AgregarCliente(cliente);*/
	}
	
	public static void listarCliente() {
		negocio.ListarClientes();
	}
	
	public static void listarPedidos() {
		System.out.print("Ingresar el id del cliente para listar sus pedidos: ");
		int idCliente = sc.nextInt();
		Cliente cliente = negocio.RetornoCliente(idCliente);
		
		cliente.ListarPedidos();
	}

	
	public static void iniciarPedido() {
	    System.out.print("Ingrese id del cliente para cargarle su pedido: ");
	    int idcliente = sc.nextInt();
	    Cliente cliente = negocio.RetornoCliente(idcliente);
	    
	    if (cliente == null) {
	        System.out.println("El cliente no existe");
	    } else {
	        System.out.print("Ingrese id del pedido: ");
	        int id = sc.nextInt();
	        
	        System.out.print("Ingrese fecha del pedido: ");
	        String fecha = sc.next();
	        
	        System.out.print("Ingrese monto total del pedido: ");
	        Double monto = sc.nextDouble();
	        
	        Pedido pedido = new Pedido(id, fecha, monto);
	        
	        System.out.print("¿Cuantas autopartes ordenara? ");
	        int numAutopartes = sc.nextInt();
	        
	        for (int i = 0; i <= numAutopartes - 1; i++) {
	        	System.out.print("Ingrese id de la autoparte: ");
	 	        int idAutoparte = sc.nextInt();
	 	        Autoparte autoparte = negocio.RetornoAutoparte(idAutoparte);
	 	        
 	        	if (autoparte == null) {
	                System.out.println("La autoparte con ID: " + idAutoparte + " no existe.");
	                i--;
	            } else {
	                pedido.CargarAutopartePed(autoparte);
	                System.out.print("Ingrese cantidad de la autoparte: ");
	                int cantidad = sc.nextInt();
	                pedido.CargarCantidadPed(cantidad);
            	} 
	        }
		        
	       if (!pedido.getAutopartes().isEmpty()) { // Verifica si se han cargado autopartes en el pedido
	    	   if (pedido.DisminuirStock()) {
	    		   cliente.CargarPedido(pedido);
	    	   } else {
	    		   System.out.println("La operación no se completó debido a falta de stock.");
	    	   }
	        } else {
	        	System.out.println("El pedido no contiene ninguna autoparte.");
	        }
	    }
	}

	public static void gestionarVentas() {
		System.out.print("Ingrese id del cliente para cargarle su venta: ");
		int idcliente = sc.nextInt();
		Cliente cliente = negocio.RetornoCliente(idcliente);
		
		if (cliente == null) {
			System.out.println("Error: No existe un cliente con el ID proporcionado.");
		} else {
			System.out.println("1- Venta directa");
			System.out.println("2- Registrar pedido reservado como venta");
			System.out.print("Ingrese una opcion:  ");
			int tipoventa = sc.nextInt();
			
			if (tipoventa == 1) {
				System.out.print("Ingrese id de la venta: ");
				int id = sc.nextInt();
				
				System.out.print("Ingrese fecha de la venta: ");
				String fecha = sc.nextLine();
				
				System.out.print("Ingrese monto total de la venta: ");
				Double monto = sc.nextDouble();
				
				System.out.println("Opcion 'tc' para tarjeta de credito.");
				System.out.println("Opcion 'td' para tarjeta de debito.");
				System.out.println("Opcion 'ef' para efectivo.");
				System.out.print("Ingrese metodo de pago: ");
				String metodopago = sc.nextLine();

				Venta venta = null;
				
				switch (metodopago) {
				case "tc":
					System.out.print("Ingrese cuotas a pagar: ");
					int cuotas = sc.nextInt();
					venta = new VentaConCredito(id, fecha, monto, cuotas);
					break;
				case "td":
					venta = new VentaConDebito(id, fecha, monto);
					break;
				case "ef":
					venta = new VentaConDebito(id, fecha, monto);
					break;
				default:
					System.err.println("Metodo de pago no reconocido.");
					gestionarVentas();
					break;
				}
				
				System.out.print("¿Cuantas autopartes ordenara? ");
		        int numAutopartes = sc.nextInt();
				
		        for (int i = 0; i <= numAutopartes - 1; i++) {
		        	System.out.print("Ingrese id de la autoparte: ");
		 	        int idAutoparte = sc.nextInt();
		 	        Autoparte autoparte = negocio.RetornoAutoparte(idAutoparte);
		 	        
	 	        	if (autoparte == null) {
		                System.out.println("La autoparte con ID: " + idAutoparte + " no existe.");
		                i--;
		            } else {
		                venta.CargarAutopartePed(autoparte);
		                System.out.print("Ingrese cantidad de la autoparte: ");
		                int cantidad = sc.nextInt();
		                venta.CargarCantidadPed(cantidad);
	            	}
		        }
				
				if (venta.DisminuirStock()) {
			        System.out.println("el monto total de la venta sera: " + venta.CalcularTotal());
			        cliente.CargarVenta(venta);
			    } else {
			        System.out.println("La operación no se completó debido a falta de stock.");
			    }
			} else {
				System.out.println("La lista de pedidos disponibles para pasar a ventas es: ");
				cliente.ListarPedidos();
				
				System.out.print("Ingrese id del pedido que pasara a venta: ");
				int id = sc.nextInt();
				
				// Recuperar el pedido del cliente
			    Pedido pedido = cliente.RetornoPedido(id);
			    
			    if (pedido != null) {
			        // Convertir el pedido en una venta
			        Venta venta = pedido.convertirAVenta();
				
			        // Agregar la venta a la lista de ventas del cliente
			        cliente.CargarVenta(venta);
	
			        System.out.println("El pedido se ha convertido en una venta y se agregó a la lista del cliente.");
			        
			        System.out.println("el monto total es de: " + venta.CalcularTotal());
			    	} else {
				        System.out.println("El pedido con el ID ingresado no existe.");
				    }
			}
		}
	}
	
	public static void cancelarPedido() {
		System.out.println("Ingrese id del cliente para eliminar el pedido de su lista");
		int idCliente = sc.nextInt();
		
		System.out.println("Ingrese id del pedido para eliminarlo");
		int idPedido = sc.nextInt();
		
		Cliente cliente = negocio.RetornoCliente(idCliente);
		Pedido pedido = cliente.RetornoPedido(idPedido);
		
		pedido.CancelarPedido();
		
		cliente.EliminarPedido(idPedido);
		System.out.println("Se elimino correctamente el pedido");
	}
		
	public static boolean valirdarLongitud(String cadena) {
		boolean valido = true;
		
		if (cadena.length() >= 1 && cadena.length() <= 50) {
			return valido;
		}
		
		return !valido;
	}
	
	public static void validarContraseña(String contraseña){
		if (contraseña.length() < 8 || contraseña.length() > 32) {
			throw new LongitudInvalidaExcepcion("Error: La contraseña debe contener entre 8 y 32 caracteres");
		}
		contieneCaracterEspecial(contraseña);
	}
	
	public static void contieneCaracterEspecial(String cadena) {
		char[] caracteres = cadena.toCharArray();
		
		for (char caracter : caracteres) {
			if (!Character.isLetterOrDigit(caracter)) {
				throw new CaracterEspecialExcepcion("Error: La contraseña no puede tener caracteres especiales");
			}
		}
	}
	
	public static void validarEmail(String email) {
		if (email == null || !email.contains("@") || !email.contains(".")) {
			throw new EmailInvalidoExcepcion("Error: El email debe tener como minimo un '@' y un dominio con '.'.");
		}
		
		String[] partes = email.split("@");
		String direccionCorreo = partes[0];
		String servidor = partes[1];	
		String[] partesServidor = servidor.split("\\.");
		
		if (direccionCorreo.isEmpty() && servidor.isEmpty()) {
			throw new EmailVacioExcepcion("Error: El email no puede estar vacio.");
		}
		
		if (partesServidor.length < 1 || partesServidor.length > 3) {
			throw new DominioInvalidoExcepcion("Error: Se espera que el dominio tenga entre 1 y 3 subdominios separados por puntos");
		}
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
	
	public static void validarOpcion1(int numero) {
		if (numero < 1 || numero > 6) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. El numero debe ser 1, 2, 3, 4, 5 o 6.");
		}
	}
	
	public static void validarOpcion2(int numero) {
		if (numero < 1 || numero > 2) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. El numero debe ser 1 o 2.");
		}
	}
	
	public static void numeroPositivo(int numero) {
		if (numero < 0) {
			throw new NumeroNegativoExcepcion("Error: El numero debe ser positivo.");
		}
	}

}