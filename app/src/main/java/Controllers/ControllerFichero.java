package Controllers;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import com.example.comprobadoranimes.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Constantes.Constantes;
import Models.Capitulo;
import Models.Serie;
import Models.Usuario;
/**
 * Clase para controlar el guardado en ficheros
 * @author alvar
 *
 */
public class ControllerFichero {
	

	protected MainActivity actividad;
	
	public ControllerFichero(MainActivity actividad) {
		this.actividad=actividad;
		//caps= new ArrayList();
		//series=new ArrayList();
	}


	/**
	 * Retorna el ultimo usuario guardado
	 * @return
	 */
	public Usuario getdestinatario(){
		Usuario users=null;

		try
		{
			BufferedReader fin =
					new BufferedReader(
							new InputStreamReader(
									actividad.openFileInput(Constantes.FICHEROUSUARIOS)));

			String texto = fin.readLine();
			String[] lista= texto.split(";");
			users= new Usuario(lista[0],lista[1],Boolean.valueOf(lista[2]));
			fin.close();
		}
		catch (Exception ex)
		{
			Log.e("Ficheros", "Error al leer fichero desde memoria interna");
		}


		return users;
		
	}

	/**
	 * Guarda el usuario
	 * @param user
	 */
	public void GuardarUser(Usuario user) {

		try {

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(actividad.openFileOutput(Constantes.FICHEROUSUARIOS, actividad.MODE_PRIVATE));
			outputStreamWriter.write(user.getNombre()+";"+user.getCorreo()+";"+user.isOpcion());
			outputStreamWriter.close();
		}
		catch (IOException e) {
			Log.e("Exception", "File write failed: " + e.toString());
			e.printStackTrace();
		}

	}


}
