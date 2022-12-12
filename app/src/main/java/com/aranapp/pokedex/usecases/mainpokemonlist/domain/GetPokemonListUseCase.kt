package com.aranapp.pokedex.usecases.mainpokemonlist.domain

import com.aranapp.pokedex.usecases.mainpokemonlist.data.PokemonListRepository
import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonListRepository) {

    suspend operator fun invoke(id: Int): PokemonListResponse {
        return repository.getPokemonByGeneration(id = id)
    }
}