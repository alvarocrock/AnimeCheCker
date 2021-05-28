package adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprobadoranimes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Constantes.Constantes;
import Models.Serie;
/**
 * Clase que hace de adapter para rellenar recycled view del listado de series
 */
public class AdapterSeries extends RecyclerView.Adapter<AdapterSeries.AdapterSeriesDatos> implements View.OnClickListener{

    private ArrayList<Serie> lista;
    private ArrayList<Serie> listaoriginal;
    private View.OnClickListener listener;

    /**
     * hay que pasarle una array list de series y un "OnClickListener" que lleve a la siguiente <br/>
     * pantalla en este caso a listado de series
     * @param lista
     * @param listener
     */
    public AdapterSeries(ArrayList<Serie> lista,View.OnClickListener listener) {
        this.lista = lista;
        this.listener=listener;
        this.listaoriginal = new ArrayList<>();
        listaoriginal.addAll(lista);

    }

    @NonNull
    @Override
    public AdapterSeriesDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listseries,null,false);
        view.setOnClickListener(this);
        return new AdapterSeriesDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSeriesDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    /**
     * Acción on click del elemento pulsado de la lista
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }

    }

    /**
     * Filtra en base a un resultado
     * @param busc
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filter(String busc){
        if (busc.length() == 0){
            lista.clear();
            lista.addAll(listaoriginal);
        } else {
            List<Serie> collect = listaoriginal.stream().filter(i -> i.getNombre().toLowerCase().contains(busc)).collect(Collectors.toList());
            lista.clear();
            lista.addAll(collect);
        }
        notifyDataSetChanged();
    }

    /**
     * Adapter datos de serie
     * @author alvar
     */
    public class AdapterSeriesDatos extends RecyclerView.ViewHolder {

        TextView titulo;

        TextView estado;
        TextView dia;
        ImageView img;


        /**
         * inicializa elementos del layout personalizado
         * @param itemView
         */
        public AdapterSeriesDatos(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.imgseries);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
            estado = itemView.findViewById(R.id.estado);
            dia = itemView.findViewById(R.id.dia);
        }

        /**
         * asigna datos para rellenar el layout personalizado del RecycledView
         * @param serie
         */
        public void asignarDatos(Serie serie) {
            titulo.setText(serie.getNombre());
            estado.setText(serie.getEstado());
            dia.setText(serie.getDia());

            String url=serie.getImagen();

            Picasso.get().load(url).resize(200,300).into(img);
        }
    }
}
