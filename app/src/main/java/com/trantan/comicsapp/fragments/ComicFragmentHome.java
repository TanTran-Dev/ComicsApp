package com.trantan.comicsapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.trantan.comicsapp.R;
import com.trantan.comicsapp.activities.ChapActivity;
import com.trantan.comicsapp.adapter.ComicAdapter;
import com.trantan.comicsapp.api.APIGetComic;
import com.trantan.comicsapp.interfaces.GetComicFromAPI;
import com.trantan.comicsapp.interfaces.RCItemClickListener;
import com.trantan.comicsapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ComicFragmentHome extends Fragment implements GetComicFromAPI, RCItemClickListener, View.OnClickListener {

    private RecyclerView rclComic;
    private ComicAdapter adapter;
    private List<Comic> listComic;
    private ProgressDialog dialog;
    private FloatingActionButton floatingActionButtonUpdate;

    private boolean isLoaded = false;
    public ComicAdapter getAdapter() {
        return adapter;
    }

    public List<Comic> getListComic() {
        return listComic;
    }

    public void setListComic(List<Comic> listComic) {
        this.listComic = listComic;
    }

    public void setAdapter(ComicAdapter adapter) {
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_comic, container, false);

        rclComic = view.findViewById(R.id.rclComic);
        floatingActionButtonUpdate = view.findViewById(R.id.floatActionUpdate);

        listComic = new ArrayList<>();
        floatingActionButtonUpdate.setOnClickListener(this);

        new APIGetComic(this).execute();

        return view;
    }

    @Override
    public void start() {
            dialog = new ProgressDialog(getContext());
            dialog.setMessage("Loading");
            dialog.show();
    }

    @Override
    public void finish(String data) {
            try {
                listComic.clear();
                JSONArray array = new JSONArray(data);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    listComic.add(new Comic(object));
                }
                adapter = new ComicAdapter(listComic , this);
                rclComic.setLayoutManager(new GridLayoutManager(getContext(),3));
                rclComic.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        dialog.dismiss();
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatActionUpdate:
                new APIGetComic(this).execute();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Comic comic = listComic.get(position);
        Intent intent = new Intent(getContext(), ChapActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("comic", comic);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
