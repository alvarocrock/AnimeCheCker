package Models;

/**
 * Clase que almacena los tags
 * @author alvar
 */
public class Tag {
    private String name;

    /**
     * Constructor
     * @param name
     */
    public Tag(String name) {

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
     * Setter de nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
