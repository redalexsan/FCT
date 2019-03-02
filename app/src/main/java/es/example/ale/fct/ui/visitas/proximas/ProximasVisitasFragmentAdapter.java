package es.example.ale.fct.ui.visitas.proximas;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.R;
import es.example.ale.fct.data.model.Visita;

public class ProximasVisitasFragmentAdapter extends ListAdapter<Visita, ProximasVisitasFragmentAdapter.ViewHolder> {

    protected ProximasVisitasFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Visita>() {
            @Override
            public boolean areItemsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return  TextUtils.equals(oldItem.getDia(),newItem.getDia());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return TextUtils.equals(oldItem.getDia(),newItem.getDia());
            }
        });
    }

    @Override
    public Visita getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProximasVisitasFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_proximas_visitas_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nombre, dia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = ViewCompat.requireViewById(itemView,R.id.lblName);
            dia = ViewCompat.requireViewById(itemView,R.id.txtFecha);
        }

        public void bind(Visita item) {
            nombre.setText(item.getNombreAlumno());
            dia.setText(item.getDia());
        }
    }
}
