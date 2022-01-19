package com.barros9.beerappkmm.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Beer(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    @SerialName("image_url")
    val imageUrl: String? = ""
)
