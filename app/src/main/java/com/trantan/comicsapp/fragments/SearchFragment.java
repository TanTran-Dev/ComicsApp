package com.trantan.comicsapp.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trantan.comicsapp.R;


public class SearchFragment extends Fragment {

    private Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        toolbar = view.findViewById(R.id.toolbar);;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        toolbar.setTitle("Tìm kiếm Truyện Tranh");
        toolbar.setTitleTextColor(Color.WHITE);

        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_search_white_24dp));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("onQueryTextSubmit: ", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}
