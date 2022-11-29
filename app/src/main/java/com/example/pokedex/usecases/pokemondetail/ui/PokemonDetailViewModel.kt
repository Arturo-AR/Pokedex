package com.example.pokedex.usecases.pokemondetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.usecases.pokemondetail.data.network.response.PokemonDetailResponse
import com.example.pokedex.usecases.pokemondetail.domain.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(private val getPokemonDetailUseCase: GetPokemonDetailUseCase) :
    ViewModel() {
    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse>()
    val pokemonDetail: LiveData<PokemonDetailResponse> = _pokemonDetail

    fun getPokemon(name: String) {
        viewModelScope.launch {
            try {
                _pokemonDetail.value = getPokemonDetailUseCase.invoke(name = name)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }
}