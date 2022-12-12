package com.aranapp.pokedex.usecases.pokemondetail.data.network

import com.aranapp.pokedex.usecases.pokemondetail.data.network.response.PokemonDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailClient {
    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): Response<PokemonDetailResponse>
}