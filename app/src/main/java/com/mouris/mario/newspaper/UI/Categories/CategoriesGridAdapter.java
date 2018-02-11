package com.mouris.mario.newspaper.UI.Categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesGridAdapter extends BaseAdapter{

    private Context mContext;

    private List<String> mCategories;
    private List<Integer> mDrawables;

    public CategoriesGridAdapter(Context context) {

        mContext = context;

        //Initialize category titles
        mCategories = new ArrayList<>();
        mCategories.add(mContext.getString(R.string.business_category));
        mCategories.add(mContext.getString(R.string.entertainment_category));
        mCategories.add(mContext.getString(R.string.general_category));
        mCategories.add(mContext.getString(R.string.health_category));
//        mCategories.add(mContext.getString(R.string.science_category));
        mCategories.add(mContext.getString(R.string.sports_category));
        mCategories.add(mContext.getString(R.string.technology_category));


        //Initialize category drawables
        mDrawables = new ArrayList<>();
        mDrawables.add(R.drawable.business_category);
        mDrawables.add(R.drawable.entertainment_category);
        mDrawables.add(R.drawable.general_category);
        mDrawables.add(R.drawable.health_category);
//        mDrawables.add(R.drawable.science_category);
        mDrawables.add(R.drawable.sports_category);
        mDrawables.add(R.drawable.technology_category);
    }

    @Override
    public int getCount() {
        return mCategories.size();
    }

    public String getCategoryName(int position) {
        return mCategories.get(position).toLowerCase();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String categoryTitle = mCategories.get(position);
        int categoryDrawable = mDrawables.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.category_item, null);
        }

        TextView titleTextView = convertView.findViewById(R.id.categoryTitle);
        ImageView imageView = convertView.findViewById(R.id.categoryImage);

        titleTextView.setText(categoryTitle);
        imageView.setImageResource(categoryDrawable);

        return convertView;
    }
}
