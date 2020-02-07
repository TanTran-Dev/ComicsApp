package com.trantan.comicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetComicFromAPI, TextWatcher, View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView gvComic;
    private ComicAdapter adapter;
    private EditText edtSearchComic;
    private ImageView imgMenu;
    private ImageView imgUpdate;
    private List<Comic> listComic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void initEventClick(){
        edtSearchComic.addTextChangedListener(this);
        imgMenu.setOnClickListener(this);
        imgUpdate.setOnClickListener(this);
        gvComic.setOnItemClickListener(this);
    }

    private void init(){
        listComic = new ArrayList<>();
//        listComic.add(new Comic("Vợ Ma","Chapter 111","https://3.bp.blogspot.com/-e3H1bXiJWHM/WBNEmFJ9B-I/AAAAAAAAMd0/tKBvR0tXr6Q/vo-ma.jpg"));
//        listComic.add(new Comic("Ubel Blatt","Chapter 167.1","https://3.bp.blogspot.com/-4lgkziTIG80/V5ZLrTlcUWI/AAAAAAAAAnk/Sw4Psj-1eQs/ubel-blatt.jpg"));
//        listComic.add(new Comic("Luân Hồi Ác Nhân","Chapter 18","http://st.nettruyen.com/data/comics/150/luan-hoi-ac-nhan.jpg"));
//        listComic.add(new Comic("Khu phố xa xăm","Chapter 6","http://st.nettruyen.com/data/comics/175/khu-pho-xa-xam.jpg"));
//
//        listComic.add(new Comic("Vợ Ma","Chapter 111","https://3.bp.blogspot.com/-e3H1bXiJWHM/WBNEmFJ9B-I/AAAAAAAAMd0/tKBvR0tXr6Q/vo-ma.jpg"));
//        listComic.add(new Comic("Ubel Blatt","Chapter 167.1","https://3.bp.blogspot.com/-4lgkziTIG80/V5ZLrTlcUWI/AAAAAAAAAnk/Sw4Psj-1eQs/ubel-blatt.jpg"));
//        listComic.add(new Comic("Luân Hồi Ác Nhân","Chapter 18","http://st.nettruyen.com/data/comics/150/luan-hoi-ac-nhan.jpg"));
//        listComic.add(new Comic("Khu phố xa xăm","Chapter 6","http://st.nettruyen.com/data/comics/175/khu-pho-xa-xam.jpg"));
//
//        listComic.add(new Comic("Vợ Ma","Chapter 111","https://3.bp.blogspot.com/-e3H1bXiJWHM/WBNEmFJ9B-I/AAAAAAAAMd0/tKBvR0tXr6Q/vo-ma.jpg"));
//        listComic.add(new Comic("Ubel Blatt","Chapter 167.1","https://3.bp.blogspot.com/-4lgkziTIG80/V5ZLrTlcUWI/AAAAAAAAAnk/Sw4Psj-1eQs/ubel-blatt.jpg"));
//        listComic.add(new Comic("Luân Hồi Ác Nhân","Chapter 18","http://st.nettruyen.com/data/comics/150/luan-hoi-ac-nhan.jpg"));
//        listComic.add(new Comic("Khu phố xa xăm","Chapter 6","http://st.nettruyen.com/data/comics/175/khu-pho-xa-xam.jpg"));

        adapter = new ComicAdapter(this,R.layout.item_comic,listComic);
    }

    private void initSetup(){
        gvComic.setAdapter(adapter);
    }

    @Override
    public void start() {
        Toast.makeText(this, "crawling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish(String data) {
        try{
            listComic.clear();
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length() ; i++) {
                JSONObject object = array.getJSONObject(i);
                listComic.add(new Comic(object));
            }
            adapter = new ComicAdapter(this,R.layout.item_comic,listComic);
            gvComic.setAdapter(adapter);
        }catch (JSONException e){

        }
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
        switch (v.getId()){
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
