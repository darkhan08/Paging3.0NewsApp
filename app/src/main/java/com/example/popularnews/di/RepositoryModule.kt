package com.example.popularnews.di

import com.example.popularnews.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get()) }
}