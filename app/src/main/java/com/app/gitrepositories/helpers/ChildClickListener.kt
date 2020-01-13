package com.app.gitrepositories.helpers

import android.view.View
import com.app.gitrepositories.service.model.GithubAPIModel

/**
 * Created by mayank on October 11 2019
 */

interface ChildClickListener {
    fun onChildClick(imageView: View, titleView: View, githubAPIModel: GithubAPIModel)
}