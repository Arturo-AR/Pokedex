package com.example.pokedex.core.generalmodels

import com.google.gson.annotations.SerializedName

data class DataObject(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)
