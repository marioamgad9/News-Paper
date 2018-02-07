package com.mouris.mario.newspaper.UI.RecentNews;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.Data.ArticlesRepository;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDao;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDatabase;

import java.util.List;

public class RecentNewsViewModel extends AndroidViewModel {

    private ArticlesRepository mRepository;

    public RecentNewsViewModel(@NonNull Application application) {
        super(application);

        ArticlesDao dao = ArticlesDatabase.getInstance(application).articlesDao();
        mRepository = mRepository.getInstance(dao);
    }

    public LiveData<List<Article>> getHeadlineArticles() {
        return mRepository.getHeadlineArticles();
    }

    public void refreshHeadlineArticles() {
        mRepository.refreshHeadlineArticles();
    }

}
