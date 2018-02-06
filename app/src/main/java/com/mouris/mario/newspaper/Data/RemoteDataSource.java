package com.mouris.mario.newspaper.Data;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.mouris.mario.newspaper.Utils.ApiUtils;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource sInstance;

    private MutableLiveData<List<Article>> mArticlesLiveData;

    private RemoteDataSource() {
        mArticlesLiveData = new MutableLiveData<>();
    }

    public static RemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new RemoteDataSource();
        }
        return sInstance;
    }

    public LiveData<List<Article>> getHeadlineArticles() {
        return mArticlesLiveData;
    }

    @SuppressLint("StaticFieldLeak")
    public void loadHeadlineArticles() {

        new AsyncTask<Void,Void,List<Article>>() {
            @Override
            protected List<Article> doInBackground(Void... voids) {
                return ApiUtils.fetchHeadlineArticles();
            }

            @Override
            protected void onPostExecute(List<Article> articles) {
                mArticlesLiveData.setValue(articles);
            }
        }.execute();

    }

}
