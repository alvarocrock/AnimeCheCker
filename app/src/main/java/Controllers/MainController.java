package Controllers;

import com.example.comprobadoranimes.MainActivity;

import java.util.ArrayList;


import Constantes.Constantes;
import Models.Capitulo;
import Models.ChapDetails;
import Models.Serie;
import Models.SerieDetails;
import Models.Usuario;


/**
 * Controlador que va a llevar el flujo del proceso
 *
 * @author alvar
 */
public class MainController  {

    private static MainController singleton;
    private ControllerPersistencia persi;
    private ControllerFichero file;
    private ControllerHTML obtener;
    private TraductorHTML traductor;
    private ControllerComparador comparador;
    private ControladorCorreos correo;
    private ArrayList<Capitulo> caps_actuales;
    private ArrayList<Serie> series_actuales;
    private ArrayList<Capitulo> caps_nuevos;
    private ArrayList<Serie> series_nuevas;
    private ArrayList<Serie> series_guardadas;
    private ArrayList<Capitulo> caps_guardados;
    private ChapDetails chapDetails;
    private SerieDetails details;
    private int reponse;

 

    /**
     * Constructor del controlador
     * @param actividad
     */
    private MainController(MainActivity actividad) {
        persi = new ControllerPersistencia(actividad);
        file = new ControllerFichero(actividad);
        traductor = new TraductorHTML();
        chapDetails = new ChapDetails();
        comparador = new ControllerComparador();
        //correo= new ControladorCorreos();
        caps_actuales = new ArrayList();
        series_actuales = new ArrayList();
        caps_nuevos = new ArrayList<>();
        series_nuevas = new ArrayList<>();
        series_guardadas = new ArrayList<>();
        caps_guardados = new ArrayList<>();
        singleton = null;

    }

    /**
     * Obtiene el singleton, si no existe lo crea
     * @param myApp
     * @return singleton
     */
    public static MainController getSingleton(MainActivity myApp) {
        if (singleton == null) {
            singleton = new MainController(myApp);
        }
        return singleton;

    }
    /**
     * Obtiene el singleton
     * @return singleton
     */
    public static MainController getSingleton() {

        return singleton;

    }

    /**
     * Inicializa las variables
     */
    public void inicializar() {
        traductor = new TraductorHTML();
        comparador = new ControllerComparador();
        //correo= new ControladorCorreos();
        caps_actuales = new ArrayList();
        series_actuales = new ArrayList();
        caps_nuevos = new ArrayList<>();
        series_nuevas = new ArrayList<>();
        series_guardadas = new ArrayList<>();
        caps_guardados = new ArrayList<>();
    }

    /**
     * comportamiento que crea las listas
     */
    public void crearlistas(String URL, String option) {
        //bug al notificar por pantalla si hay cambios o no
        inicializar();
        obtener = new ControllerHTML(URL, option);

        boolean acabado = false;
        obtener.execute();


        while (obtener.isFin() == false) {
            acabado = true;
        }

        reponse= obtener.getCode();
        if (acabado = true) {
            if (reponse>=200 && reponse<300) {
                ArrayList<String> caps = obtener.getCaps_list();
                ArrayList<String> seri = obtener.getSeries_list();
                if (option.equals("main")) {
                    caps_actuales = traductor.getcap(caps);
                    series_actuales = traductor.getseries(seri);
                }
                if (option.equals("contDetails")) {
                    details = traductor.getdetails(obtener.getSeriDetails_list());
                }
                if (option.equals("chatDetails")) {
                    chapDetails = traductor.getChapDetails(obtener.getCharDetails_list());
                }


                caps_nuevos = comparador.comparacap(persi.obtenercaps(), caps_actuales);
                series_nuevas = comparador.comparaserie(persi.obtenerseries(), series_actuales);
                obtener.setFin(false);
                CrearListasGuardadas();
            }

            //si se ponen a null al pusar el boton por segunda vez, no peta
            obtener = null;
            correo = null;

        }
    }

    /**
     * Crea listas de datos guardados
     */
    public void CrearListasGuardadas() {
        series_guardadas = persi.obtenerseries();
        caps_guardados = persi.obtenercaps();

    }

