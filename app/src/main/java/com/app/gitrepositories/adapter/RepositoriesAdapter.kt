package com.app.gitrepositories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.gitrepositories.R
import com.app.gitrepositories.databinding.ListItemBinding
import com.app.gitrepositories.helpers.ChildClickListener
import com.app.gitrepositories.service.model.GithubAPIModel
import com.app.gitrepositories.viewholder.RepositoriesViewHolder

/**
 * Created by mayank on October 11 2019
 */
internal class RepositoriesAdapter(private val context: Context, private val childClickListener: ChildClickListener) : RecyclerView.Adapter<RepositoriesViewHolder>() {

    private var mutableRepositoriesList = mutableListOf<GithubAPIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        val trendingItemBinding: ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent,false)

        return RepositoriesViewHolder(trendingItemBinding)

    }

    override fun getItemCount(): Int {
        return mutableRepositoriesList.size
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val item = mutableRepositoriesList[position]
        holder.binding.model = item
        holder.binding.imageView = holder.binding.imageViewAvatar
        holder.binding.titleView = holder.binding.textviewName
        holder.binding.childClick = childClickListener
    }

     fun setRepositoriesList(reposList: MutableList<GithubAPIModel>) {
        this.mutableRepositoriesList = reposList
        notifyDataSetChanged()
    }

}