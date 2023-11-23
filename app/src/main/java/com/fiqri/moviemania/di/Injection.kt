package com.fiqri.moviemania.di

import com.fiqri.moviemania.data.MovieRepository

object Injection {
    fun provideRepository(): MovieRepository {
        return MovieRepository.getInstance()
    }
}