package com.trantan.comicsapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trantan.comicsapp.R;
import com.trantan.comicsapp.adapter.ChapAdapter;
import com.trantan.comicsapp.api.APIGetChap;
import com.trantan.comicsapp.interfaces.GetChapFromAPI;
import com.trantan.comicsapp.interfaces.RCItemClickListener;
import com.trantan.comicsapp.model.Chap;
import com.trantan.comicsapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ChapActivity extends AppCompatActivity implements GetChapFromAPI, View.OnClickListener, RCItemClickListener{
    private ImageView imgComic;
    private TextView txtTitle;
    private RecyclerView rclChap;
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
        imgProfileComic = findViewById(R.id.profile_comic_image);
        imgBack = findViewById(R.id.imgBack);
        rclChap = findViewById(R.id.rclChaps);
    }

    private void init() {
        Bundle bundle = getIntent().getBundleExtra("data");
        comic = (Comic) bundle.getSerializable("comic");

        listChap = new ArrayList<>();
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
            chapAdapter = new ChapAdapter(listChap, this);
            rclChap.setLayoutManager(new LinearLayoutManager(this));
            rclChap.setAdapter(chapAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBack:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ReadComicActivityy.class);
        Bundle bundle = new Bundle();

        bundle.putString("idChap", listChap.get(position).getmId());
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
