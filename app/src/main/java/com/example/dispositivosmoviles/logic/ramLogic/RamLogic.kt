package com.example.dispositivosmoviles.logic.ramLogic

import com.example.dispositivosmoviles.data.connections.ApiConnection
import com.example.dispositivosmoviles.data.endpoints.JikanEndpoint
import com.example.dispositivosmoviles.data.endpoints.RickAndMortyEndpoint
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import com.example.dispositivosmoviles.data.entities.marvel.characters.getMarvelChars
import com.example.dispositivosmoviles.data.entities.ram.getRamChars
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars
import com.example.dispositivosmoviles.logic.data.getMarvelCharsDB
import com.example.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.example.dispositivosmoviles.ui.utilities.DispositivosMoviles
import java.lang.Exception
import java.lang.RuntimeException

class RamLogic {

    suspend fun getAllCharactersRam(): List<RamChars> {

        var itemList = arrayListOf<RamChars>()


        val response =
            ApiConnection.getService(
                ApiConnection.typeApi.RickAndMorty,
                RickAndMortyEndpoint::class.java
            ).getAllCharacters()

        if (response.isSuccessful) {
            response.body()!!.results  .forEach {


                val me = it.getRamChars()

                itemList.add(me)
            }
        }

        return itemList
    }


}