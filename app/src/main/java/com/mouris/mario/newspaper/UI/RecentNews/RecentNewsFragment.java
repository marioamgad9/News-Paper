package com.mouris.mario.newspaper.UI.RecentNews;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mouris.mario.newspaper.R;
import com.mouris.mario.newspaper.UI.ArticlesRVAdapter;
import com.mouris.mario.newspaper.Utils.NetworkUtils;

public class RecentNewsFragment extends Fragment {

    private RecentNewsViewModel mViewModel;

    public RecentNewsFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_news, container, false);

        mViewModel = ViewModelProviders.of(this).get(RecentNewsViewModel.class);

        SwipeRefreshLayout mSwipeRefreshLayout = rootView.findViewById(R.id.newsSwipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (NetworkUtils.isNetworkConnected(getContext())) {
                mViewModel.refreshHeadlineArticles();
            } else {
                //TODO: Show toast or SnackBar that there is no internet connection
            }
        });
        mViewModel.isLoading().observe(this, mSwipeRefreshLayout::setRefreshing);

        RecyclerView recyclerView = rootView.findViewById(R.id.recents_recyclerView);
        ArticlesRVAdapter adapter = new ArticlesRVAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mViewModel.getHeadlineArticles().observe(this, articleList -> {
            if (articleList.isEmpty()) {
                if (NetworkUtils.isNetworkConnected(getContext())) {
                    mViewModel.refreshHeadlineArticles();
                } else {
                    //TODO: Show message that app needs internet connection (Or show a placeholder)
                }
            }

            adapter.setArticlesList(articleList);
        });

        return rootView;
    }


}
