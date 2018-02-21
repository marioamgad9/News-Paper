package com.mouris.mario.newspaper.UI.NewsDetailActivity;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mouris.mario.newspaper.Data.Article;
import com.mouris.mario.newspaper.Data.ArticlesRepository;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDao;
import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDatabase;

public class NewsDetailViewModel extends AndroidViewModel{

    private ArticlesRepository mRepository;

    public NewsDetailViewModel(@NonNull Application application) {
        super(application);

        ArticlesDao dao = ArticlesDatabase.getInstance(application).articlesDao();
        mRepository = ArticlesRepository.getInstance(application, dao);
    }

    LiveData<Article> getArticle(int id) {
        return mRepository.getArticleById(id);
    }

}
