package com.trantan.comicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.trantan.comicsapp.adapter.ComicAdapter;
import com.trantan.comicsapp.api.APIGetComic;
import com.trantan.comicsapp.interfaces.GetComicFromAPI;
import com.trantan.comicsapp.model.Comic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements GetComicFromAPI, TextWatcher, View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView gvComic;
    private ComicAdapter adapter;
    private EditText edtSearchComic;
    private ImageView imgMenu;
    private ImageView imgUpdate;
    private List<Comic> listComic;

    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        initView();
        initSetup();
        initEventClick();
        new APIGetComic(this).execute();
    }

    private void initView() {
        gvComic = findViewById(R.id.gvComic);
        edtSearchComic = findViewById(R.id.edtSearchComic);
        imgMenu = findViewById(R.id.imgMenu);
        imgUpdate = findViewById(R.id.imgUpdate);

    }

    private void initEventClick() {
        edtSearchComic.addTextChangedListener(this);
        imgMenu.setOnClickListener(this);
        imgUpdate.setOnClickListener(this);
        gvComic.setOnItemClickListener(this);
    }

    private void init() {
        listComic = new ArrayList<>();

        adapter = new ComicAdapter(this, R.layout.item_comic, listComic);
    }

    private void initSetup() {
        gvComic.setAdapter(adapter);
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
            listComic.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                listComic.add(new Comic(object));
            }
            adapter = new ComicAdapter(this, R.layout.item_comic, listComic);
            gvComic.setAdapter(adapter);
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
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String s = edtSearchComic.getText().toString();
        adapter.sortComic(s);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgMenu:
                break;
            case R.id.imgUpdate:
                new APIGetComic(this).execute();
                break;
        }
    }

    int index = -1;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        Comic comic = listComic.get(index);
        Intent intent = new Intent(parent.getContext(), ChapActivity.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("comic", comic);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}
