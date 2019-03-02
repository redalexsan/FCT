package es.example.ale.fct.ui.listaEmpresas;

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
import es.example.ale.fct.R;
import es.example.ale.fct.data.model.Empresa;
import es.example.ale.fct.MainActivityViewModel;

public class ListaEmpresasFragmentAdapter extends ListAdapter<Empresa, ListaEmpresasFragmentAdapter.ViewHolder> {

    private NavController navController;
    private MainActivityViewModel mainActivityViewModel;

    protected ListaEmpresasFragmentAdapter(NavController navController, MainActivityViewModel mainActivityViewModel) {
        super(new DiffUtil.ItemCallback<Empresa>() {
            @Override
            public boolean areItemsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return false;
            }
        });
        this.navController = navController;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaEmpresasFragmentAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_lista_empresas_item,parent,false), navController,mainActivityViewModel);

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

            itemView.setOnClickListener(v -> selectEmpresa(mainActivityViewModel,navController,getItem(getAdapterPosition())));
        }

        private void selectEmpresa(MainActivityViewModel mainActivityViewModel,NavController navController, Empresa item) {
            mainActivityViewModel.setNombreEmpresaSeleccionada(item.getNombre());
            navController.popBackStack();
        }

        public void bind(Empresa item) {
            nombre.setText(item.getNombre());
            telefono.setText(String.valueOf(item.getTelf()));
        }

    }
}
