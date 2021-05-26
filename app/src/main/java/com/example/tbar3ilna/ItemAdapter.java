package com.example.tbar3ilna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private int mResource;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView bloodGrpView = convertView.findViewById(R.id.bloodGrpView);
        TextView txtName = convertView.findViewById(R.id.textView_name);
        TextView txtDesc = convertView.findViewById(R.id.textView_description);
        TextView ID = convertView.findViewById(R.id.textView_hidden);

        bloodGrpView.setText(getItem(position).getBLOODGRP().toUpperCase());
        txtName.setText(getItem(position).getNAME());
        txtDesc.setText(getItem(position).getDESCRIPTION());
        ID.setText(getItem(position).getID());

        return convertView;
    }
}
