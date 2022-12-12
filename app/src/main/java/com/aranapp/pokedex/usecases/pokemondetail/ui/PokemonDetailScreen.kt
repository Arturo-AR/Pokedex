package com.aranapp.pokedex.usecases.pokemondetail.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.aranapp.pokedex.core.util.TypeColors
import com.aranapp.pokedex.core.util.initialLetterUpperCase

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
    name: String
) {
    viewModel.getPokemon(name)
    val pokemonDetail by viewModel.pokemonDetail.observeAsState()
    val typeColors = TypeColors()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        typeColors.getColorByType(
                            pokemonDetail?.types?.get(0)?.type?.name ?: ""
                        ),
                        Color.Black
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Color.White
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))
            if (pokemonDetail != null) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = name.initialLetterUpperCase(),
                    fontSize = 32.sp,
                )
                Row {
                    PokemonTypeText(pokemonType = pokemonDetail?.types?.get(0)?.type?.name ?: "")
                    if (pokemonDetail?.types?.size!! > 1) {
                        Spacer(modifier = Modifier.width(16.dp))
                        PokemonTypeText(
                            pokemonType = pokemonDetail?.types?.get(1)?.type?.name ?: ""
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(pokemonDetail!!.stats) { stats ->
                        PokemonStatsDetails(
                            stat = stats.stat.name,
                            statValue = stats.baseStat.toString()
                        )
                    }
                }
            } else {
                CircularProgressIndicator()
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.Center,
            painter = rememberAsyncImagePainter(
                model = pokemonDetail?.sprites?.others?.officialArtwork?.frontDefault
            ),
            contentDescription = null
        )
    }
}

@Composable
fun PokemonTypeText(
    pokemonType: String
) {
    val typeColors = TypeColors()
    Text(
        text = pokemonType.initialLetterUpperCase(),
        Modifier
            .clip(RoundedCornerShape(40.dp))
            .background(
                typeColors.getColorByType(pokemonType)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
        textAlign = TextAlign.Center,
        style = TextStyle(
            color = typeColors.getColorTextByType(pokemonType)
        ),
        fontSize = 22.sp
    )
}

@Composable
fun PokemonStatsDetails(
    stat: String,
    statValue: String
) {
    Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(text = "${stat.initialLetterUpperCase()}: ", textAlign = TextAlign.Start, fontSize = 22.sp, color = Color.Black)
        Text(text = statValue, fontSize = 22.sp, color = Color.Black)
    }
}