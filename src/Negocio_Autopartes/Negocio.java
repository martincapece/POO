package Negocio_Autopartes;

import java.util.ArrayList;
import Excepciones.*;

public class Negocio {
	ArrayList<Autoparte> autopartes;
	ArrayList<Cliente> clientes;
	ArrayList<Venta> ventas;
	ArrayList<Usuario> usuarios;
	
	public Negocio() {
		autopartes = new ArrayList<Autoparte>();
		clientes = new ArrayList<Cliente>();
		ventas = new ArrayList<Venta>();
		usuarios = new ArrayList<Usuario>();
	}
	
	public Autoparte RetornoAutoparte(int id) {
		for(Autoparte autoparte: autopartes) {
			if(autoparte.getId()==id) {
				return autoparte;
			}
		}
		throw new ObjetoInexistenteExcepcion("Error: No se encontro ninguna autoparte con el ID: " + id);
	}
	
	public Cliente RetornoCliente(int id) {
		for(Cliente cliente: clientes) {
			if(cliente.getId() == id) {
				return cliente;
			}
		}
		throw new ObjetoInexistenteExcepcion("Error: No se encontro ningun cliente con el ID: " + id);
	}
	
	public Usuario RetornoUsuario(int id) {
		for(Usuario usuario : usuarios) {
			if(usuario.getId() == id) {
				return usuario;
			}
		}
		throw new UsuarioNoEncontradoExcepcion("Error: No se encontro ningun usuario con el ID: " + id);
	}
	
	public void CargarUsuario(Usuario usuario) {
		boolean existe = this.VerificarUsuario(usuario.getId());
		
		if (existe) {
			throw new ObjetoExistenteExcepcion("Error: El usuario ya existe en el sistema.");
		}
		
		usuarios.add(usuario);
		if (usuario.getId() != 1) {
			System.out.println("¡El usuario fue cargado en el sistema!");
		}
	}
	
	public void CargarCliente(Cliente cliente) {
		clientes.add(cliente);
		System.out.println("Se agrego al cliente a la lista");
	}
	
	public void CargarAutoparte(Autoparte autoparte) {
		boolean existe = this.VerificarAutoparte(autoparte.getId());
		
		if (!existe) {
			autopartes.add(autoparte);
			System.out.println("¡La autoparte fue cargada con exito!");
		} else {
			throw new ObjetoExistenteExcepcion("Error: La autoparte ya existe en el sistema.");
		}
	}
	
	public void MostrarIdAutopartes() {
		if (autopartes.isEmpty()) {
			throw new ListaVaciaExcepcion("Error: Actualmente no hay autopartes cargadas.");
		} else {
			System.out.println("Listado de IDs disponibles a eliminar");
			for (Autoparte autoparte : autopartes) {
				System.out.println("ID: " + autoparte.getId() +", Modelo: " + autoparte.getModelo());	
			}			
		}
	}
	
	public void EliminarAutoparte(int id) {
	    boolean existe = this.VerificarAutoparte(id);
	    
	    if (!existe) {
	        throw new ObjetoInexistenteExcepcion("Error: La autoparte con ID: " + id + " no existe.");
	    } else {
	        Autoparte elegida = null;
	        for (Autoparte autoparte : autopartes) {
	            if (autoparte.getId() == id) {
	                elegida = autoparte;
	                break; // Salimos del bucle una vez que encontramos la autoparte
	            }
	        }
	        if (elegida != null) {
	            autopartes.remove(elegida);
	            System.out.println("¡La autoparte fue eliminada con éxito!");
	        }
	    }
	}
	
	public void ListarAutopartes() {
        if (autopartes.isEmpty()) {
            throw new ListaVaciaExcepcion("Error: Actualmente no hay autopartes cargadas.");
        } else {
            for (Autoparte autoparte : autopartes) {
            	System.out.println("ID: " + autoparte.getId() +", Modelo: " + autoparte.getModelo()+ ", Precio: " + autoparte.getPrecio() +" Cantidad de Stock: " +autoparte.getStock());
                if (autoparte.getStock() < autoparte.getStockMinimo()) {
                	System.out.println("EL STOCK ESTA POR DEBAJO DEL MINIMO. REPONER STOCK");
                }
            }
        }
    }
	
	public void ListarClientes() {
        if (clientes.isEmpty()) {
            throw new ListaVaciaExcepcion("Error: Actualmente no hay clientes en el sistema.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getId() + ", Nombre: " + cliente.getNombre() + ", e-mail: " + cliente.getEmail());
            }
        }
    }
	
	public void AgregarStock(int id, int cantidad) {
		Autoparte autoparte = this.RetornoAutoparte(id);
		autoparte.sumarStock(cantidad);
	}
	
	public void QuitarStock(int id, int cantidad) {
		Autoparte autoparte = this.RetornoAutoparte(id);
		
		boolean posible = this.VerificarStock(autoparte, cantidad);
		
		if (posible) {
			autoparte.RestarStock(cantidad);
			System.out.println("¡El stock fue decrementado correctamente!");
		} else {
			throw new AccionImposibleExcepcion("Error: No puede quitar esa cantidad de stock. Stock actual: " + autoparte.getStock());
		}
		
	}
	
	public void AgregarCliente(Cliente nuevoCliente) {
		boolean existe = this.VerificarCliente(nuevoCliente.getId());
		
		if (!existe) {
			clientes.add(nuevoCliente);
			System.out.println("¡El cliente fue cargado correctamente!");
		} else {
			throw new ObjetoExistenteExcepcion("Error: El cliente ya se encuentra cargado en el sistema");
		}
	}
	
	// Estan en amarillo porque todavia no los usamos
	private boolean VerificarStock(Autoparte autoparte, int stock) {
		boolean posible = true;
		
		int stockActual = autoparte.getStock();
		
		if ((stockActual - stock) >= autoparte.getStockMinimo()) {
			return posible;
		} else {
			return !posible;			
		}
	}
	
	private boolean VerificarAutoparte(int id) {
		boolean existe = true;
		
		for (Autoparte autoparte : autopartes) {
			if (autoparte.getId() == id) {
				return existe;
			}
		}
		
		return !existe;
	}
	
	private boolean VerificarCliente(int id) {
		boolean existe = true;
		
		for (Cliente cliente : clientes) {
			if (cliente.getId() == id) {
				return existe;
			}
		}
		
		return !existe;
	}
	
	private boolean VerificarUsuario(int id) {
		boolean existe = true;
		
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				return existe;
			}
		}
		
		return !existe;
	}
	
	public void CorroborarExistencia(int id) {
		if (this.VerificarAutoparte(id)) {
			throw new IdExistenteExcepcion("Error: El id proporcionado ya existe en el sistema.");
		}
	}
}
