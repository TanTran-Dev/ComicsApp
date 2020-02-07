package com.trantan.comicsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.trantan.comicsapp.R;
import com.trantan.comicsapp.model.Comic;

import java.util.List;

public class ComicAdapter extends ArrayAdapter<Comic> {
    private Context context;
    private List<Comic> listComic;
    private int resource;

    public ComicAdapter(@NonNull Context context, int resource, @NonNull List<Comic> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listComic = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comic, null);

        }

        if (listComic.size() > 0){
            Comic comic = this.listComic.get(position);

            ImageView imgImageComic = convertView.findViewById(R.id.imgImageComic);
            TextView txtChapName = convertView.findViewById(R.id.txtChapName);
            TextView txtComicName = convertView.findViewById(R.id.txtComicName);


            txtComicName.setText(comic.getmNameComic());
            txtChapName.setText(comic.getmNameChap());
            Glide.with(this.context).load(comic.getmLinkImage()).into(imgImageComic);
        }

        return convertView;
    }


    public void sortComic(String pName){
        pName = pName.toUpperCase();

        int k = 0;
        for (int i = 0; i < listComic.size() ; i++) {
            Comic comic = listComic.get(i);
            String name = comic.getmNameComic().toUpperCase();
            if (name.indexOf(pName) >= 0){
                listComic.set(i, listComic.get(k));
                listComic.set(k, comic);
                k++;
            }
        }

        notifyDataSetChanged();
    }
}
