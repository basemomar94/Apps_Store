package com.bassem.appstore.data.models

data class Data(
    val hidden: Int,
    val limit: Int,
    val list: List<Details>,
    val next: Int,
    val offset: Int,
    val total: Int
)