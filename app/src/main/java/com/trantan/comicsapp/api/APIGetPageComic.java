package com.trantan.comicsapp.api;

import android.os.AsyncTask;

import com.trantan.comicsapp.interfaces.GetComicFromAPI;
import com.trantan.comicsapp.interfaces.GetPageComicFromAPI;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIGetPageComic extends AsyncTask<Void, Void, Void> {
    String data;
    GetPageComicFromAPI getPageComicFromAPI;
    String idChap;

    public APIGetPageComic(GetPageComicFromAPI getComicFromAPI, String idChap) {
        this.getPageComicFromAPI = getComicFromAPI;
        this.idChap = idChap;
        this.getPageComicFromAPI.start();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //.url("https://api.myjson.com/bins/1hf452")
                .url("http://tantran99.000webhostapp.com/getPage.php?id_chap=" + idChap)
                .build();
        data = null;
        try{
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            data = body.string();
        }catch (IOException e){
            data = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (data == null){
            this.getPageComicFromAPI.error();
        }else {
            this.getPageComicFromAPI.finish(data);
        }
    }
}
