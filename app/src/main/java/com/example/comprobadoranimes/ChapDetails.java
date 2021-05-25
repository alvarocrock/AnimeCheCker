package com.example.comprobadoranimes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import Constantes.Constantes;
import Controllers.MainController;

public class ChapDetails extends AppCompatActivity {

    private MainController contro;
    private Models.ChapDetails details;
    //private TextView title;
    private WebView video;
    private Button Bum;
    private Button BMega;
    private Button BJK;
    private Button Bokru;


    /**
     * inicializa elementos de la vista y asigna valores
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap_details); //define archivo xml de layout que se utilizará
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //define orientación
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //elemina barra de estaado (wifi, cobertura, fecha, hora etc...)
        video = findViewById(R.id.Video); //inicialza video viewer
        contro = MainController.getSingleton(); // Obtiene singleton del controlador
        BMega = findViewById(R.id.botonmega); //define biton de opciñon de mega
        BJK = findViewById(R.id.botonjk); //define boton jk
        Bokru = findViewById(R.id.botonokru); //fefine boton okru



        iniDetails();
        checkbuttons();
        iniData();
    }

    /**
     * Obtiene detalles del capitulo del cotrolador
     */
    private void iniDetails() {
        details = contro.getChapDetails();
    }

    /**
     * Deshabilita botones en caso se que la referencia sea null
     */
    private void checkbuttons(){
        if (details.getVideojk()==null){
            BJK.setEnabled(false);
        }
        if (details.getVideomega()==null){
            BMega.setEnabled(false);
        }
        if (details.getVideookru()==null){
            Bokru.setEnabled(false);
        }
    }


    /**
     * Inicializa los datos de la pantalla
     */
    private void iniData() {
        //title.setText(details.getCapitulo());

        WebSettings ajustesVisorWeb = video.getSettings(); //Inicializa web view
        video.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY); //define scroll bar style
        ajustesVisorWeb.setJavaScriptEnabled(true); //activa java sctipt


        if (details.getVideojk()!=null) {
            //String url= Constantes.url+details.getVideo();
            video.loadUrl(details.getVideojk()); //opción por defecto si el video de mazon existe en este caso es necesario pasarel antes la url princial
            BJK.setAlpha(0.7f); //definir alpha especial
            BMega.setAlpha(1f);
        } else {
            video.loadUrl(details.getVideomega()); //carga opciond e mega si amazon es null
            BJK.setAlpha(1f);
            BMega.setAlpha(0.7f); //define alpha especial

        }


    }


    /**
     * Carga video de Mega
     * @param v
     */
    public void loadmega(View v){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Loading...", Toast.LENGTH_SHORT);

        toast1.show();
        video.loadUrl(details.getVideomega());
        BMega.setAlpha(0.7f);
        BJK.setAlpha(1f);
        Bokru.setAlpha(1f);
    }

    /**
     * Carga video de ok.ru
     * @param v
     */
    public void loadokru(View v){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Loading...", Toast.LENGTH_SHORT);

        toast1.show();
        video.loadUrl(details.getVideookru());
        BMega.setAlpha(1f);
        BJK.setAlpha(1f);
        Bokru.setAlpha(0.7f);
    }

    /**
     * Carga video de jk
     * @param v
     */
    public void loadjk(View v){
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Loading...", Toast.LENGTH_SHORT);

        toast1.show();
        video.loadUrl(details.getVideojk());
        BMega.setAlpha(1f);
        BJK.setAlpha(0.7f);
        Bokru.setAlpha(1f);
    }

}