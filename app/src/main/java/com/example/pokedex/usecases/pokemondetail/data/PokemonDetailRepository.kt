package com.example.pokedex.usecases.pokemondetail.data

import com.example.pokedex.usecases.pokemondetail.data.network.PokemonDetailService
import com.example.pokedex.usecases.pokemondetail.data.network.response.PokemonDetailResponse
import javax.inject.Inject

class PokemonDetailRepository @Inject constructor(private val api: PokemonDetailService) {

    suspend fun getPokemonDetail(name: String): PokemonDetailResponse {
        return api.getPokemonDetail(name = name)
    }
}