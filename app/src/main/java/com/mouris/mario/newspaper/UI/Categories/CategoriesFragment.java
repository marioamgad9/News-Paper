package com.mouris.mario.newspaper.UI.Categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.mouris.mario.newspaper.R;

public class CategoriesFragment extends Fragment {


    public CategoriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        GridView gridView = rootView.findViewById(R.id.categoriesGridView);
        CategoriesGridAdapter gridAdapter = new CategoriesGridAdapter(getContext());
        gridView.setAdapter(gridAdapter);

        return rootView;
    }

}
