package com.mouris.mario.newspaper.UI;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.Data.RemoteDataSource;

import java.util.List;

public class RecentNewsViewModel extends ViewModel {

    private RemoteDataSource mRemoteDataSource;

    public RecentNewsViewModel() {
        mRemoteDataSource = RemoteDataSource.getInstance();
    }

    public LiveData<List<Article>> getHeadlineArticles() {
        loadHeadlineArticles();
        return mRemoteDataSource.getHeadlineArticles();
    }

    public void loadHeadlineArticles() {
        mRemoteDataSource.loadHeadlineArticles();
    }

}
