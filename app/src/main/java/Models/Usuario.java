package Models;

/**
 * clase que almacena los modelos usuarios
 * @author alvar
 *
 */
public class Usuario {
	
	private String nombre;
	private String correo;
	private boolean opcion;

	
	public Usuario(String nombre,String correo, boolean opcion) {
		this.nombre=nombre;
		this.correo=correo;
		this.opcion= opcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isOpcion() {
		return opcion;
	}

	public void setOpcion(boolean opcion) {
		this.opcion = opcion;
	}
}
