package com.mouris.mario.newspaper.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mouris.mario.newspaper.R;

public class RecentNewsFragment extends Fragment {

    private RecentNewsViewModel mViewModel;

    public RecentNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_news, container, false);

        mViewModel = ViewModelProviders.of(this).get(RecentNewsViewModel.class);

        RecyclerView recyclerView = rootView.findViewById(R.id.recents_recyclerView);
        ArticlesRVAdapter adapter = new ArticlesRVAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mViewModel.getHeadlineArticles().observe(this, adapter::setArticlesList);

        return rootView;
    }


}
