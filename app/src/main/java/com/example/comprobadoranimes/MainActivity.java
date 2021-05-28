package com.example.comprobadoranimes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStreamReader;
import java.util.ArrayList;


import Controllers.ControladorCorreos;
import Controllers.ControllerFichero;
import Controllers.MainController;
import Models.Capitulo;
import Models.Serie;
import Models.Usuario;
import adapters.AdapterSeries;

/**
 * Clase que controla la pantalla principal del programa
 */
public class MainActivity extends AppCompatActivity{

    //ControladorCorreos correo;
    private MainController contro;
    private EditText TFuser;
    private EditText TFemail;
    private TextView log;
    private Switch swich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //se define el dichero xml con el layout
        contro = MainController.getSingleton(this); //se crea el controlador
        log = (TextView) findViewById(R.id.Log); //se instancia un elemento
        swich = findViewById(R.id.sendcomfirmation); //se instancia swich

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //definimos orientación
        contro.CrearListasGuardadas(); //creamos listas guardadas
        IniUserData(); //iniciamos datos de usuario




    }

    /**
     * Inicializa datos
     */
    public void IniUserData(){
        //file=new ControllerFichero();

        Usuario user= contro.Getuser();

        TFuser = (EditText) findViewById(R.id.LB_nombre);
        TFemail = (EditText) findViewById(R.id.LB_correo);

        if (user!=null){
            TFuser.setText(user.getNombre());
            TFemail.setText(user.getCorreo());
            swich.setChecked(user.isOpcion());
        }


    }

    /**
     * Navega a lista de series
     * @param view
     */
    public void goToListSeri(View view){
        Intent myintent= new Intent(MainActivity.this,list_seri_act.class);
        startActivity(myintent);
    }

    /**
     * Navega a lista de capitulos
     * @param view
     */
    public void goToListcaps(View view){
        Intent myintent= new Intent(MainActivity.this,List_caps_act.class);
        startActivity(myintent);
    }

    
    /**
     * Comprueba si hay cambios, si hay cambios y la opción de enviar correo esta seleccionada, <br/>
     * enviará un correo
     * @param view
     */
    public void CheckAll(View view){

        //reseteamos contenido de log
        log.setText("");
        log.setBackgroundColor(Color.WHITE);
        //mensaje de cargando
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "Loading...", Toast.LENGTH_SHORT);

        toast1.show();

            contro.comprobartodo(TFuser.getText().toString(), TFemail.getText().toString(), swich.isChecked());
            //nos aseguramos que el código de repsuesta sea corrrecto (200 bueno)
            if (contro.getReponse()>=200 && contro.getReponse()<300) {
                if (contro.getcapsize() > 0 && contro.getseriesize() > 0) {
                    log.setText("There's new stuff");
                    log.setBackgroundColor(Color.YELLOW);
                } else if (contro.getcapsize() > 0) {
                    log.setText("There are " + contro.getcapsize() + " chapters");
                    log.setBackgroundColor(Color.YELLOW);
                } else if (contro.getseriesize() > 0) {
                    log.setText("There are " + contro.getseriesize() + " new content");
                    log.setBackgroundColor(Color.YELLOW);
                } else {
                    log.setText("There's nothing new");
                    log.setBackgroundColor(Color.GREEN);
                }
            } else {
                log.setText("Something went worong");
                log.setBackgroundColor(Color.RED);
                log.setTextColor(Color.WHITE);
            }

            //iniListas();

    }


}