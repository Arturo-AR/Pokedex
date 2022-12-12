package com.aranapp.pokedex.usecases.mainpokemonlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse
import com.aranapp.pokedex.usecases.mainpokemonlist.domain.GetPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPokemonListViewModel @Inject constructor(private val getPokemonsUseCase: GetPokemonListUseCase) :
    ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _pokemonList = MutableLiveData<PokemonListResponse>()
    val pokemonList: LiveData<PokemonListResponse> = _pokemonList

    init {
        getPokemons(1)
    }

    fun getPokemons(generationId: Int) {
        viewModelScope.launch {
            try {
                _pokemonList.value = getPokemonsUseCase.invoke(id = generationId)
                _error.value = false
            } catch (ex: Exception) {
                ex.printStackTrace()
                _error.value = true
            }
        }
    }
}