package es.example.ale.fct.ui.visitas.realizadas;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.R;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.data.model.Visita;
import es.example.ale.fct.ui.formularioVisita.FormVisitaFragmentArgs;

public class VisitasRealizadasFragmentAdapter extends ListAdapter<Visita, VisitasRealizadasFragmentAdapter.ViewHolder> {

    private NavController navController;

    public VisitasRealizadasFragmentAdapter(NavController navController) {
        super(new DiffUtil.ItemCallback<Visita>() {
            @Override
            public boolean areItemsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Visita oldItem, @NonNull Visita newItem) {
                return TextUtils.equals(oldItem.getNombreAlumno(),newItem.getNombreAlumno()) && TextUtils.equals(oldItem.getDia(),newItem.getDia()) && TextUtils.equals(oldItem.getHoraFin(),newItem.getHoraFin()) &&
                        TextUtils.equals(oldItem.getHoraInicio(),newItem.getHoraInicio());
            }
        });
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_visitas_item,parent,false),navController);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Visita getItem(int position) {
        return super.getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nombre, horaInicio,horaFin,dia;

        public ViewHolder(@NonNull View itemView, NavController navController) {
            super(itemView);
            nombre = ViewCompat.requireViewById(itemView,R.id.lblName);
            horaInicio = ViewCompat.requireViewById(itemView,R.id.txtHoraInicio);
            horaFin = ViewCompat.requireViewById(itemView,R.id.txtHoraFin);
            dia = ViewCompat.requireViewById(itemView,R.id.txtDia);

            itemView.setOnClickListener(v -> editVisita(navController,getItem(getAdapterPosition())));
        }

        private void editVisita(NavController navController, Visita item) {
            FormVisitaFragmentArgs arg = new FormVisitaFragmentArgs.Builder(String.valueOf(item.getId())).build();
            navController.navigate(R.id.formVisitaFragment,arg.toBundle());
        }

        public void bind(Visita visita) {
            nombre.setText(visita.getNombreAlumno());
            horaInicio.setText(visita.getHoraInicio());
            horaFin.setText(visita.getHoraFin());
            dia.setText(visita.getDia());
        }
    }
}
