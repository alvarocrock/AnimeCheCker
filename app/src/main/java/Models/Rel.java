package Models;

/**
 * Clase que almacena relacionados
 * @author alvar
 */
public class Rel {

    private String name;

    /**
     * Constructor
     * @param name
     */
    public Rel(String name) {
        this.name = name;

    }

    /**
     * Getter de nombre
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
    Setter de nombre
     @param name
     */
    public void setName(String name) {
        this.name = name;
    }


}
