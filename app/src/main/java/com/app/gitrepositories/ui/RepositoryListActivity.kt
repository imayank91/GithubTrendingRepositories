package com.app.gitrepositories.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gitrepositories.R
import com.app.gitrepositories.adapter.RepositoriesAdapter
import com.app.gitrepositories.databinding.ActivityRepoListBinding
import com.app.gitrepositories.helpers.ChildClickListener
import com.app.gitrepositories.service.model.GithubAPIModel
import com.app.gitrepositories.utils.AnimUtils
import com.app.gitrepositories.utils.AppUtils.isConnectedToNetwork
import com.app.gitrepositories.utils.StringContract
import com.app.gitrepositories.viewmodel.TrendingReposViewModel

internal class RepositoryListActivity : AppCompatActivity(), ChildClickListener {

    private lateinit var trendingReposViewModel: TrendingReposViewModel
    private lateinit var binding: ActivityRepoListBinding
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var repositoriesAdapter: RepositoriesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_repo_list
        )
        binding.noNetworkLayout.activity = this
        setSupportActionBar(binding.mainToolbar.toolbar)

        trendingReposViewModel = ViewModelProviders.of(this).get(TrendingReposViewModel::class.java)

        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        repositoriesAdapter = RepositoriesAdapter(applicationContext, this)
        binding.recyclerView.adapter = repositoriesAdapter

        trendingReposViewModel.repositoryList.observe(this, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    stopShimmer()
                    repositoriesAdapter.setRepositoriesList(it)
                    binding.recyclerView.scheduleLayoutAnimation()
                }
            }
        })

        handleLayout()
    }

    override fun onChildClick(imageView: View, titleView: View, githubAPIModel: GithubAPIModel) {
        openDetailsScreen(imageView, titleView, githubAPIModel)
    }

    private fun openDetailsScreen(
        imageView: View,
        titleView: View,
        githubAPIModel: GithubAPIModel
    ) {
        val intent = Intent(this, RepositoryDetailActivity::class.java).apply {
            putExtra(StringContract.GITHUB_PARCLEABLE_OBJECT, githubAPIModel)
        }
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, *AnimUtils.getTransitionElements(
                applicationContext, imageView, titleView
            )
        )
        startActivity(intent, options.toBundle())
    }

    fun retryFetching() {
        if (isConnectedToNetwork(applicationContext)) {
            binding.noNetworkLayout.rootView.visibility = View.GONE
            startShimmer()
            trendingReposViewModel.fetchRepositories()
        }
    }

    private fun startShimmer() {
        binding.loaderLayout.rootView.visibility = View.VISIBLE
        binding.loaderLayout.shimmerLayout.startShimmer()
    }

    private fun stopShimmer() {
        binding.noNetworkLayout.rootView.visibility = View.GONE
        binding.loaderLayout.shimmerLayout.stopShimmer()
        binding.loaderLayout.rootView.visibility = View.GONE
    }


    private fun handleLayout() {
        if(!isConnectedToNetwork(applicationContext)){
            trendingReposViewModel.fetchFromStore()
            binding.noNetworkLayout.rootView.visibility = View.VISIBLE
        }else{
            binding.noNetworkLayout.rootView.visibility = View.GONE
            trendingReposViewModel.fetchRepositories()
        }
    }

}
