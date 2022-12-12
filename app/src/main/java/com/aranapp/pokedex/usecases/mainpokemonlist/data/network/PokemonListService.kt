package com.aranapp.pokedex.usecases.mainpokemonlist.data.network

import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonListService @Inject constructor(private val pokemonListClient: PokemonListClient) {

    suspend fun getPokemonByGeneration(id: Int): PokemonListResponse {
        return withContext(Dispatchers.IO) {
            val response = pokemonListClient.getPokemonsByGeneration(id = id)
            if (response.body() == null) {
                throw Exception("Not Found")
            } else {
                response.body()!!
            }
        }
    }
}