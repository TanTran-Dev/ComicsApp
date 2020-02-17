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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trantan.comicsapp.R;
import com.trantan.comicsapp.interfaces.RCItemClickListener;
import com.trantan.comicsapp.model.Comic;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicHolder> {
    private List<Comic> listComic;
    private RCItemClickListener itemClickListener;

    public ComicAdapter(List<Comic> listComic, RCItemClickListener itemClickListener) {
        this.listComic = listComic;
        this.itemClickListener = itemClickListener;
    }

    public void setListComic(List<Comic> listComic) {
        this.listComic = listComic;
    }

    public List<Comic> getListComic() {
        return listComic;
    }


    @NonNull
    @Override
    public ComicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ComicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicHolder holder, int position) {
        Comic comic = listComic.get(position);
        holder.txtComicName.setText(comic.getmNameComic());
        holder.txtChapName.setText(comic.getmNameChap());
        Glide.with(holder.itemView.getContext()).load(comic.getmLinkImage()).into(holder.imgImageComic);
    }

    @Override
    public int getItemCount() {
        if (listComic == null){
            return 0;
        }
        return listComic.size();
    }

    class ComicHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgImageComic;
        TextView txtChapName;
        TextView txtComicName;

        public ComicHolder(@NonNull View itemView) {
            super(itemView);

            imgImageComic = itemView.findViewById(R.id.imgImageComic);
            txtComicName = itemView.findViewById(R.id.txtComicName);
            txtChapName = itemView.findViewById(R.id.txtChapName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
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
