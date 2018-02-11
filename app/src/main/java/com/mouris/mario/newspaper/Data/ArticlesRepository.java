package com.mouris.mario.newspaper.Data;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.mouris.mario.newspaper.Data.LocalDataSource.ArticlesDao;
import com.mouris.mario.newspaper.Utils.ApiUtils;

import java.util.List;

public class ArticlesRepository {

    private ArticlesDao mArticlesDao;

    private MutableLiveData<Boolean> mIsLoading;

    private static ArticlesRepository sInstance;

    public static ArticlesRepository getInstance(ArticlesDao articlesDao) {
        if (sInstance == null) {
            sInstance = new ArticlesRepository(articlesDao);
        }
        return sInstance;
    }

    private ArticlesRepository(ArticlesDao articlesDao) {
        mArticlesDao = articlesDao;
        mIsLoading = new MutableLiveData<>();
        mIsLoading.setValue(false);
    }

    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    public LiveData<List<Article>> getArticles(String category) {
        return mArticlesDao.getArticles(category);
    }

    @SuppressLint("StaticFieldLeak")
    private void insertArticles(List<Article> articlesList) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mArticlesDao.insert(articlesList);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteArticles(String category) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                mArticlesDao.deleteArticles(category);
                return null;
            }
        }.execute();
    }


    /* Remote data source code */

    //This method gets the new articles, deletes the old ones, and insert the new ones instead.
    @SuppressLint("StaticFieldLeak")
    public void loadHeadlineArticlesFromApi(String category) {
        //Start loading
        mIsLoading.setValue(true);

        new AsyncTask<Void,Void,List<Article>>() {
            @Override
            protected List<Article> doInBackground(Void... voids) {
                return ApiUtils.fetchHeadlineArticles(category);
            }

            @Override
            protected void onPostExecute(List<Article> articles) {
                //Finish loading
                mIsLoading.setValue(false);
                deleteArticles(category);
                insertArticles(articles);
            }
        }.execute();

    }

}
