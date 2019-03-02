package es.example.ale.fct.ui.empresas;

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
import es.example.ale.fct.ui.formularioEmpresa.FormEmpresaFragmentArgs;

public class EmpresasFragmentAdapter extends ListAdapter<Empresa, EmpresasFragmentAdapter.ViewHolder> {

    private NavController navController;
    protected EmpresasFragmentAdapter(NavController navController) {
        super(new DiffUtil.ItemCallback<Empresa>() {
            @Override
            public boolean areItemsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Empresa oldItem, @NonNull Empresa newItem) {
                return TextUtils.equals(oldItem.getNombre(),newItem.getNombre()) && oldItem.getTelf() == newItem.getTelf() && TextUtils.equals(oldItem.getLogoURL(),newItem.getLogoURL());
            }
        });
        this.navController = navController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_empresas_item,parent,false), navController);
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
    protected Empresa getItem(int position) {
        return super.getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nombre, numTelefono;
        private final ImageView logo;

        public ViewHolder(@NonNull View itemView,NavController navController) {
            super(itemView);
            nombre = ViewCompat.requireViewById(itemView,R.id.lblName);
            numTelefono = ViewCompat.requireViewById(itemView,R.id.txtHoraFin);
            logo = ViewCompat.requireViewById(itemView,R.id.imgLogo);

            itemView.setOnClickListener(v -> editEmpresa(navController,getItem(getAdapterPosition())));
        }

        private void editEmpresa(NavController navController, Empresa item) {
            FormEmpresaFragmentArgs arg = new FormEmpresaFragmentArgs.Builder(String.valueOf(item.getId())).build();
            navController.navigate(R.id.formEmpresaFragment,arg.toBundle());
        }

        public void bind(Empresa item) {
            nombre.setText(item.getNombre());
            numTelefono.setText(String.valueOf(item.getTelf()));
            Picasso.with(nombre.getContext()).load(item.getLogoURL()).into(logo);
        }
    }
}
