package Controllers;

import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Constantes.Constantes;

/**
 * Clase que obtiene y clasifica el html de la pagina web
 *
 * @author alvar
 */
public class ControllerHTML extends AsyncTask<Void, String, Void>  {

    private HttpURLConnection conexion;
    private ArrayList<String> series;
    private ArrayList<String> caps;
    private ArrayList<String> seriDetaails;
    private ArrayList<String> charDetails;
    boolean fin;
    private int code;



    private String url;

    private String option;

    /**
     * Constructor, si de le dala opción "main", obtiene lista de sreies y capitulos, <br/>
     *
     * @param url
     * @param option
     */
    public ControllerHTML(String url, String option) {
        conexion = null;
        this.url = url;
        this.option = option;
        charDetails = new ArrayList<>();
        series = new ArrayList<>();
        caps = new ArrayList<>();
        seriDetaails = new ArrayList<>();
        fin = false;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        //conectar();

        if (option.equals("main")) {

            caps = getcapitulos();
            series = getseries();

        }
        if (option.equals("contDetails")) {
            seriDetaails = getSeriDetails();
        }
        if (option.equals("chatDetails")) {
            charDetails = getCharDetails();
        }
        fin = true;
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        conexion.disconnect();
        super.onPostExecute(aVoid);

    }

    

