package Models;

/**
 * Clase que almacena detalles de capitulo
 * @author alvar
 */
public class ChapDetails {
    private String capitulo;
    private String videomega;
    private String videoum;
    private String videookru;
    private String videojk;

    /**
     * Constructor
     */
    public ChapDetails(){

    }

    /**
     * Obitiene capitulo
     * @return
     */
    public String getCapitulo() {
        return capitulo;
    }

    /**
     * Setter de capitulo
     * @param capitulo
     */
    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    /**
     * Obtiene video de mega
     * @return videomega
     */
    public String getVideomega() {
        return videomega;
    }

    /**
     * Setter video mega
     * @param videomega
     */
    public void setVideomega(String videomega) {
        this.videomega = videomega;
    }

    /**
     * Obiene video um
     * @return videoum
     */
    public String getVideoum() {
        return videoum;
    }

    /**
     * Setter video um
     * @param videoum
     */
    public void setVideoum(String videoum) {
        this.videoum = videoum;
    }

    /**
     * Obtiene video ok.ru
     * @return videookru
     */
    public String getVideookru() {
        return videookru;
    }

    /**
     * Setter video okru
     * @param videookru
     */
    public void setVideookru(String videookru) {
        this.videookru = videookru;
    }

    /**
     * Obtiene video de jk
     * @return videojk
     */
    public String getVideojk() {
        return videojk;
    }

    /**
     * Setter video jk
     * @param videojk
     */
    public void setVideojk(String videojk) {
        this.videojk = videojk;
    }
}
