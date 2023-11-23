package com.fiqri.moviemania.data

import com.fiqri.moviemania.model.MoviesData
import com.fiqri.moviemania.model.OrderMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MovieRepository {

    private val orderMovie = mutableListOf<OrderMovie>()

    init {
        if (orderMovie.isEmpty()) {
            MoviesData.movies.forEach {
                orderMovie.add(OrderMovie(it, 0))
            }
        }
    }

    fun getAllMovies(): Flow<List<OrderMovie>> {
        return flowOf(orderMovie)
    }

    fun getOrderMovieById(movieId: Long): OrderMovie {
        return orderMovie.first {
            it.movie.id == movieId
        }
    }

    fun searchMovie(query: String): Flow<List<OrderMovie>>{
        return flowOf(orderMovie.filter{
            it.movie.name.contains(query, ignoreCase = true)
        })
    }

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(): MovieRepository =
            instance ?: synchronized(this) {
                MovieRepository().apply {
                    instance = this
                }
            }
    }
}