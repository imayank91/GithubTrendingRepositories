package com.app.gitrepositories.service.store

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.gitrepositories.service.model.GithubAPIModel

/**
 * Created by mayank on October 11 2019
 */
@Database(entities = [(GithubAPIModel::class)], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repoDao(): RepositoriesDao
}