package com.fiqri.moviemania.model

data class Movie(
    val id: Long,
    val name: String,
    val photoUrl: String,
    val desc: String,
    val genre: String,
    val runtime: String,
    val release: String
)