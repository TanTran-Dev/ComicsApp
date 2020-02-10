package com.trantan.comicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trantan.comicsapp.adapter.ChapAdapter;
import com.trantan.comicsapp.adapter.ComicAdapter;
import com.trantan.comicsapp.api.APIGetChap;
import com.trantan.comicsapp.api.APIGetComic;
import com.trantan.comicsapp.interfaces.GetChapFromAPI;
import com.trantan.comicsapp.model.Chap;
import com.trantan.comicsapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ChapActivity extends AppCompatActivity implements GetChapFromAPI, AdapterView.OnItemClickListener, View.OnClickListener {
    private ImageView imgComic;
    private TextView txtTitle;
    private ListView lvChap;
    private ImageView imgProfileComic;
    private ImageView imgBack;

    private Comic comic;
    private ChapAdapter chapAdapter;
    private List<Chap> listChap;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);

        init();
        initView();
        initSetup();
        initEventClick();

        new APIGetChap(this, comic.getmId()).execute();

    }

    private void initEventClick() {
        lvChap.setOnItemClickListener(this);
        imgBack.setOnClickListener(this);
    }

    private void initSetup() {
        txtTitle.setText(comic.getmNameComic());
        Glide.with(this).load(comic.getmLinkImage()).transform(new BlurTransformation(6, 2)).into(imgComic);
        Glide.with(this).load(comic.getmLinkImage()).into(imgProfileComic);
    }

    private void initView() {
        imgComic = findViewById(R.id.imgComicChap);
        txtTitle = findViewById(R.id.txtComicTittle);
        lvChap = findViewById(R.id.lvChaps);
        imgProfileComic = findViewById(R.id.profile_comic_image);
        imgBack = findViewById(R.id.imgBack);
    }

    private void init() {
        Bundle bundle = getIntent().getBundleExtra("data");
        comic = (Comic) bundle.getSerializable("comic");

        listChap = new ArrayList<>();

//        chapAdapter = new ChapAdapter(this, R.layout.item_chap, listChap);
    }

    @Override
    public void start() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.show();
    }

    @Override
    public void finish(String data) {
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                Chap chap = new Chap(array.getJSONObject(i));
                listChap.add(chap);
            }
            chapAdapter = new ChapAdapter(this, R.layout.item_chap, listChap);
            lvChap.setAdapter(chapAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    int index = -1;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        Intent intent = new Intent(parent.getContext(), ReadComicActivityy.class);
        Bundle bundle = new Bundle();

        bundle.putString("idChap", listChap.get(position).getmId());
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }
}
