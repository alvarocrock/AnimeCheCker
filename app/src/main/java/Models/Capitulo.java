package Models;


/**
 * Clase modelo de capitulos
 * @author alvar
 */
public class Capitulo {


	private String capitulo;
	private String imagen;
	private String serie;
	private String ref;

	/**
	 * Constructor sin imagen
	 * @param capitulo
	 * @param serie
	 * @param ref
	 */
	public Capitulo(String capitulo,String serie,String ref) {
		this.capitulo=capitulo;
		this.serie=serie;
		this.ref=ref;
	}

	/**
	 * Constructor con imagen
	 * @param capitulo
	 * @param serie
	 * @param ref
	 * @param imagen
	 */
	public Capitulo(String capitulo,String serie,String ref,String imagen) {
		this.imagen=imagen;
		this.capitulo=capitulo;
		this.serie=serie;
		this.ref=ref;
	}

	/**
	 * Obtiene la imagen
	 * @return imagen
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Setter de imagen
	 * @param imagen
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	/**
	 * Obtiene la referencia
	 * @return ref
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * Setter la referencia
	 * @param ref
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * Obtiene capitulo
	 * @return capitulo
	 */
	public String getCapitulo() {
		return capitulo;
	}

	/**
	 * Setter capitulo
	 * @param capitulo
	 */
	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}

	/**
	 * Obtiene serie
	 * @return serie
	 */
	public String getSerie() {
		return serie;
	}

	/**
	 * Setter serie
	 * @param serie
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}

	/**
	 * Obtiene string para crear cuerpor del correo
	 * @return  "- "+serie+" --> "+capitulo+"\n"+ref+"\n"+"\n";
	 */
	public String toString() {
		return "- "+serie+" --> "+capitulo+"\n"+ref+"\n"+"\n";
	}
}
