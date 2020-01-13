package com.app.gitrepositories.service.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.gitrepositories.GithubApp
import com.app.gitrepositories.service.APIService
import com.app.gitrepositories.service.model.GithubAPIModel
import com.app.gitrepositories.utils.NetworkUtils.buildApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
 * Created by mayank on October 11 2019
 */
class TrendingRepository {

    private val TAG = TrendingRepository::class.qualifiedName

    private var apiService: APIService = buildApiService()

    var repositoryList = MutableLiveData<MutableList<GithubAPIModel>>()

    private var mutablerepositoryList = mutableListOf<GithubAPIModel>()

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    private val scope = CoroutineScope(coroutineContext)


    fun getListofTrendingRepos() {
        apiService.getTrendingRepos().enqueue(object : Callback<List<GithubAPIModel>> {
            override fun onFailure(call: Call<List<GithubAPIModel>>, t: Throwable) {
                Log.d(TAG, "onFailure")
            }

            override fun onResponse(
                call: Call<List<GithubAPIModel>>,
                response: Response<List<GithubAPIModel>>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        response.body()?.let {
                            mutablerepositoryList.addAll(response.body()!!)
                            repositoryList.value = mutablerepositoryList
                            scope.launch {
                                    GithubApp.database!!.repoDao().insertRepos(mutablerepositoryList)
                            }

                        }
                    }
                }
            }

        })
    }


    fun fetchCachedRepos() {
        scope.launch {
            val reposList = GithubApp.database!!.repoDao().getAllRepos()
            scope.launch (Dispatchers.Main){
                mutablerepositoryList.addAll(reposList)
                repositoryList.value = mutablerepositoryList
            }
        }
    }

}