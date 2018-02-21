package com.mouris.mario.newspaper.UI.NewsFragment;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.Data.ArticlesRepository;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDao;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDatabase;

import java.util.List;

class NewsViewModel extends AndroidViewModel {

    private ArticlesRepository mRepository;

    NewsViewModel(@NonNull Application application) {
        super(application);

        ArticlesDao dao = ArticlesDatabase.getInstance(application).articlesDao();
        mRepository = ArticlesRepository.getInstance(application, dao);
    }

    LiveData<List<Article>> getArticles(String category) {
        return mRepository.getArticles(category);
    }

    void refreshHeadlineArticles(String category) {
        mRepository.loadHeadlineArticlesFromApi(category);
    }

    LiveData<Boolean> isLoading() {
        return mRepository.isLoading();
    }

}
