package com.app.gitrepositories.service.store

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.gitrepositories.service.model.GithubAPIModel

/**
 * Created by mayank on October 11 2019
 */

@Dao
interface RepositoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(reposList: List<GithubAPIModel>)

    @Query("SELECT * FROM trendingRepos")
    fun getAllRepos(): List<GithubAPIModel>

}