package com.aranapp.pokedex.core.util

import java.util.*

//Function to capitalization the first character
fun String.initialLetterUpperCase(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}
