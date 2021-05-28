package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprobadoranimes.R;

import java.util.ArrayList;

import Models.Rel;
import Models.ShortChap;
/**
 * Clase que hace de adapter para rellenar recycled view del listado de capitulos en detalles de series actualmente no se utiliza(se usaba en otras paginas "animefenix.com")
 */
public class adapterShortChar extends RecyclerView.Adapter<adapterShortChar.ViewHolderDatos> implements View.OnClickListener{
    ArrayList<ShortChap> lista;
    private View.OnClickListener listener;

    /**
     * hay que pasarle una array list de capitulos y un "OnClickListener" que lleve a la siguiente <br/>
     * pantalla en este caso a listado de capitulos
     * @param lista
     * @param listener
     */
    public adapterShortChar(ArrayList<ShortChap> lista, View.OnClickListener listener){
        this.listener=listener;
        this.lista=lista;
    }
    @NonNull
    @Override
    public adapterShortChar.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.relacionados,null,false);
        view.setOnClickListener(this);
        return  new adapterShortChar.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
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
     * Adapter datos de ShortChar
     * @author alvar
     */
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView titulo;

        /**
         * inicializa elementos del layout personalizado
         * @param itemView
         */
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.TituloChapshort);

        }

        /**
         * asigna datos para rellenar el layout personalizado del RecycledView
         * @param rel
         */
        public void asignarDatos(ShortChap rel) {
            titulo.setText(rel.getName());

        }
    }
}
