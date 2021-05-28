package Models;

import java.util.ArrayList;

/**
 * Clase que allmacena detalles de serie
 * @author alvar
 */
public class SerieDetails {
    private String titulo;
    private String estado;
    private String image;
    private ArrayList<Tag> tags;
    private String Description;
    private Rel rel;
    private String nextChap;
    private String totalChap;
    private ArrayList<ShortChap> chaps;

    /**
     * Contrusctor
     */
    public SerieDetails(){
        tags = new ArrayList<>();
        chaps = new ArrayList<>();
        estado= "Finalizado";
        nextChap= "--/--/--";
    }

    /**
     * añade un tag a la listaa interna
     * @param tag
     */
    public void addTag(Tag tag){
        tags.add(tag);
    }

    /**
     * añade un parametro a lista de capitulos
     * @param chap
     */
    public void addChap(ShortChap chap){
        this.chaps.add(chap);
    }

    /**
     * Getter de titulo
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Setter de titulo
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Getter de image
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter de image
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter de estado
     * @return estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Setter de estaddo
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Getter de tags
     * @return tags
     */
    public ArrayList<Tag> getTags() {
        return tags;
    }

    /**
     * Setter de taags
     * @param tags
     */
    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Getter de descripción
     * @return description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Setter de descripcion
     * @param description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Getter de relacionado
     * @return rel
     */
    public Rel getRel() {
        return rel;
    }

    /**
     * Setter de relacionado
     * @param rel
     */
    public void setRel(Rel rel) {
        this.rel = rel;
    }

    /**
     * Getter next chap
     * @return nectChap
     */
    public String getNextChap() {
        return nextChap;
    }

    /**
     * setter next chap
     * @param nextChap
     */
    public void setNextChap(String nextChap) {
        this.nextChap = nextChap;
    }

    /**
     * Getter totaal cahp
     * @return totalChap
     */
    public String getTotalChap() {
        return totalChap;
    }

    /**
     * Setter total chap
     * @param totalChap
     */
    public void setTotalChap(String totalChap) {
        this.totalChap = totalChap;
    }

    /**
     * Getter Short chap
     * @return chaps
     */
    public ArrayList<ShortChap > getChaps() {
        return chaps;
    }

    /**
     * Setter chaps
     * @param chaps
     */
    public void setChaps(ArrayList<ShortChap> chaps) {
        this.chaps = chaps;
    }
}
