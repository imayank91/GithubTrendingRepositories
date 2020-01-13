package com.app.gitrepositories.service

import com.app.gitrepositories.service.model.GithubAPIModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by mayank on October 11 2019
 */
interface APIService {

    @GET("/repositories")
    fun getTrendingRepos(): Call<List<GithubAPIModel>>

}