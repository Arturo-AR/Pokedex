package com.aranapp.pokedex.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aranapp.pokedex.usecases.mainpokemonlist.ui.MainPokemonListScreen
import com.aranapp.pokedex.usecases.mainpokemonlist.ui.MainPokemonListViewModel
import com.aranapp.pokedex.usecases.pokemondetail.ui.PokemonDetailScreen
import com.aranapp.pokedex.usecases.pokemondetail.ui.PokemonDetailViewModel

@Composable
fun PokedexNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = PokedexScreens.MainPokemonList.route
    ) {
        composable(
            PokedexScreens.PokemonDetail.route,
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val mainPokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
            PokemonDetailScreen(
                viewModel = mainPokemonDetailViewModel,
                name = backStackEntry.arguments?.getString("name") ?: ""
            )
        }
        composable(PokedexScreens.MainPokemonList.route) {
            val mainPokemonListViewModel = hiltViewModel<MainPokemonListViewModel>()
            MainPokemonListScreen(
                navController = navController,
                viewModel = mainPokemonListViewModel
            )
        }
    }
}