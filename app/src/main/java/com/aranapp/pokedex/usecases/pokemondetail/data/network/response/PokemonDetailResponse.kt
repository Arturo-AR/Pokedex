package com.aranapp.pokedex.usecases.pokemondetail.data.network.response

import com.aranapp.pokedex.core.generalmodels.DataObject
import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    @SerializedName("height") val height: String,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("stats") val stats: List<PokemonStats>,
    @SerializedName("types") val types: List<PokemonTypes>,
)

data class PokemonTypes(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: DataObject,
)

data class PokemonStats(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: DataObject
)

data class PokemonSprites(
    @SerializedName("other") val others: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("official-artwork") val officialArtwork: FrontDefault
)

data class FrontDefault(
    @SerializedName("front_default") val frontDefault: String
)
