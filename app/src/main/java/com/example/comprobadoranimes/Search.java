package com.example.comprobadoranimes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
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

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView listseries;
    private MainController contro;
    private SearchView search;
    //private AdapterSeries ASeries;
    private ArrayList<Serie> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search); //define archivo xml del layout
        listseries= findViewById(R.id.listcaps); //Se asocia al id del elemento del layout
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //se define orientación
        search = findViewById(R.id.searchv);
        contro= MainController.getSingleton(); //se obtiene singleton del controlador
        iniListas();
        inilisteners();
    }

    public void Search(){


        contro.Buscar("https://jkanime.net/buscar/"+search.getQuery()+"/1/");
        //temp.add(new Serie("test ","","","data:image/jpeg;base64,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","test"));
        if (contro.getResult_search().size()>0) {
            ChangeSearch(contro.getResult_search());
        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "There are no results", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    public void ChangeSearch(ArrayList<Serie> list){
        AdapterSeries adapter;

        lista=list;
        adapter= new AdapterSeries(lista, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(Search.this,SeriDetails.class); //definimos intent
                int position= listseries.getChildAdapterPosition(v);
                //Serie s = lista.get(position);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Loading...", Toast.LENGTH_SHORT);

                toast1.show();
                //si la url de la lista no tiene la url continnua
                if (!lista.get(position).getRef().contains("https://")) {
                    contro.crearlistas(Constantes.url + lista.get(position).getRef(), "contDetails"); //creamos detalles de la serie
                }
                //si la url de la lista esta completa
                if (lista.get(position).getRef().contains("https://")) {
                    contro.crearlistas(lista.get(position).getRef(), "contDetails"); //creamos detalles de la serie
                }
                startActivity(myintent); //iniciamos nueva activity
            }
        });

        listseries.setAdapter(adapter); //asignamos el adaptador
        listseries.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


    }
    /**
     * incializa elementos y datos
     */
    public void iniListas(){

        //contro.CrearListasGuardadas(); //crea listas de archivos xml
        listseries.setHasFixedSize(true);
        //definimos el layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); //se define layout para recycled view
        listseries.setLayoutManager(layoutManager); //asigna layout a Recycled view
        ArrayList<Serie> lista= new ArrayList<Serie>(); //obtiene lista de series

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
        Search();
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
        //ASeries.filter(newText);
        return false;
    }
}