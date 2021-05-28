package com.example.comprobadoranimes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Constantes.Constantes;
import Controllers.MainController;
import Models.Capitulo;
import Models.Serie;
import Models.SerieDetails;
import Models.ShortChap;
import adapters.AdapterCaps;
import adapters.adapterShortChar;
import adapters.adapterTags;

/**
 * Clase que controla los detalles de series.
 */
public class SeriDetails extends AppCompatActivity {

     private TextView titulo;
     private TextView tipo;
     private TextView description;
     private TextView total;
     private RecyclerView tags;
     private RecyclerView caps;
     private ImageView image;
     private MainController contro;
     private SerieDetails details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seri_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inivariables();
        iniDetails();

        iniData();



    }

    /**
     * Inicializa los datos en los elemtentos de la pantalla
     */
    private void iniData(){
        tipo.setText(details.getEstado());
        titulo.setText(details.getTitulo());
        description.setText(details.getDescription());
        description.setMovementMethod(new ScrollingMovementMethod());
        String s=null;
        if (details.getNextChap()!=null){
           s= "Next Chapter"+ details.getNextChap();
        }
        total.setText("Total "+details.getTotalChap()+" " +s);
        String url="";
        if (details.getImage().contains("https://")) {
            url = details.getImage();
        } else{
            url = Constantes.url+details.getImage();
        }
        Picasso.get().load(url).into(image);
        //inicializa recycled view
        adapterTags adaptertag= new adapterTags(details.getTags());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        tags.setLayoutManager(layoutManager);
        tags.setAdapter(adaptertag);

        ArrayList<ShortChap> chapters= details.getChaps();

        //inicializa recycled view
        adapterShortChar adapterchar = new adapterShortChar(chapters, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent= new Intent(SeriDetails.this,ChapDetails.class);
                int position= caps.getChildAdapterPosition(v);
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Loading...", Toast.LENGTH_SHORT);

                toast1.show();
                contro.crearlistas(chapters.get(position).getRef(),"chatDetails");
                //myintent.putExtra("URL",chapters.get(position).getRef());
                startActivity(myintent);
            }
        });
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        caps.setLayoutManager(layoutManager2);
        caps.setAdapter(adapterchar);



    }
    
    /**
     * Inicializa detalles
     */
    private void iniDetails(){
        details = contro.getDetails();
    }


    /**
     * Inicializa variables
     */
    private void inivariables(){
        caps =  findViewById(R.id.listShortSeri);
        tipo = findViewById(R.id.Type);
        titulo = findViewById(R.id.Title);
        tags = findViewById(R.id.listTags);
        description = findViewById(R.id.Description);
        total = findViewById(R.id.Total);
        image = findViewById(R.id.imageView);
        contro = MainController.getSingleton();
    }


}