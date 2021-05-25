package Models;

import java.util.ArrayList;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Rel getRel() {
        return rel;
    }

    public void setRel(Rel rel) {
        this.rel = rel;
    }

    public String getNextChap() {
        return nextChap;
    }

    public void setNextChap(String nextChap) {
        this.nextChap = nextChap;
    }

    public String getTotalChap() {
        return totalChap;
    }

    public void setTotalChap(String totalChap) {
        this.totalChap = totalChap;
    }

    public ArrayList<ShortChap > getChaps() {
        return chaps;
    }

    public void setChaps(ArrayList<ShortChap> chaps) {
        this.chaps = chaps;
    }
}
