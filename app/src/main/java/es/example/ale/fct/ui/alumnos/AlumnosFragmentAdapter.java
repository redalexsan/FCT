package es.example.ale.fct.ui.alumnos;

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
import es.example.ale.fct.data.model.Alumno;

public class AlumnosFragmentAdapter extends ListAdapter<Alumno,AlumnosFragmentAdapter.ViewHolder> {

    protected AlumnosFragmentAdapter() {
        super(new DiffUtil.ItemCallback<Alumno>() {
            @Override
            public boolean areItemsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Alumno oldItem, @NonNull Alumno newItem) {
                return TextUtils.equals(oldItem.getNombre(),newItem.getNombre()) && TextUtils.equals(oldItem.getEmail(),newItem.getEmail()) &&
                        oldItem.getTelf() == newItem.getTelf();
            }
        });
    }

    @NonNull
    @Override
    public AlumnosFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_alumnos_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosFragmentAdapter.ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    protected Alumno getItem(int position) {
        return super.getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView lblName;
        private final TextView lblEmail;
        private final TextView lblTelf;


        public ViewHolder(View itemView) {
            super(itemView);

            lblName = ViewCompat.requireViewById(itemView,R.id.lblName);
            lblEmail = ViewCompat.requireViewById(itemView,R.id.lblMail);
            lblTelf = ViewCompat.requireViewById(itemView,R.id.lblTelf);
        }

        public void bind(Alumno alumno){
            lblName.setText(alumno.getNombre());
            lblEmail.setText(alumno.getEmail());
            lblTelf.setText(String.valueOf(alumno.getTelf()));
        }
    }
}
