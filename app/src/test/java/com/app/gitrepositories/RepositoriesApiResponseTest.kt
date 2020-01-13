package com.app.gitrepositories

import com.app.gitrepositories.service.APIService
import com.app.gitrepositories.utils.NetworkUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by mayank on October 12 2019
 */
class RepositoriesApiResponseTest {

    private lateinit var service: APIService

    @Before
    fun initService() {
        this.service = NetworkUtils.buildApiService()
    }

    @Test
    fun testFetchRepos() {

        val response = service.getTrendingRepos().execute()

        Assert.assertTrue(response.isSuccessful)
    }

}