package com.mouris.mario.newspaper.Data.LocalDataSource;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mouris.mario.newspaper.Data.Article;

import java.util.List;

@Dao
public interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Article> articleList);

    @Query("SELECT * FROM Articles WHERE category = :category")
    LiveData<List<Article>> getArticles(String category);

    @Query("DELETE FROM Articles WHERE category = :category")
    void deleteArticles(String category);
}
