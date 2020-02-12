package com.trantan.comicsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trantan.comicsapp.R;
import com.trantan.comicsapp.interfaces.RCItemClickListener;
import com.trantan.comicsapp.model.Chap;

import java.util.List;

public class ChapAdapter extends RecyclerView.Adapter<ChapAdapter.ChapHolder> {
    private List<Chap> listChap;
    public RCItemClickListener itemClickListener;

    public ChapAdapter(List<Chap> listChap, RCItemClickListener itemClickListener) {
        this.listChap = listChap;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ChapHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chap, parent, false);
        return new ChapHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapHolder holder, int position) {
        Chap chap = listChap.get(position);
        holder.txtChapName.setText(chap.getmNameChap());
        holder.txtDate.setText("Ngày cập nhật: " + chap.getmDate());
    }

    @Override
    public int getItemCount() {
        if (listChap == null) {
            return 0;
        }
        return listChap.size();
    }


    class ChapHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtChapName;
        TextView txtDate;


        public ChapHolder(@NonNull View itemView) {
            super(itemView);

            txtChapName = itemView.findViewById(R.id.txtChapName);
            txtDate = itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
