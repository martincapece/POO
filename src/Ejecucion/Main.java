package Ejecucion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		String tipoVenta = "";
		Usuario usuario = null;
		boolean continuar = true;
		
		while (continuar) {
			
			if (usuario == null) {
				boolean valido = false;
				
				while (!valido) {
					try {
						usuario = iniciarSesion();
						if (usuario == null) {
							throw new ObjetoInexistenteExcepcion("Error: El usuario no pudo loguearse correctamente.");
						}
						valido = true;
					} catch (ObjetoInexistenteExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				System.out.println();
				System.out.println("¡Bienvenido " + usuario.getUsuario() + "!");					
			}
			
			try {
				System.out.println();
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
						try {
							System.out.println();
							System.out.println("a- Agregar autoparte");
							System.out.println("b- Eliminar autoparte");
							System.out.println("c- Listar autopartes");
							System.out.println("d- Modificar autoparte");
							System.out.println("e- Modificar stock");
							System.out.println("f- Salir");
							
							try {
								System.out.print("Ingrese una opción: ");
								opcion = sc.next();
								opcion = opcion.toLowerCase();
								validarOpcion3(opcion);
							} catch (OpcionInvalidaExcepcion e) {
								System.err.print(e.getMessage());
								continue;
							}
							
							System.out.println();
							
							if(opcion.equals("a")) {
								agregarAutoparte();
							}else if(opcion.equals("b")) {
								bajaAutoparte();
							}else if(opcion.equals("c")) {
								listarAutoparte();
							}else if(opcion.equals("d")) {
								modificarAutoparte();
							}else if(opcion.equals("e")) {
								modificarStock();
							}
						} catch (ListaVaciaExcepcion e) {
							System.err.println(e.getMessage());
						}
					} while(!opcion.equals("f"));
					break;
				case 2:
					try {
						do {
							System.out.println();
							System.out.println("a- Agregar un nuevo cliente");
							System.out.println("b- Listar clientes");
							System.out.println("c- Salir");
							System.out.print("Ingrese una opción: ");
							opcionCliente = sc.next();
							opcionCliente = opcionCliente.toLowerCase();
							try {
								validarOpcion5(opcionCliente);							
							} catch (OpcionInvalidaExcepcion e) {
								System.err.println(e.getMessage());
							}
							
							if(opcionCliente.equals("a")) {
								agregarCliente();
							}else if(opcionCliente.equals("b")) {
								listarClientes();
							}
						}while(!opcionCliente.equals("c"));												
					} catch (ListaVaciaExcepcion e) {
						System.err.println(e.getMessage());
						System.out.println();
					}
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
						opcionPedido = opcionPedido.toLowerCase();
						try {
							validarOpcion6(opcionPedido);							
						} catch (OpcionInvalidaExcepcion e) {
							System.err.println(e.getMessage());
						}
						
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
						do {
							System.out.println();
							System.out.println("a- Venta directa");
							System.out.println("b- Registrar pedido reservado como venta");
							System.out.println("c- Listar ventas de clientes");
							System.out.println("d- Listar ventas del negocio");
							System.out.println("e- Salir");			
							System.out.print("Ingrese una opcion:  ");
							tipoVenta = sc.next();
							tipoVenta = tipoVenta.toLowerCase();
							try {	
								validarOpcion7(tipoVenta);
							} catch (OpcionInvalidaExcepcion e) {
								System.err.println(e.getMessage());
							}
							
							if(tipoVenta.equals("a")) {
								realizarVenta();
							}else if(tipoVenta.equals("b")) {
								convertirPedido();
							}else if(tipoVenta.equals("c")) {
								listarVentasCliente();
							}else if(tipoVenta.equals("d")) {
								listarVentasNegocio();
							}
						}while(!tipoVenta.equals("e"));
					} catch (ListaVaciaExcepcion e) {
						System.err.println(e.getMessage());
					}
					break;
				case 5:
					usuario = null;
					System.out.println();
					break;
				case 6:
					System.out.println("¡Hasta la proxima " + usuario.getUsuario() + "!");
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
						validarPositivo(id);
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
						validarLongitudContraseña(contraseña);
						valido = true;
					} catch (LongitudInvalidaExcepcion e) {
						System.err.println(e.getMessage());
					} catch (CuotasInvalidasExcepecion e) {
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
		} catch (OpcionInvalidaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.err.println("Error: No puede ingresar una cadena de caracteres");
			sc.nextLine();
		} catch (ObjetoExistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (UsuarioNoEncontradoExcepcion e) {
			System.err.println(e.getMessage());
		}
		
		return null;	

	}
	
	public static void agregarAutoparte() {
		boolean valido = false;
		try {
			int id = 0;
			while (!valido) {
				try {
					System.out.print("Ingresar id de la autoparte: ");
					id = sc.nextInt();
					sc.nextLine();						
					validarPositivo(id);
					valido = true;
				} catch (NumeroNegativoExcepcion e) {
					System.err.println(e.getMessage());
				}
			}
			
			System.out.print("Ingresar denominacion: ");
			String denominacion = sc.nextLine();
			
			System.out.print("Ingresar descripcion: ");
			String descripcion = sc.nextLine();
			
			String categoria = "";
			valido = false;
			while (!valido) {
				try {
					System.out.print("Ingresar categoria: ");
					categoria = sc.nextLine();
					validarLongtud(categoria);
					valido = true;
				} catch (LongitudInvalidaExcepcion e) {
					System.err.println(e.getMessage());
				}
			}
			
			String marca = "";
			valido = false;
			while (!valido) {
				try {
					System.out.print("Ingresar marca: ");
					marca = sc.nextLine();
					validarLongtud(marca);
					valido = true;
				} catch (LongitudInvalidaExcepcion e) {
					System.err.println(e.getMessage());
				}
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
		} catch (InputMismatchException e) {
			System.err.println("Error: No puede ingresar una cadena de caracteres");
			sc.nextLine();
		}
	}
	
	public static void bajaAutoparte() {
		try {
			negocio.MostrarIdAutopartes();
			System.out.print("Ingresar el id de la autoparte a eliminar: ");
			int id = sc.nextInt();	
			negocio.EliminarAutoparte(id);			
		} catch (InputMismatchException e) {
			System.err.println("Error: No puede ingresar una cadena de caracteres");
			sc.nextLine();
			System.out.println();
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void listarAutoparte() {
		negocio.ListarAutopartes();			
	}
	
	public static void modificarAutoparte() {		
		try {
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
			System.out.println("9- Stock Minimo");
			System.out.println("10- Enlace");
			
			boolean valido = false;
			int tipo = 0;
			while (!valido) {
				try {
					System.out.print("Inidicar que desea modificar: ");
					tipo = sc.nextInt();
					validarOpcion4(tipo);
					valido = true;
				} catch (OpcionInvalidaExcepcion e) {
					System.err.println(e.getMessage());
				}
			}
			
			switch (tipo) {
			case 1:
				valido = false;
				while (!valido) {
					try {
						System.out.print("Indicar nuevo id: ");
						int nuevoId = sc.nextInt();
						negocio.CorroborarExistencia(nuevoId);
						autoparte.setId(nuevoId);
						valido = true;
					} catch (IdExistenteExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				break;
			case 2:
				System.out.print("Ingresar nueva denominacion: ");
				String nuevaDenominacion = sc.next();
				
				autoparte.setDenominacion(nuevaDenominacion);
				break;
			case 3:
				System.out.print("Ingresar descripcion: ");
				String nuevaDescripcion = sc.next();
				
				autoparte.setDescripcion(nuevaDescripcion);
				break;
			case 4:
				valido = false;
				while (!valido) {
					try {
						System.out.print("Ingresar categoria: ");
						String nuevaCategoria = sc.next();
						validarLongtud(nuevaCategoria);
						autoparte.setCategoria(nuevaCategoria);
						valido = true;
					} catch (LongitudInvalidaExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				break;
			case 5:
				valido = false;
				while (!valido) {
					try {
						System.out.print("Ingresar marca: ");
						String nuevaMarca = sc.next();
						validarLongtud(nuevaMarca);
						autoparte.setMarca(nuevaMarca);
						valido = true;
					} catch (LongitudInvalidaExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				break;
			case 6:
				System.out.print("Ingresar vehiculo: ");
				String nuevoVehiculo = sc.next();
				
				autoparte.setVehiculo(nuevoVehiculo);
				break;
			case 7:
				System.out.print("Ingresar modelo: ");
				String nuevoModelo = sc.next();
				
				autoparte.setModelo(nuevoModelo);
				break;
			case 8:
				System.out.print("Ingresar precio: $");
				double nuevoPrecio = sc.nextDouble();
				
				autoparte.setPrecio(nuevoPrecio);
				break;
			case 9:
				System.out.print("Ingresar stock minimo: ");
				int nuevoStockMinimo = sc.nextInt();
				
				autoparte.setStockMinimo(nuevoStockMinimo);
				break;
			case 10:
				System.out.print("Ingresar enlace: ");
				String nuevoEnlace = sc.next();
				
				autoparte.setEnlace(nuevoEnlace);
				break;
			}
			System.out.print("¡El cambio fue aplicado con exito!");
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void modificarStock() {
		try {
			int id = 0;
			int cantidad = 0;
			int modificacion = 0;
			boolean valido = false;
			System.out.println("1- Cargar stock");
			System.out.println("2- ELiminar stock");
			
			while (!valido) {
				try {
					System.out.print("Ingrese la opcion que desee: ");
					modificacion = sc.nextInt();
					validarOpcion2(modificacion);
					valido = true;
				} catch (OpcionInvalidaExcepcion e) {
					System.err.println(e.getMessage());
				}
			}
			
			switch (modificacion) {
			case 1:
				System.out.print("Ingresar el id de la autoparte para cargar stock: ");
				id = sc.nextInt();	
				
				valido = false;
				while (!valido) {
					try {
						System.out.print("Ingresar la cantidad que desea agregar: ");
						cantidad = sc.nextInt();
						validarPositivo(cantidad);
						valido = true;
					} catch (NumeroNegativoExcepcion e) {
						System.err.println(e.getMessage());
					}
				}
				
				negocio.AgregarStock(id,cantidad);
				break;
			case 2:
				System.out.print("Ingresar el id de la autoparte para eliminar stock: ");
				id = sc.nextInt();	
				
				System.out.print("Ingresar la cantidad que desea eliminar: ");
				cantidad = sc.nextInt();
				
				negocio.QuitarStock(id,cantidad);
				break;
			} 
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (AccionImposibleExcepcion e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public static void agregarCliente() {
		try {
			boolean valido = true;
			
			System.out.print("Ingrese id del cliente: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			System.out.print("Ingrese nombre del cliente: ");
			String nombre = sc.nextLine();
			
			System.out.print("Ingrese direccion del cliente: ");
			String direccion = sc.nextLine();
			
			String telefono = "";
			while (!valido) {
				try {
					System.out.print("Ingrese telefono del cliente: ");
					telefono = sc.nextLine();
					contieneGuion(telefono);
					valido = true;
				} catch (CuotasInvalidasExcepecion e) {
					System.err.println(e.getMessage());
				}
			}
			
			String localidad = "";
			valido = false;
			while (!valido) {
				try {
					System.out.print("Ingrese localidad del cliente: ");
					localidad = sc.nextLine();
					validarLongtud(localidad);
					valido = true;
				} catch (LongitudInvalidaExcepcion e) {
					System.err.println(e.getMessage());
				}
			}
			
			System.out.print("Ingrese provincia del cliente: ");
			String provincia = sc.nextLine();
			
			String email = "";
			valido = false;
			while (!valido) {
				try {
					System.out.print("Ingrese email del cliente: ");
					email = sc.nextLine();
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
			
			Cliente cliente = new Cliente(id,nombre,direccion,telefono,localidad,provincia,email);
			negocio.AgregarCliente(cliente);
		} catch (ObjetoExistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void listarClientes() {
		negocio.ListarClientes();
	}
		
	public static void iniciarPedido() {
		try {
			negocio.ObtenerAutopartes();
			boolean valido = false;
			
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingrese id del cliente para cargarle su pedido: ");
			int idcliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idcliente);
			
			System.out.print("Ingrese id del pedido: ");
			int id = sc.nextInt();
			
			String fecha = "";
			while (!valido) {
				try {
					System.out.print("Ingrese fecha de la venta: ");
					fecha = sc.next();
					validarFecha(fecha);
					valido = true;
				} catch (FechaNoValidaExcepcion e) {
					sc.nextLine();
					System.err.println(e.getMessage());
				}
			}
			
			Pedido pedido = new Pedido(id, fecha);
			validarPedido(cliente, id);
			
			System.out.println();
			System.out.println("Autopartes disponibles:");
			listarAutoparte();
			System.out.println();

			System.out.print("¿Cuantas autopartes ordenara (3 como maximo)? ");
			int numAutopartes = sc.nextInt();
			validarPositivo(numAutopartes);
			validarCantidad(numAutopartes);
			validarPosibilidad(numAutopartes);
			
			for (int i = 0; i <= numAutopartes - 1; i++) {
				try {
					System.out.print("Ingrese id de la autoparte: ");
					int idAutoparte = sc.nextInt();
					Autoparte autoparte = negocio.RetornoAutoparte(idAutoparte);
					
					pedido.CargarAutopartePed(autoparte);
					
					System.out.print("Ingrese cantidad de la autoparte: ");
					int cantidad = sc.nextInt();
					pedido.CargarCantidadPed(cantidad);					
				} catch (ObjetoInexistenteExcepcion e) {
					i--;
					System.err.println(e.getMessage());
				}
			}
			
			pedido.CalcularMontoTotal();
			pedido.DisminuirStock();
			cliente.CargarPedido(pedido);
			
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (LongitudInvalidaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ListaVaciaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (AccionImposibleExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ObjetoExistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void cancelarPedido() {
		try {
			negocio.ObtenerClientes();
			
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingrese id del cliente para eliminar el pedido de su lista: ");
			int idCliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idCliente);
			
			System.out.println();
			System.out.println("Pedidos realizados:");
			cliente.ListarPedidos();
			System.out.println();
			
			System.out.print("Ingrese id del pedido para eliminarlo: ");
			int idPedido = sc.nextInt();
			Pedido pedido = cliente.RetornoPedido(idPedido);
			
			pedido.CancelarPedido();
			
			cliente.EliminarPedido(pedido);
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ListaVaciaExcepcion e) {
			System.err.println(e.getMessage());
		}
	}	
	
	public static void listarPedidos() {
		try {
			negocio.ObtenerClientes();
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingresar el id del cliente para listar sus pedidos: ");
			int idCliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idCliente);
			
			cliente.ListarPedidos();			
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ListaVaciaExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void realizarVenta() {
		try {
			negocio.ObtenerAutopartes();
			negocio.ObtenerClientes();
			boolean valido = false;
			
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingrese id del cliente para cargarle su venta: ");
			int idcliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idcliente);
			
			System.out.print("Ingrese id de la venta: ");
			int id = sc.nextInt();
			
			String fecha = "";
			while (!valido) {
				try {
					System.out.print("Ingrese fecha de la venta: ");
					fecha = sc.next();
					validarFecha(fecha);
					valido = true;
				} catch (FechaNoValidaExcepcion e) {
					sc.nextLine();
					System.err.println(e.getMessage());
				}
			}
			
			System.out.println("Opcion 'tc' para tarjeta de credito.");
			System.out.println("Opcion 'td' para tarjeta de debito.");
			System.out.println("Opcion 'ef' para efectivo.");
			System.out.print("Ingrese metodo de pago: ");
			String metodopago = sc.next();
			metodopago = metodopago.toLowerCase();
			validarMetodoPago(metodopago);
			
			Venta venta = null;
			switch (metodopago) {
			case "tc":
				int cuotas = 0;
				valido = false;
				while (!valido) {
					try {
						System.out.print("Ingrese cuotas a pagar: ");
						cuotas = sc.nextInt();
						validarCuotas(cuotas);
						valido = true;
					} catch (CuotasInvalidasExcepecion e) {
						System.err.println(e.getMessage());
					}
				}
				venta = new VentaConCredito(id, fecha, cuotas);							
				break;
			case "td":
				venta = new VentaConDebito(id, fecha);
				break;
			case "ef":
				venta = new VentaConEfectivo(id, fecha);
				break;
			}
			
			System.out.println();
			System.out.println("Autopartes disponibles:");
			listarAutoparte();
			System.out.println();

			System.out.print("¿Cuantas autopartes ordenara (3 como maximo)? ");
			int numAutopartes = sc.nextInt();
			validarPositivo(numAutopartes);
			validarCantidad(numAutopartes);
			validarPosibilidad(numAutopartes);
			
			for (int i = 0; i <= numAutopartes - 1; i++) {
				try {
					System.out.print("Ingrese id de la autoparte: ");
					int idAutoparte = sc.nextInt();
					Autoparte autoparte = negocio.RetornoAutoparte(idAutoparte);
					
					venta.CargarAutopartePed(autoparte);
					
					System.out.print("Ingrese cantidad de la autoparte: ");
					int cantidad = sc.nextInt();
					venta.CargarCantidadPed(cantidad);					
				} catch (ObjetoInexistenteExcepcion e) {
					i--;
					System.err.println(e.getMessage());
				}
			}
			
			venta.CalcularMontoTotal();
			venta.DisminuirStock();
			System.out.println("el monto total de la venta sera: " + venta.CalcularTotal());
			cliente.CargarVenta(venta);
			negocio.CargarVenta(venta);
		} catch (ObjetoExistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (MetodoNoReconocidoExcepcion e) {
			System.err.println(e.getMessage());
		} catch (LongitudInvalidaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ListaVaciaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (AccionImposibleExcepcion e) {
			System.err.println(e.getMessage());
		}
	}	
	
	public static void convertirPedido() {
		try {
			negocio.ObtenerClientes();
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingrese id del cliente para cargarle su venta: ");
			int idcliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idcliente);
			
			System.out.println();
			System.out.println("La lista de pedidos disponibles para pasar a ventas es: ");
			cliente.ListarPedidos();
			System.out.println();
			
			System.out.print("Ingrese id del pedido que pasara a venta: ");
			int id = sc.nextInt();
			
			Pedido pedido = cliente.RetornoPedido(id);
			
			System.out.print("Ingrese id de la venta: ");
			int idVenta = sc.nextInt();
			negocio.CorroborarExistenciaVenta(idVenta);
			pedido.setId(idVenta);
			
			Venta venta = pedido.convertirAVenta();
			cliente.SacarListaPedido(pedido);
			
			cliente.CargarVenta(venta);
			negocio.CargarVenta(venta);
			
			System.out.println("El pedido se ha convertido en una venta y se agregó a la lista del cliente.");
			venta.CalcularMontoTotal();
			System.out.println("el monto total es de: " + venta.CalcularTotal());			
		} catch (ObjetoExistenteExcepcion e) {
			System.err.println(e.getMessage());
		} catch (MetodoNoReconocidoExcepcion e) {
			System.err.println(e.getMessage());
		} catch (LongitudInvalidaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (ListaVaciaExcepcion e) {
			System.err.println(e.getMessage());
		} catch (AccionImposibleExcepcion e) {
			System.err.println(e.getMessage());
		} catch (IdExistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void listarVentasCliente() {
		try {
			negocio.ObtenerClientes();
			
			System.out.println();
			System.out.println("Clientes disponibles:");
			listarClientes();
			System.out.println();
			
			System.out.print("Ingrese id del cliente para mostrar ventas realizadas: ");
			int idcliente = sc.nextInt();
			Cliente cliente = negocio.RetornoCliente(idcliente);
			
			cliente.ListarVentas();			
		} catch (ObjetoInexistenteExcepcion e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static void listarVentasNegocio() {
		negocio.ListarVentas();
	}
	
	public static void validarLongtud(String cadena) {
		if (cadena.length() < 1 || cadena.length() > 50) {
			throw new LongitudInvalidaExcepcion("Error: Debe contener entre 1 y 50 caracteres");
		}
	}
	
	public static void validarLongitudContraseña(String contra) {
		if (contra.length() < 8 || contra.length() > 32) {
			throw new LongitudInvalidaExcepcion("Error: La contraseña debe contener entre 8 y 32 caracteres");
		}
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
				throw new CuotasInvalidasExcepecion("Error: La contraseña no puede tener caracteres especiales");
			}
		}
	}
	
	public static void validarPedido(Cliente cliente, int id) {
		if (cliente.ComprobarPedido(id)) {
			throw new ObjetoExistenteExcepcion("Error: El pedido con ID: " + id + " ya se encuentra cargado.");
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
	
	public static void contieneGuion(String cadena) {
		boolean valido = false;
		char[] caracteres = cadena.toCharArray();
		
		for (int i = 0; i < caracteres.length; i++) {
			char c = caracteres[i];
			if (c == '-' && (i == 2 || i == 3)) {
				valido = true;
			}
		}

		if (!valido) {
			throw new CuotasInvalidasExcepecion("Error: El numero debe contener un guion luego de la caracteristica. Ej: 11-24565234");
		}
	}
	
	public static void validarCantidad(int cantidad) {
		if (cantidad > 3) {
			throw new LongitudInvalidaExcepcion("Error: No puede ordenar mas de 3 autopartes.");
		}
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
	
	public static void validarOpcion3(String letra) {
		if (!letra.equals("a") && !letra.equals("b") && !letra.equals("c") && !letra.equals("d") && !letra.equals("e") && !letra.equals("f")) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. Debe seleccionar 'a', 'b', 'c', 'd', 'e o 'f'.");
		}
	}
	
	public static void validarOpcion4(int numero) {
		if (!(numero == 1) && !(numero == 2) && !(numero == 3) && !(numero == 4) && !(numero == 5) && !(numero == 6) && !(numero == 7) && !(numero == 8) && !(numero == 9) && !(numero == 10)){
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. El numero debe ser 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 u 11.");
		}
	}
	
	public static void validarOpcion5(String letra) {
		if (!letra.equals("a") && !letra.equals("b") && !letra.equals("c")) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. Debe seleccionar 'a', 'b' o 'c'.");
		}
	}

	public static void validarOpcion6(String letra) {
		if (!letra.equals("a") && !letra.equals("b") && !letra.equals("c") && !letra.equals("d")) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. Debe seleccionar 'a', 'b', 'c' o 'd'.");
		}
	}
	
	public static void validarOpcion7(String letra) {
		if (!letra.equals("a") && !letra.equals("b") && !letra.equals("c") && !letra.equals("d") && !letra.equals("e")) {
			throw new OpcionInvalidaExcepcion("Error: Opcion no valida. Debe seleccionar 'a', 'b', 'c', 'd' o 'e'.");
		}
	}
	
	public static void validarPositivo(int numero) {
		if (numero < 0) {
			throw new NumeroNegativoExcepcion("Error: El numero debe ser positivo.");
		}
	}
	
	public static void validarFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(fecha);
		} catch (ParseException e) {
			throw new FechaNoValidaExcepcion("Error: La fecha ingresada no es valida. Ej: dd/mm/aaaa");
		}
	}
	
	public static void validarMetodoPago(String metodo) {
		if (metodo.equals("tc") && metodo.equals("td") && metodo.equals("ef")) {
			throw new MetodoNoReconocidoExcepcion("Error: El metodo de pago es incorrecto. Solo puede pagar mediante 'tc', 'td' o 'ef'");
		}
	}
	
	public static void validarCuotas(int cuotas) {
		if (!(cuotas == 2) && !(cuotas == 3) && !(cuotas == 6)) {
			throw new CuotasInvalidasExcepecion("Error: No puede pagar en " + cuotas + " cuotas. Debe elegir entre 2, 3 o 6 cuotas.");
		}
	}
	
	public static void validarPosibilidad(int cantAutopartes) {
		if (cantAutopartes > negocio.ObtenerCantidadAutopartes()) {
			throw new AccionImposibleExcepcion("Error: No puede solicitar una cantidad de " + cantAutopartes + " autopartes ya que en el sistema solo hay " + negocio.ObtenerCantidadAutopartes());
		}
	}

}