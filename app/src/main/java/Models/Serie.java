package Models;

import Constantes.Constantes;

/**
 * Clase que alamcena series
 * @author alvar
 */
public class Serie {


    private String nombre;
    private String tipo;
    private String ref;
    private String estado;
    private String imagen;
    private String dia;


    /**
     * Contructor de series
     * @param nombre
     * @param ref
     * @param estado
     * @param imagen
     */
    public Serie(String nombre,String ref,String estado,String imagen,String dia) {
        this.nombre=nombre;

        this.ref=ref;
        this.estado=estado;
        this.imagen=imagen;
        this.dia=dia;
    }
    public Serie(String nombre,String ref,String estado,String imagen,String dia,String tipo) {
        this.nombre=nombre;
        this.tipo=tipo;
        this.ref=ref;
        this.estado=estado;
        this.imagen=imagen;
        this.dia=dia;
    }

    /**
     * Obtiene imagen
     * @return imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Setter imagen
     * @param imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene estado
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Setter estado
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene referencia
     * @return ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * Setter referencia
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Obtiene nombre
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setea nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene tipo
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Setter tipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * To string para crear cuerpo del correo
     * @return lineaCorreo
     */
    public String toString() {
        return tipo+": "+nombre+"\n"+"\n"+ref+"\n"+"\n";
    }

    /**
     * Getter de dia
     * @return dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Setter de dia
     * @param dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }
}
