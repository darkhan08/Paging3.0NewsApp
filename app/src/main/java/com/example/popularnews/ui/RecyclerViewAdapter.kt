package com.example.popularnews.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popularnews.data.entity.Article
import com.example.popularnews.databinding.ListItemBinding

class RecyclerViewAdapter :
    PagingDataAdapter<Article, RecyclerViewAdapter.NewsListViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        return NewsListViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val newsItem = getItem(position)
        newsItem?.let { holder.bind(it) }
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    class NewsListViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsList: Article) {
            binding.newsList = newsList
            binding.executePendingBindings()
        }
    }
}