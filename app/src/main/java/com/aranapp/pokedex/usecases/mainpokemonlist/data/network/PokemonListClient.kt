package com.aranapp.pokedex.usecases.mainpokemonlist.data.network

import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonListClient {
    @GET("pokedex/{id}")
    suspend fun getPokemonsByGeneration(
        @Path("id") id: Int
    ): Response<PokemonListResponse>

}