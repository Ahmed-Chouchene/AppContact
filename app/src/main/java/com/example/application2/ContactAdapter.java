package com.example.application2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    int resource;
    Context context;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        TextView Name = convertView.findViewById(R.id.Name);
        TextView Phone = convertView.findViewById(R.id.Phone);

        Contact currentcontact = getItem(position);
        Name.setText(currentcontact.getName());
        Phone.setText(String.valueOf(currentcontact.getPhone()));

        return convertView;

    }
}
