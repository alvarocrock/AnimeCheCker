package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.comprobadoranimes.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import Models.Capitulo;
import Models.Serie;

/**
 * Clase que hace de adapter para rellenar recycled view del listado de capitulos
 */
public class AdapterCaps extends RecyclerView.Adapter<AdapterCaps.ViewHolderDatos> implements View.OnClickListener {

    private ArrayList<Capitulo> list;
    private View.OnClickListener listener;

    /**
     * hay que pasarle una array list de capitulos y un "OnClickListener" que lleve a la siguiente <br/>
     * pantalla en este caso a listado de capitulos
     * @param list
     * @param listener
     */
    public AdapterCaps(ArrayList<Capitulo> list,View.OnClickListener listener) {
        this.list = list;
        this.listener=listener;

    }


    @NonNull
    @Override
    public AdapterCaps.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcap,null,false);
        view.setOnClickListener(this);
        return  new AdapterCaps.ViewHolderDatos(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCaps.ViewHolderDatos holder, int position) {
        holder.asignardatos(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
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
     * Adapter datos de cspitulo
     * @author alvar
     */
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView serie;
        TextView capi;

        ImageView img;

        /**
         * inicializa elementos del layout personalizado
         * @param itemView
         */
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgcap);
            capi = itemView.findViewById(R.id.cap);
            serie = (TextView) itemView.findViewById(R.id.titulo);
        }

        /**
         * asigna datos para rellenar el layout personalizado del RecycledView
         * @param cap
         */
        public void asignardatos(Capitulo cap){
            serie.setText(cap.getSerie());
            capi.setText("EP"+cap.getCapitulo());
            String url =cap.getImagen();
            Picasso.get().load(url).resize(150, 240).into(img);
        }
    }

}
