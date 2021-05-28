package com.example.comprobadoranimes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import Constantes.Constantes;
import Controllers.MainController;
import Models.Serie;
import adapters.AdapterSeries;

/**
 * Clase que cotrola las series actuales
 */
public class list_seri_act extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView listseries;
    private MainController contro;
    private SearchView search;
    private AdapterSeries ASeries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_seri_act); //define archivo xml del layout
        listseries= findViewById(R.id.listcaps); //Se asocia al id del elemento del layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //se define orientación
        search = findViewById(R.id.searchv);
        contro= MainController.getSingleton(); //se obtiene singleton del controlador
        iniListas();
        inilisteners();
    }

    /**
     * incializa elementos y datos
     */
    public void iniListas(){
        contro.CrearListasGuardadas(); //crea listas de archivos xml
        listseries.setHasFixedSize(true);
        //definimos el layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //se define layout para recycled view
        listseries.setLayoutManager(layoutManager); //asigna layout a Recycled view
        ArrayList<Serie> lista= contro.getSeries_guardadas(); //obtiene lista de series
        //creamos el adaptador
        ASeries= new AdapterSeries(lista, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(list_seri_act.this,SeriDetails.class); //definimos intent
                int position= listseries.getChildAdapterPosition(v);
                //Serie s = lista.get(position);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Loading...", Toast.LENGTH_SHORT);

                toast1.show();
                contro.crearlistas(lista.get(position).getRef(),"contDetails"); //creamos detalles de la serie
                startActivity(myintent); //iniciamos nueva activity
            }
        });

        listseries.setAdapter(ASeries); //asignamos el adaptador

    }

    /**
     * Inicialozamos listeners para la barra buscadora
     */
    public void inilisteners(){
        search.setOnQueryTextListener(this);
    }

    /**
     * Actualiza las liatas en base a un filtro cuando se busca
     * @param query
     * @return false
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onQueryTextSubmit(String query) {
        //ASeries.filter(query);
        return false;
    }

    /**
     * Actualiza las liatas en base a un filtro cuando se escribe
     * @param newText
     * @return false
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onQueryTextChange(String newText) {
        ASeries.filter(newText);
        return false;
    }
}
