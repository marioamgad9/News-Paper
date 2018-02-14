package com.mouris.mario.newspaper.UI.NewsFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.R;
import com.mouris.mario.newspaper.UI.NewsDetailActivity.NewsDetail;
import com.mouris.mario.newspaper.Utils.NetworkUtils;

public class NewsFragment extends Fragment implements ArticlesRVAdapter.OnItemClickListener {

    public static final String CATEGORY_EXTRA_KEY = "category_key";

    private NewsViewModel mViewModel;

    private String category = null;

    public NewsFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        //Get the category if existent
        Intent thisIntent = getActivity().getIntent();
        if (thisIntent.hasExtra(CATEGORY_EXTRA_KEY)) {
            category = thisIntent.getStringExtra(CATEGORY_EXTRA_KEY);
            getActivity().setTitle(category.substring(0, 1).toUpperCase() + category.substring(1));
        } else {
            category = Article.RECENT_CATEGORY;
        }

        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        SwipeRefreshLayout mSwipeRefreshLayout = rootView.findViewById(R.id.newsSwipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            if (NetworkUtils.isNetworkConnected(getContext())) {
                mViewModel.refreshHeadlineArticles(category);
            } else {
                //TODO: Show toast or SnackBar that there is no internet connection
            }
        });
        mViewModel.isLoading().observe(this, mSwipeRefreshLayout::setRefreshing);

        RecyclerView recyclerView = rootView.findViewById(R.id.recents_recyclerView);
        ArticlesRVAdapter adapter = new ArticlesRVAdapter(null, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mViewModel.getArticles(category).observe(this, articleList -> {
            if (articleList.isEmpty()) {
                if (NetworkUtils.isNetworkConnected(getContext())) {
                    mViewModel.refreshHeadlineArticles(category);
                } else {
                    //TODO: Show message that app needs internet connection (Or show a placeholder)
                }
            }

            adapter.setArticlesList(articleList);
        });

        return rootView;
    }


    @Override
    public void onItemClick(Article article, ImageView imageView, TextView titleTextView) {
        Intent intent = new Intent(getContext(), NewsDetail.class);
        intent.putExtra(NewsDetail.ARTICLE_ID_KEY, article.id);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageView, "picture");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
