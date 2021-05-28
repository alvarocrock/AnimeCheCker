package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprobadoranimes.R;

import java.util.ArrayList;

import Models.Tag;
/**
 * Clase que hace de adapter para rellenar recycled view del listado de tags o generos en detalles de series
 */
public class adapterTags extends RecyclerView.Adapter<adapterTags.ViewHolderDatos>{
    private ArrayList<Tag> lista;

    /**
     * hay que pasarle una array list de capitulos
     * @param lista
     */
    public adapterTags(ArrayList<Tag> lista){
        this.lista=lista;
    }
    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tags,null,false);
        return  new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(lista.get(position));
    }

    /**
     * Obtiene el tamaño de la lista de objrtod
     * @return
     */
    @Override
    public int getItemCount() {
        return lista.size();
    }


    
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView text;

        /**
         * inicializa elementos del layout personalizado
         * @param itemView
         */
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.Tag);
        }

        /**
         * asigna datos para rellenar el layout personalizado del RecycledView
         * @param tag
         */
        public void asignarDatos(Tag tag) {
            text.setText(tag.getName());
        }
    }
}
