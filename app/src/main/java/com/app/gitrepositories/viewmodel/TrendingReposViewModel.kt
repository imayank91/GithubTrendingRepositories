package com.app.gitrepositories.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.gitrepositories.service.model.GithubAPIModel
import com.app.gitrepositories.service.repository.TrendingRepository

/**
 * Created by mayank on October 11 2019
 */
class TrendingReposViewModel : ViewModel() {

    private val trendingRepository = TrendingRepository()
    var repositoryList = MutableLiveData<MutableList<GithubAPIModel>>()

    init {
        repositoryList = trendingRepository.repositoryList
    }

    fun fetchRepositories() {
        trendingRepository.getListofTrendingRepos()
    }

    fun fetchFromStore() {
        trendingRepository.fetchCachedRepos()
    }

}