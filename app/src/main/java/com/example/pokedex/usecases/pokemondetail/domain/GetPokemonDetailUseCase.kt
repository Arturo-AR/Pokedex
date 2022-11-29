package com.example.pokedex.usecases.pokemondetail.domain

import com.example.pokedex.usecases.pokemondetail.data.PokemonDetailRepository
import com.example.pokedex.usecases.pokemondetail.data.network.response.PokemonDetailResponse
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(private val repository: PokemonDetailRepository)  {
    suspend operator fun invoke(name: String): PokemonDetailResponse {
        return repository.getPokemonDetail(name = name)
    }
}