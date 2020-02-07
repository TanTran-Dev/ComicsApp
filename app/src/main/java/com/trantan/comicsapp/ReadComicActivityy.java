package com.trantan.comicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trantan.comicsapp.api.APIGetPageComic;
import com.trantan.comicsapp.interfaces.GetPageComicFromAPI;
import com.trantan.comicsapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ReadComicActivityy extends AppCompatActivity implements GetPageComicFromAPI, View.OnClickListener {
    private ImageView imgImage;
    private ImageView imgLeft;
    private ImageView imgRight;
    private TextView txtNumPage;

    private List<String> listUrlImage;
    private int numPage;
    private int numPageReading;
    String idChap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic_activityy);

        init();
        initView();
        initSetup();
        initEventClick();

        new APIGetPageComic(this, idChap).execute();
    }

    private void initEventClick() {
        imgLeft.setOnClickListener(this);
        imgRight.setOnClickListener(this);
    }

    private void initSetup() {
//        readByPage(0);
    }

    private void initView() {
        imgImage = findViewById(R.id.imgImage);
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        txtNumPage = findViewById(R.id.txtNumPage);
    }

    private void init() {
        Bundle bundle = getIntent().getBundleExtra("data");
        idChap = bundle.getString("idChap");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLeft:
                readByPage(-1);
                break;
            case R.id.imgRight:
                readByPage(1);
                break;
        }
    }

    private void readByPage(int i) {
        numPageReading = numPageReading + i;

        if (numPageReading == 0) {
            numPageReading = 1;
        }

        if (numPageReading > numPage) {
            numPageReading = numPage;
        }

        txtNumPage.setText(numPageReading + " / " + numPage);
        Glide.with(this).load(listUrlImage.get(numPageReading - 1)).into(imgImage);
    }

    @Override
    public void start() {

    }

    @Override
    public void finish(String data) {

        listUrlImage = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                listUrlImage.add(array.getString(i));
            }

            numPageReading = 1;
            numPage = listUrlImage.size();

            readByPage(0);
        } catch (JSONException e) {

        }
    }

    @Override
    public void error() {

    }
}
