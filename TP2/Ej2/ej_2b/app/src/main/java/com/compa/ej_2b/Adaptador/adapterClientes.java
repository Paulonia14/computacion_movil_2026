package com.compa.ej_2b.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.compa.ej_2b.Entidades.clientes;
import com.compa.ej_2b.R;

import java.util.ArrayList;

public class adapterClientes extends RecyclerView.Adapter<adapterClientes.ViewHolderClientes> {

    ArrayList<clientes> listClientes;
    RecyclerItemClick recyclerItemClick;

    public interface RecyclerItemClick {
        void itemClick(clientes clientes);
    }

    public adapterClientes(ArrayList<clientes> listClientes, RecyclerItemClick recyclerItemClick) {
        this.listClientes = listClientes;
        this.recyclerItemClick = recyclerItemClick;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_clientes, null, false);
        return new ViewHolderClientes(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderClientes holder, int position) {
        final clientes item=listClientes.get(position);
        holder.id.setText(listClientes.get(position).getIdCliente()+"");
        holder.nombre.setText(listClientes.get(position).getNombre());
        holder.apellido.setText(listClientes.get(position).getApellido());
        holder.documento.setText(listClientes.get(position).getDocumento());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClick.itemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    public class ViewHolderClientes extends RecyclerView.ViewHolder {

        TextView id, nombre, apellido, documento;
        public ViewHolderClientes(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.txtidlist);
            nombre=itemView.findViewById(R.id.txtnomlist);
            apellido=itemView.findViewById(R.id.txtapellist);
            documento=itemView.findViewById(R.id.txtdoclist);
        }
    }
}
