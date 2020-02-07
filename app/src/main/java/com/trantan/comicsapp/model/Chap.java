package com.trantan.comicsapp.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Chap {
    private String mId;
    private String mNameChap;
    private String mIdComic;
    private String mDate;

    public Chap(String mId, String mNameChap, String mIdComic, String mDate) {
        this.mId = mId;
        this.mNameChap = mNameChap;
        this.mIdComic = mIdComic;
        this.mDate = mDate;
    }

    public Chap(String mNameChap, String mIdComic, String mDate) {
        this.mNameChap = mNameChap;
        this.mIdComic = mIdComic;
        this.mDate = mDate;
    }

    public Chap(JSONObject o) throws JSONException {
        mId = o.getString("id");
        mNameChap = o.getString("nameChap");
        mDate = o.getString("date");
    }

    public Chap() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNameChap() {
        return mNameChap;
    }

    public void setmNameChap(String mNameChap) {
        this.mNameChap = mNameChap;
    }

    public String getmIdComic() {
        return mIdComic;
    }

    public void setmIdComic(String mIdComic) {
        this.mIdComic = mIdComic;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