    /**
     * Comprueba todo los cambios, sii hay cambios envia correo
     * @param nombre
     * @param correo
     * @param send
     */
    public void comprobartodo(String nombre, String correo, Boolean send) {
    	if (!nombre.equals("") && !correo.equals("")) {
    		file.GuardarUser(new Usuario(nombre, correo,send));
    	}
        crearlistas(Constantes.url, "main");
        String cuerpo = crearcuerpo(nombre);
        String asunto = "";
        if (send == true) {
            if (series_nuevas.size() > 0 || caps_nuevos.size() > 0) {
                ControladorCorreos mail = null;
                if (series_nuevas.size() > 0 && caps_nuevos.size() > 0) {
                    int contenido = series_nuevas.size() + caps_nuevos.size();
                    asunto = "There are " + contenido + " new stuff";
                } else if (series_nuevas.size() > 0) {
                    asunto = "there are " + series_nuevas + " new content";
                } else if (caps_nuevos.size() > 0) {
                    asunto = "there are " + caps_nuevos.size() + " chapters";
                }
                mail = new ControladorCorreos(correo, asunto, cuerpo);
                mail.execute();
            }
        }
        persi.guardarcaps(caps_actuales);
        persi.guardarseries(series_actuales);
        //test();


    }

    /**
     * Crea el cuerpo del mensaje
     * @param nombre
     * @return cuerpo
     */
    public String crearcuerpo(String nombre) {
        String cuerpo = "buenas " + nombre + "\n";
        if (series_nuevas.size() > 0) {
            cuerpo = cuerpo + "series" + "\n";
            for (Serie serie : series_nuevas) {
                cuerpo = cuerpo + serie.toString();
            }
        }

        if (series_actuales.size() > 0) {
            cuerpo = cuerpo + "capitulos" + "\n";
            for (Capitulo cap : caps_nuevos) {
                cuerpo = cuerpo + cap.toString();
            }
        }

        ArrayList<Serie> finalizadas = new ArrayList<>();
        for (Serie s : series_actuales) {
            if (s.getDia().contains("F")) {
                finalizadas.add(s);
            }
        }

        if (finalizadas.size() > 0) {
            cuerpo = cuerpo + "Series finalizadas" + "\n";
            for (Serie c : finalizadas) {
                cuerpo = cuerpo + c.getNombre() + "\n";
            }
        }


        return cuerpo;
    }

    /**
     * Obtiene el usuario guardado
     *
     * @return user
     */
    public Usuario Getuser() {
        return file.getdestinatario();
    }


    /**
     * Guarda datos del usuario como persistencia
     *
     * @param user
     */
    public void SafeUser(Usuario user) {
        file.GuardarUser(user);
    }

    /**
     * Obtiene lista de capitulos catuales
     * @return caps_actuales
     */
     
    public ArrayList<Capitulo> getcapsactuales() {
        return caps_actuales;
    }

    /**
     * Obtiene lista de series catuales
     * @return series_actuales
     */
    public ArrayList<Serie> getSeries_actuales() {
        return series_actuales;
    }

    /**
     * retorna la longitud de la lista de capitulos nuevos
     *
     * @return
     */
    public int getcapsize() {
        return caps_nuevos.size();
    }

    /**
     * retona la longitud de las series nuevas
     *
     * @return
     */
    public int getseriesize() {
        return series_nuevas.size();
    }

    /**
     * Obtiene detalles de series
     * @return details
     */
    public SerieDetails getDetails() {
        return details;
    }

    /**
     * Obtiene detalles de capitulo
     * @return
     */
    public ChapDetails getChapDetails() {
        return chapDetails;
    }
    
    /**
     * Retorna series guardadas
     * @return series_guardadas
     */
    public ArrayList<Serie> getSeries_guardadas() {
        return series_guardadas;
    }

    /**
     * Retorna capitulos guardadas
     * @return caps_guardadas
     */
    public ArrayList<Capitulo> getCaps_guardados() {
        return caps_guardados;
    }

    /**
     * obtiene codigo de respuesta al conectar
     * @return reponse
     */
    public int getReponse() {
        return reponse;
    }

    /**
     * setea codigo de respuesta
     * @param reponse
     */
    public void setReponse(int reponse) {
        this.reponse = reponse;
    }
}
