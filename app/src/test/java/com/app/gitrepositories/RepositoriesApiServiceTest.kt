package com.app.gitrepositories

import com.app.gitrepositories.service.APIService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.BufferedSource
import okio.Okio
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * Created by mayank on October 12 2019
 */

class RepositoriesApiServiceTest {

    private lateinit var service: APIService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun initService() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        this.service = createService()
    }

    private fun createService(): APIService {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }


    @Throws(IOException::class)
    private fun enqueueResponse(fileName: String) {
        val inputStream = RepositoriesApiServiceTest::class.java.classLoader.getResourceAsStream(String.format("api-response/%s", fileName))
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody((source as BufferedSource).readString(
            StandardCharsets.UTF_8)))
    }

    @Test
    @Throws(IOException::class)
    fun fetchPostsTest() {
        enqueueResponse("github_repositories.json")
        val response = service.getTrendingRepos().execute()
        Assert.assertEquals(true, response.isSuccessful)
    }

}