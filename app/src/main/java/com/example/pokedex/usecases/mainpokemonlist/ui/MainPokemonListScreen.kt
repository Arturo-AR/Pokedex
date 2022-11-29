package com.example.pokedex.usecases.mainpokemonlist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.pokedex.R
import com.example.pokedex.core.navigation.PokedexScreens
import com.example.pokedex.usecases.mainpokemonlist.data.network.response.PokemonByRegion
import com.example.pokedex.usecases.mainpokemonlist.data.network.response.PokemonListResponse

@Composable
fun MainPokemonListScreen(
    viewModel: MainPokemonListViewModel,
    navController: NavHostController
) {
    val pokemonList by viewModel.pokemonList.observeAsState()
    val error by viewModel.error.observeAsState(initial = false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
            Color(
                0xFF507D92
            )
        )
    ) {
        Logo()
        Header(pokemonList, viewModel)
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
        painter = painterResource(id = R.drawable.pokemon_logo),
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
            LazyColumn() {
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
fun Header(pokemonList: PokemonListResponse?, viewModel: MainPokemonListViewModel) {
    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {

        Text(
            text = pokemonList?.regionName ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        var mExpanded by remember { mutableStateOf(false) }
        var mSelectedText by remember { mutableStateOf("") }
        var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Column(
            Modifier
                .padding(20.dp)
                .weight(3f)
        ) {
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        mTextFieldSize = coordinates.size.toSize()
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,

                ),
                label = { Text("Generacion") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                },
            )
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                items.forEach { id ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = id.toString()
                        mExpanded = false
                        viewModel.getPokemons(id)
                    }) {
                        Text(text = id.toString())
                    }
                }
            }
        }
    }
}
