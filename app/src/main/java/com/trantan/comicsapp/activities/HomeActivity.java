package com.trantan.comicsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.trantan.comicsapp.R;
import com.trantan.comicsapp.adapter.ComicAdapter;
import com.trantan.comicsapp.fragments.ComicFragmentHome;
import com.trantan.comicsapp.fragments.SavedFragment;
import com.trantan.comicsapp.fragments.SearchFragment;
import com.trantan.comicsapp.interfaces.OnBackPressedListener;
import com.trantan.comicsapp.model.Comic;

import java.util.List;

public class HomeActivity extends AppCompatActivity{

    private List<Comic> listComic;
    private GridView gvComic;
    private ComicAdapter adapter;
    private EditText edtSearchComic;
    private ImageView imgMenu;
    private ImageView imgUpdate;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        initView();
        initSetup();
        initEventClick();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getSupportFragmentManager().getBackStackEntryCount() == 0){
//                    finish();
//                }
//            }
//        });
        loadFragment(new ComicFragmentHome());
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    private void initEventClick() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);
    }

    private void init() {
    }

    private void initSetup() {
    }
//
//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable editable) {
//        String s = edtSearchComic.getText().toString();
//        adapter.sortComic(s);
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    fragment = new ComicFragmentHome();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_search:
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_saved:
                    fragment = new SavedFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
