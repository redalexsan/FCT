package es.example.ale.fct.ui.listaAlumnos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.example.ale.fct.MainActivityViewModel;
import es.example.ale.fct.R;
import es.example.ale.fct.data.model.Alumno;

public class ListAlumnoFragmentAdapter extends ListAdapter<Alumno, ListAlumnoFragmentAdapter.ViewHolder> {

    private MainActivityViewModel mainActivityViewModel;
    private NavController navController;

    protected ListAlumnoFragmentAdapter(NavController navController,MainActivityViewModel mainActivityViewModel) {
        super(new DiffUtil.ItemCallback<Alumno>() {
            @Override
            public boolean areItemsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return false;
            }
        });
        this.mainActivityViewModel = mainActivityViewModel;
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListAlumnoFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_alumnos_item,parent,false), navController,mainActivityViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, telefono;
        public ViewHolder(@NonNull View itemView,NavController navController,MainActivityViewModel mainActivityViewModel) {
            super(itemView);

            nombre = ViewCompat.requireViewById(itemView,R.id.lblName);
            telefono = ViewCompat.requireViewById(itemView,R.id.txtTelefono);

            itemView.setOnClickListener(v -> selectAlumno(mainActivityViewModel,navController,getItem(getAdapterPosition())));
        }

        private void selectAlumno(MainActivityViewModel mainActivityViewModel,NavController navController, Alumno item) {
            mainActivityViewModel.setNombreAlumnoSeleccionado(item.getNombre());
            navController.popBackStack();
        }

        public void bind(Alumno item) {
            nombre.setText(item.getNombre());
            telefono.setText(String.valueOf(item.getTelf()));
        }
    }
}
