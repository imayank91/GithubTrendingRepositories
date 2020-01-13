package com.app.gitrepositories.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.gitrepositories.R
import com.app.gitrepositories.databinding.ActivityRepoDetailBinding
import com.app.gitrepositories.service.model.GithubAPIModel
import com.app.gitrepositories.utils.AppUtils
import com.app.gitrepositories.utils.StringContract

/**
 * Created by mayank on October 11 2019
 */
internal class RepositoryDetailActivity :AppCompatActivity() {

    private lateinit var binding: ActivityRepoDetailBinding
    private lateinit var githubModel: GithubAPIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_repo_detail
        )
        githubModel = intent.getParcelableExtra(StringContract.GITHUB_PARCLEABLE_OBJECT)
        binding.model = githubModel
        binding.activity = this
        githubModel.languageColor?.let {

            val drawable = resources.getDrawable(R.drawable.ic_circle) as GradientDrawable
            drawable.setColor(Color.parseColor(githubModel.languageColor))
            binding.itemImgLanguage.setImageDrawable(drawable)
        }

    }

    fun visitSite(activity: RepositoryDetailActivity, url: String) {
        AppUtils.openBrowser(activity, url)
    }
}