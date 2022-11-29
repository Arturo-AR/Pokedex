package com.example.pokedex.core.di

import com.example.pokedex.usecases.mainpokemonlist.data.network.PokemonListClient
import com.example.pokedex.usecases.pokemondetail.data.network.PokemonDetailClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonListClient(retrofit: Retrofit):PokemonListClient{
        return retrofit.create(PokemonListClient::class.java)
    }

    @Singleton
    @Provides
    fun providePokemonDetailClient(retrofit: Retrofit):PokemonDetailClient{
        return retrofit.create(PokemonDetailClient::class.java)
    }
}