package com.compa.ktorbasic.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Hero (
    val id: String,
    val name: String
)