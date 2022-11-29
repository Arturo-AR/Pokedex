package com.example.pokedex.core.util

import androidx.compose.ui.graphics.Color

class TypeColors {

    private val fighting = Color(0xFFC0631C)
    private val poison = Color(0xFF433883)
    private val rock = Color(0xFF754848)
    private val dragon = Color(0xFF673AB7)
    private val dark = Color(0xFF36393A)
    private val normal = Color(0xFF7E7E7E)
    private val flying = Color(0xFFA9EEFF)
    private val ground = Color(0xFF926100)
    private val bug = Color(0xFF69C76E)
    private val ghost = Color(0xFF9F76FF)
    private val steel = Color(0xFF8A8A8A)
    private val fire = Color(0xFFFF0000)
    private val water = Color(0xFF0065FF)
    private val grass = Color(0xFF00FF21)
    private val electric = Color(0xFFF1EF18)
    private val psychic = Color(0xFFEA48F7)
    private val ice = Color(0xFF31A7FF)
    private val fairy = Color(0xFFA878B9)
    private val unknown = Color(0xFFA9EEFF)
    private val shadow = Color(0xFFA9EEFF)

    fun getColorByType(type: String): Color {
        return when (type) {
            "normal" -> normal
            "fighting" -> fighting
            "flying" -> flying
            "poison" -> poison
            "ground" -> ground
            "rock" -> rock
            "bug" -> bug
            "ghost" -> ghost
            "steel" -> steel
            "fire" -> fire
            "water" -> water
            "grass" -> grass
            "electric" -> electric
            "psychic" -> psychic
            "ice" -> ice
            "dragon" -> dragon
            "dark" -> dark
            "fairy" -> fairy
            "shadow" -> shadow
            else -> unknown
        }
    }

    fun getColorTextByType(type: String): Color {
        return when (type) {
            "poison",
            "rock",
            "water",
            "dragon",
            "fire",
            "ground",
            "dark",
            "fighting" -> Color.White
            else -> Color.Black
        }
    }
}