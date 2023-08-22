package com.example.dispositivosmoviles.data.connections

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiConnection {

    enum class typeApi {
        Jikan, Marvel, RickAndMorty
    }

    private val API_JIKAN = "https://api.jikan.moe/v4/"
    private val API_MARVEL = "https://gateway.marvel.com/v1/public/"

    private val API_RAM= "https://rickandmortyapi.com/api/"

    private fun getConnnection(base: String): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    suspend fun <T, E : Enum<E>> getService(api: E, service: Class<T>): T {
        var BASE = ""
        when (api.name) {
            typeApi.Jikan.name -> {

                BASE = API_JIKAN

            }

            typeApi.Marvel.name -> {
                BASE = API_MARVEL


            }

            typeApi.RickAndMorty.name -> {
                BASE = API_RAM
            }


        }
        return getConnnection(BASE).create(service)
    }
}