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

	/**
	 * Getter de nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter de nombre
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de correo
	 * @return
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Setter de correo
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * Getter de opcion
	 * @return opcion
	 */
	public boolean isOpcion() {
		return opcion;
	}

	/**
	 * Setter de opcion
	 * @param opcion
	 */
	public void setOpcion(boolean opcion) {
		this.opcion = opcion;
	}
}
