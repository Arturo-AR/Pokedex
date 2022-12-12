package com.aranapp.pokedex.usecases.mainpokemonlist.data

import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.PokemonListService
import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import javax.inject.Inject

class PokemonListRepository @Inject constructor(private val api: PokemonListService) {

    suspend fun getPokemonByGeneration(id: Int): PokemonListResponse {
        return api.getPokemonByGeneration(id = id)
    }
}