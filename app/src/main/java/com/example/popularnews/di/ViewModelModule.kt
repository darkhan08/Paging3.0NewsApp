package com.example.popularnews.di

import com.example.popularnews.ui.NewsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsListViewModel(get())
    }
}