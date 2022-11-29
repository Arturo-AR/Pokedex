package com.example.pokedex.usecases.pokemondetail.data.network

import com.example.pokedex.usecases.pokemondetail.data.network.response.PokemonDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailService @Inject constructor(private val pokemonDetailClient: PokemonDetailClient) {

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return withContext(Dispatchers.IO) {
            val response = pokemonDetailClient.getPokemonDetail(name = name)
            if (response.body() == null) {
                throw Exception("Not Found")
            } else {
                response.body()!!
            }
        }
    }
}