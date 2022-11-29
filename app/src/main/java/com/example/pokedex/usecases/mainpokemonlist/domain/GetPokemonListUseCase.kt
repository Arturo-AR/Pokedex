package com.example.pokedex.usecases.mainpokemonlist.domain

import com.example.pokedex.usecases.mainpokemonlist.data.PokemonListRepository
import com.example.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonListRepository) {

    suspend operator fun invoke(id: Int): PokemonListResponse {
        return repository.getPokemonByGeneration(id = id)
    }
}