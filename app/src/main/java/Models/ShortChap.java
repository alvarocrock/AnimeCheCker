package Models;


/**
 * Clase que almacena sereis con menos detalles
 * @author alvar
 */
public class ShortChap {

    private String name;
    private String ref;

    /**
     * Constructor
     * @param name
     * @param ref
     */
    public ShortChap(String name,String ref) {
        this.name = name;
        this.ref = ref;
    }

    /**
     * Getter de referencia
     * @return ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * Setter de referencia
     * @param ref
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Getter de nombre
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter de nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
