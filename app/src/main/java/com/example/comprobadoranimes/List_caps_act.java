package com.example.comprobadoranimes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import Constantes.Constantes;
import Controllers.MainController;
import Models.Capitulo;
import adapters.AdapterCaps;

/**
 * Clase que controla la pantalla de lista de capitulos actuales
 */
public class List_caps_act extends AppCompatActivity {

    private RecyclerView listcaps;
    private MainController contro;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_caps_act); //define archivo xml de layout que se utilizara
        listcaps= findViewById(R.id.listcaps); //inicializa recycled view
        image = findViewById(R.id.cabeceracaps); //inicializa cabecera
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //define orientación
        contro= MainController.getSingleton(); //obtiene singelton del controlador
        iniListas();
    }

    /**
     * inicializa datos y elemtos del layout
     */
    public void iniListas(){
        contro.CrearListasGuardadas(); //inicializa las listas del fichero xml
        listcaps.setHasFixedSize(true);
        //definimos el layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //Define layout para recycled view
        listcaps.setLayoutManager(layoutManager); //asigna layout al recycled view

        //creamos el adaptador
        ArrayList<Capitulo> lista= contro.getCaps_guardados(); //obtiene lista de capitulos guardados
        //crea adapter de capitulos
        AdapterCaps ASeries= new AdapterCaps(lista,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(List_caps_act.this,ChapDetails.class); //crea intent
                int position= listcaps.getChildAdapterPosition(v);
                Capitulo s = lista.get(position);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Loading...", Toast.LENGTH_SHORT);

                toast1.show();
                contro.crearlistas(lista.get(position).getRef(), "chatDetails"); //crea detalles de series
                startActivity(myintent); //inicializa nueva activity o pantalla
            }
        });
        listcaps.setAdapter(ASeries); //asigna el adapter al recycled view

    }


}