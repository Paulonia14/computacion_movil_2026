package com.example.tp_compmovil_2d.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_compmovil_2d.R;
import com.example.tp_compmovil_2d.ViewActivity;
import com.example.tp_compmovil_2d.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListContactsAdapter extends RecyclerView.Adapter<ListContactsAdapter.ContactViewHolder> {

    ArrayList<Contact> contacts;
    ArrayList<Contact> originalContacts;

    public ListContactsAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        this.originalContacts = new ArrayList<>();
        this.originalContacts.addAll(contacts);
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.viewName.setText(contacts.get(position).getName());
        holder.viewPhone.setText(contacts.get(position).getPhone());
        holder.viewEmail.setText(contacts.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void filter(final String text) {
        int length = text.length();
        if (length == 0) {
            contacts.clear();
            contacts.addAll(originalContacts);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contact> collection = originalContacts.stream()
                        .filter(i -> i.getName().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                contacts.clear();
                contacts.addAll(collection);
            } else {
                contacts.clear();
                for (Contact c : originalContacts) {
                    if (c.getName().toLowerCase().contains(text.toLowerCase())) {
                        contacts.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView viewName, viewPhone, viewEmail;

        public ContactViewHolder(View view) {
            super(view);

            viewName = itemView.findViewById(R.id.viewName);
            viewPhone = itemView.findViewById(R.id.viewPhone);
            viewEmail = itemView.findViewById(R.id.viewEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("ID", contacts.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
