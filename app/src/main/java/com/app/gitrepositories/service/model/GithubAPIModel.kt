package com.app.gitrepositories.service.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by mayank on October 11 2019
 *
 * There’s an issue with Android Studio showing an error about an incomplete implementation of the Parcelable interface.
 * This is a known bug in the IDE itself and you can ignore it, there’s nothing wrong with the code and it works as expected
 */

@Parcelize
@Entity(tableName = "trendingRepos")
data class GithubAPIModel(
    @PrimaryKey var author: String, var name: String, var avatar: String,
    var description: String,
    var language: String?,
    var languageColor: String?, var stars: Int, var forks: Int, var currentPeriodStars: Int, var url: String
):Parcelable