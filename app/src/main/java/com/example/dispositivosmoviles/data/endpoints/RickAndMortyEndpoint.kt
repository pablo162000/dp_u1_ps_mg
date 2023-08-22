package com.example.dispositivosmoviles.data.endpoints

import com.example.dispositivosmoviles.data.entities.jikan.JikanAnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyEndpoint {

    @GET("character")
    suspend fun getAllCharacters( ) : Response<RamEntity>
}