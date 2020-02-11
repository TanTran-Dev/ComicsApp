package com.trantan.comicsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trantan.comicsapp.R;
import com.trantan.comicsapp.model.Chap;

import java.util.List;

public class ChapAdapter extends ArrayAdapter<Chap> {
    private Context context;
    private List<Chap> listChap;
    private int resource;

    public ChapAdapter(@NonNull Context context, int resource, @NonNull List<Chap> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listChap = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chap, parent, false);
        }

        if (listChap.size() > 0){
            Chap chap = listChap.get(position);

            TextView txtChapName = convertView.findViewById(R.id.txtChapName);
            TextView txtDate = convertView.findViewById(R.id.txtDate);

            txtChapName.setText(chap.getmNameChap());
            txtDate.setText("Ngày cập nhật: " +chap.getmDate());
        }
        return convertView;
    }
}
