package com.aranapp.pokedex.usecases.mainpokemonlist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.aranapp.pokedex.R
import com.aranapp.pokedex.core.navigation.PokedexScreens
import com.aranapp.pokedex.core.util.initialLetterUpperCase
import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonByRegion
import com.aranapp.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse

@Composable
fun MainPokemonListScreen(
    viewModel: MainPokemonListViewModel,
    navController: NavHostController
) {
    val pokemonList by viewModel.pokemonList.observeAsState()
    //val error by viewModel.error.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
            Color(
                0xFF507D92
            )
        )
    ) {
        Logo()
        Header(pokemonList)
        Body(pokemonList, navController)
        Footer()
    }
}

@Composable
fun Logo() {
    Image(
        modifier = Modifier
            .height(120.dp)
            .padding(16.dp),
        painter = painterResource(id = R.drawable.info_dex),
        contentDescription = "PokemonLogo"
    )
}

@Composable
fun Footer() {

}

@Composable
fun Body(pokemonList: PokemonListResponse?, navController: NavController) {
    if (pokemonList?.pokemonList != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            LazyColumn {
                items(pokemonList.pokemonList) { pokemon ->
                    PokemonListItem(pokemon = pokemon, navController = navController)
                }
            }
        }
    }
}

@Composable
fun PokemonListItem(pokemon: PokemonByRegion, navController: NavController) {
    Card(modifier = Modifier
        .padding(bottom = 18.dp)
        .clickable {
            navController.navigate(
                PokedexScreens.PokemonDetail.createRoute(
                    pokemon.pokemon.pokemonName
                )
            )
        }) {
        Text(
            text = "${pokemon.pokedexNumber} - ${pokemon.pokemon.pokemonName}",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        )

    }


}

@Composable
fun Header(pokemonList: PokemonListResponse?) {

    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = pokemonList?.regionName?.initialLetterUpperCase() ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