    /**
     * Comportamineto para obtener la conexion del htm, con la url del constructor
     * @return conexion
     */
    public void conectar() {

        try {
            URL link = new URL(url);
            conexion = (HttpURLConnection) link.openConnection();
            //conexion.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            conexion.setDoOutput(true);
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            code = conexion.getResponseCode(); //si es 200 funciona correctamente si es 403, da fallo
            System.out.println(url+" "+ code);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Comportamineto para obtener la conexion del html, para la url de emisión
     * @return conexion
     */
    public void conectar2() {

        try {
            URL link = new URL(Constantes.urlEmi);
            conexion = (HttpURLConnection) link.openConnection();
            //conexion.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            conexion.setDoOutput(true);
            conexion.setRequestMethod("GET");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
            code = conexion.getResponseCode(); //si es 200 funciona correctamente si es 403, da fallo
            System.out.println(Constantes.urlEmi+" "+ code);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * Comportamineto para obtener una ArrayList con las series
     * @return lista
     */
    protected ArrayList<String> getseries() {

        ArrayList<String> series = new ArrayList();
        BufferedReader br = null;

            try {
                conectar2();
                InputStream hola = conexion.getInputStream();
                InputStreamReader ireader = new InputStreamReader(conexion.getInputStream());
                br = new BufferedReader(ireader);
                String linea = "";
                Boolean escribiendo = false;
                String prueba = br.readLine();
                while ((linea = br.readLine()) != null) {

                    if (linea.contains("<div id=\"tabla\">")){
                        escribiendo=true;
                    }
                    if (escribiendo==true){
                        series.add(linea);
                    }
                    if (linea.contains("<center></center><div id=\"disqus_thread\"></div>") && escribiendo==true){
                        escribiendo=false;
                    }




                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }




        return series;
    }


    /**
     * Comportaamineto para obtener una ArrayList con los capitulos
     * @return lista
     */
    protected ArrayList<String> getcapitulos() {
        conectar();
        ArrayList<String> series = new ArrayList();

        BufferedReader br = null;
        try {
            InputStream hola = conexion.getInputStream();
            InputStreamReader ireader = new InputStreamReader(conexion.getInputStream());
            br = new BufferedReader(ireader);
            String linea = "";
            Boolean escribiendo = false;
            String prueba = br.readLine();
            while ((linea = br.readLine()) != null) {
                if (linea.contains("<div class=\"maximoaltura\">")){
                    escribiendo=true;
                }
                if (escribiendo==true){
                    series.add(linea);
                }
                if (linea.contains("</section>") && escribiendo==true){
                    escribiendo=false;
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                //e.printStackTrace();
            }
        }


        return series;
    }

    /**
     * Obtiene detalles de capituloos
     * @return lista
     */
    protected ArrayList<String> getCharDetails() {
        conectar();
        ArrayList<String> series = new ArrayList();

        BufferedReader br = null;
        try {
            InputStream hola = conexion.getInputStream();
            InputStreamReader ireader = new InputStreamReader(conexion.getInputStream());
            br = new BufferedReader(ireader);
            //reader = new BufferedReader(input);
            String linea = "";
            Boolean escribiendo = false;
            String prueba = br.readLine();
            while ((linea = br.readLine()) != null) {
                if (linea.contains("um.")){
                    series.add(linea);
                } else if (linea.contains("mega.nz")){
                    series.add(linea);
                }  else if (linea.contains("jk.")){
                    series.add(linea);
                } else if (linea.contains("jkokru.")){
                    series.add(linea);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                //e.printStackTrace();
            }
        }

        return series;

    }

    /**
     * Comportamiento para obtener html que se traducira a detalles de serie
     * @return lista
     */
    protected ArrayList<String> getSeriDetails() {
        ArrayList<String> lista = new ArrayList<>();
        conectar();
        boolean image = false;
        boolean tag = false;
        boolean titulo = false;
        boolean rel = false;
        BufferedReader br = null;
        try {
            InputStream hola = conexion.getInputStream();
            InputStreamReader ireader = new InputStreamReader(conexion.getInputStream());
            br = new BufferedReader(ireader);
            String linea = "";
            //String prueba=br.readLine();
            while ((linea = br.readLine()) != null) {
                if (linea.contains("<div class=\"anime__details__pic set-bg\"")){
                    lista.add(linea);
                    image=true;
                } else if (image==true){
                    lista.add(linea);
                } else if (linea.contains("</div>") && image==true){
                    lista.add(linea);
                    image=false;
                } else if (linea.contains("<div class=\"anime__details__title\">")){
                    lista.add(linea);
                    titulo=true;
                } else if (titulo==true){
                    lista.add(linea);
                } else if (linea.contains("<div class=\"anime__details__widget\">") && titulo==true){
                    //lista.add(linea);
                    titulo=false;
                }/* else if (linea.contains("<li><span>Genero:")){
                    lista.add(linea);
                    tag=true;
                } else if (tag==true){
                    lista.add(linea);
                } else if (linea.contains("</ul>") && tag==true){
                    tag=false;
                    lista.add(linea);
                    */
                 else if (linea.contains("<div class=\"col-lg-6 col-md-6\">")){
                    lista.add(linea);
                    rel=true;
                } else if (rel==true){
                    lista.add(linea);
                }else if (linea.contains("</div>") && rel==true){
                    lista.add(linea);
                    rel=false;
                }else if (linea.contains("class=\"numbers\" ")){
                    lista.add(linea);
                } else if (linea.contains("var cap_url")){
                    lista.add(linea);
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println(e);
                //e.printStackTrace();
            }
        }
        lista.add("url]"+url);
        return lista;
    }

    /**
     * Obtiene detalles de serie
     * @return seriDetails
     */
    public ArrayList<String> getSeriDetails_list() {
        return seriDetaails;
    }

    /**
     * Obtiene lista de capitulos
     * @return caps
     */
    public ArrayList<String> getCaps_list() {
        return caps;
    }

    /**
     * Obtiene lista de series
     * @return series
     */
    public ArrayList<String> getSeries_list() {
        return series;
    }

    /**
     * Obtiene detalles de capitulo
     * @return charDetails
     */
    public ArrayList<String> getCharDetails_list() {
        return charDetails;
    }
    
    /**
     * Setter del valor fin a true o false
     * @return fin
     */
    public boolean isFin() {
        return fin;
    }

    /**
     * Getter estado del proceso
     * @param fin
     */
    public void setFin(boolean fin) {
        this.fin = fin;
    }

    /**
     * Obtiene el código de repsuesta
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Setter del código de respuesta
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }
}
