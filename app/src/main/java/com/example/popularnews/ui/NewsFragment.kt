package com.example.popularnews.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.popularnews.R
import com.example.popularnews.databinding.FragmentNewsBinding
import com.example.popularnews.utils.ARTICLE_TITLE
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class NewsFragment : Fragment(), SearchView.OnQueryTextListener {
    private val viewModel: NewsListViewModel by viewModel()
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter
        setUpAdapter(ARTICLE_TITLE)
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun setUpAdapter(query: String) {
        lifecycleScope.launch {
            viewModel.getNews(query).collect {
                adapter.submitData(it)
            }
        }
    }

    private fun search(query: String) {
        val searchText = "$query"
        lifecycleScope.launch {
            viewModel.getNews(searchText).collect {
                adapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.item_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null)
            search(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}