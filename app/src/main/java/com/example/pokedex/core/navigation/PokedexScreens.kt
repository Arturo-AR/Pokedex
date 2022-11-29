package com.example.pokedex.core.navigation

sealed class PokedexScreens(
    val route: String
) {
    object MainPokemonList : PokedexScreens(
        route = "mainPokedexList"
    )

    object PokemonDetail : PokedexScreens(
        route = "pokedexDetail/{name}"
    ) {
        fun createRoute(name: String) = "pokedexDetail/$name"
    }
}
