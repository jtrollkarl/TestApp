package com.example.testapp.data

data class SearchResult(
    val _embedded: Embedded,
    val _links: LinksX,
    val totalResults: Int
)