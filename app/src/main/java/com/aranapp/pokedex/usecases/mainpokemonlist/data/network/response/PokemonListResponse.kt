package com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("name") val regionName: String,
    @SerializedName("pokemon_entries") val pokemonList: List<PokemonByRegion>
)

data class PokemonByRegion(
    @SerializedName("entry_number") val pokedexNumber: Int,
    @SerializedName("pokemon_species") val pokemon: PokemonDetail
)

data class PokemonDetail(
    @SerializedName("name") val pokemonName: String,
    @SerializedName("url") val pokemonUrl: String
)