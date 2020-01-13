package com.app.gitrepositories

import android.app.Application
import androidx.room.Room
import com.app.gitrepositories.service.store.AppDatabase

/**
 * Created by mayank on October 11 2019
 */
class GithubApp : Application() {

    companion object {
        var database: AppDatabase? = null

    }
    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        database =  Room.databaseBuilder(applicationContext, AppDatabase::class.java, "github_db").fallbackToDestructiveMigration().build()
    }

}