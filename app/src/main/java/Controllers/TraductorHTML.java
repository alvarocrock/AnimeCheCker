package Controllers;

import java.util.ArrayList;

import Models.Capitulo;
import Models.ChapDetails;
import Models.Rel;
import Models.Serie;
import Models.SerieDetails;
import Models.ShortChap;
import Models.Tag;

/**
 * Clase que traduce el html a objetos
 * @author alvar
 */
public class TraductorHTML {

    public TraductorHTML() {

    }

    /**
     * Obtiene array list de series
     * @param lista
     * @return series
     */
    public ArrayList<Serie> getseries(ArrayList<String> lista) {
        ArrayList<Serie> series = new ArrayList();

        boolean image = false;
        String[] list;
        String[] list2;
        String img = null;
        String dia = null;
        String ref = null;
        String estado = null;
        String nombre = null;
        for (String s : lista) {
            if (s.contains("</h2>")){
                s= s.replace("<",";");
                s= s.replace(">",";");
                list= s.split(";");
                if (list.length==4){
                    dia=list[2];
                }

            } else if (s.contains("<a href=")){
                list=s.split("\"");
                if (list.length==3){
                    ref= list[1];
                }
            }else if (s.contains("<h3>")){
                s= s.replace("<",";");
                s= s.replace(">",";");
                list= s.split(";");
                if (list.length==4){
                    nombre=list[2];
                }

            } else if (s.contains("<img src=")){
                list=s.split("\"");
                if (list.length==3){
                    img= list[1];
                }
            }

            if (ref!=null && nombre!=null && img!=null){
                series.add(new Serie(nombre,ref,"Emisión",img,dia));
                ref=null;
                nombre=null;
                img=null;
            }

        }

        return series;

    }

    /**
     * Obtine array list de capitulos
     * @param lista
     * @return capitulos
     */
    public ArrayList<Capitulo> getcap(ArrayList<String> lista) {
        ArrayList<Capitulo> capitulos = new ArrayList();

        String nombre = null;
        String capitulo = null;
        String referencia = null;
        String imagen = null;
        String[] list;

        for (String s : lista) {

            if(s.contains("<a href=")){
                list = s.split("\"");
                if (list.length==7) {
                    referencia = list[1];
                }

            } else if (s.contains("<img src=")){
                list = s.split("\"");
                if (list.length==7) {
                    imagen = list[1];
                }

            }else if (s.contains("<h5>")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                list = s.split("ç");
                if (list.length==4){
                    nombre = list[2];
                    if (nombre.contains("&#039;")){
                        nombre= nombre.replace("&#039;","'");
                    }
                }
            }else if (s.contains("</h6>")){
                s= s.replace("<",";");
                s= s.replace(">",";");
                list = s.split(";");
                if (list.length==2){
                    capitulo = list[0];
                    capitulo = capitulo.replace(" ","");
                }

            }


            if (nombre != null && referencia != null && imagen != null && capitulo!=null) {
                capitulos.add(new Capitulo(capitulo,nombre, referencia, imagen));
                capitulo= null;
                referencia = null;
                nombre = null;
                imagen = null;
            }
        }
        return capitulos;

    }

    /**
     * Obtiene detalles de capitulos
     * @param lista
     * @return detils
     */
    public ChapDetails getChapDetails(ArrayList<String> lista) {
        ChapDetails details = new ChapDetails();
        String[] list;
        for (String s : lista) {

            if (s.contains("um.")){
                list= s.split("\"");
                details.setVideoum(list[3]);
            } else if (s.contains("mega.nz") && s.contains("video[")){
                list= s.split("\"");
                details.setVideomega(list[1]);
            }  else if (s.contains("jk.")){
                list= s.split("\"");
                details.setVideojk(list[3]);
            } else if (s.contains("jkokru.")){
                list= s.split("\"");
                details.setVideookru(list[3]);
            }
        }
        return details;
    }

    /**
     * Devuelve detalles de una serie
     * @param list
     * @return details
     */
    public SerieDetails getdetails(ArrayList<String> list) {
        SerieDetails details = new SerieDetails();

        String[] lista;
        String url= null;
        String ref = null;
        String name = null;
        String img = null;
        boolean rel = false;
        for (String s : list) {
            if (s.contains("<div class=\"anime__details__pic set-bg\"")){
                lista= s.split("\"");
                if (lista.length==7) {
                    details.setImage(lista[3]);
                }

            } else if (s.contains("<h3>") && details.getTitulo()==null) {
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==4) {
                    details.setTitulo(lista[2]);
                    if (details.getTitulo().contains("&#039;")){
                        details.setTitulo(details.getTitulo().replace("&#039;","'"));
                    }
                }
            } else if ((s.contains("</p>") || s.contains("<p>")) && details.getDescription()==null){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (s.contains("ç/pç")){
                    if (lista.length == 4) {
                        details.setDescription(lista[2]);
                    } else if (lista.length == 2) {
                        details.setDescription(lista[0]);
                    }
                } else {
                    if (lista.length == 2) {
                        details.setDescription(lista[0]);
                    }
                }
            } else if (s.contains("<li><span>Genero:")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==10) {
                    details.addTag(new Tag(lista[8]));
                }
            } else if (s.contains("<a href=\"https://jkanime.net/genero")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==4) {
                    details.addTag(new Tag(lista[2]));
                }
            }else if (s.contains("<li><span>Estado:</span> <span class=\"enemision currently\">En emision</span></li>")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==12) {
                    details.setEstado(lista[8]);
                }
            } else if (s.contains("<div id=\"proxep\">")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==16) {
                    details.setNextChap(lista[8]);
                }
            } else if (s.contains("<a class=\"numbers\"")){
                s= s.replace("<","ç");
                s= s.replace(">","ç");
                lista= s.split("ç");
                if (lista.length==4) {
                    String[] lista1= lista[2].split("-");
                    details.setTotalChap(lista1[1]);
                    details.setTotalChap(details.getTotalChap().replace(" ",""));

                }
            } else if (s.contains("url]")){
                lista = s.split("]");
                url= lista[1];

            }


        }

        //Rellenamos lista de ShortChap
        for (int cont=0;cont<Integer.parseInt(details.getTotalChap());cont++){
            int ep= cont+1;
            name= "Episodio "+ep;
            ref= url+ep+"/";
            if (ref!=null && name!=null){
                details.addChap(new ShortChap(name,ref));
                ref=null;
                name=null;
            }
        }
        return details;
    }
}