package com.example.sqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sqlite.Entidades.Personas;
import java.util.List;
public class PersonaAdapter extends
        RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {
    private List<Personas> listaPersonas;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
    public void setPersonasList(List<Personas> personas) {
        this.listaPersonas = personas;
        notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }
    public Personas getPersonaAt(int position) {
        return listaPersonas.get(position);
    }
    // Método para actualizar una persona en la lista
    public void updatePersona(int position, Personas persona) {
        listaPersonas.set(position, persona);
        notifyItemChanged(position);
    }
    // Método para eliminar una persona de la lista
    public void removePersona(int position) {
        listaPersonas.remove(position);
        notifyItemRemoved(position);
    }
    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = listaPersonas.get(position);
        holder.tvNombre.setText("Nombre: " + persona.nombrePersona);
        holder.tvApellido.setText("Apellido: " + persona.apellidoPersona);
        holder.tvEdad.setText("Edad: " + String.valueOf(persona.edadPersona));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (clickListener != null && position !=
                        RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                    longClickListener.onItemLongClick(position);
                    return true;
                }
                return false;
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    longClickListener.onItemLongClick(position);
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return listaPersonas != null ? listaPersonas.size() : 0;
    }
    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvApellido;
        TextView tvEdad;
        PersonaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvEdad = itemView.findViewById(R.id.tvEdad);
        }
    }
}