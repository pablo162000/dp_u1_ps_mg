package com.example.dispositivosmoviles.logic.ramLogic

import com.example.dispositivosmoviles.data.connections.ApiConnection
import com.example.dispositivosmoviles.data.endpoints.JikanEndpoint
import com.example.dispositivosmoviles.data.endpoints.RickAndMortyEndpoint
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import com.example.dispositivosmoviles.data.entities.marvel.characters.getMarvelChars
import com.example.dispositivosmoviles.data.entities.ram.database.RamCharsDB
import com.example.dispositivosmoviles.data.entities.ram.getRamChars
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars
import com.example.dispositivosmoviles.logic.data.getMarvelCharsDB
import com.example.dispositivosmoviles.logic.data.getRamCharsDB
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

    suspend fun getAllRamCharsDB(): List<RamChars> {
        val items: ArrayList<RamChars> = arrayListOf()
        val items_aux = DispositivosMoviles.getDbInstance().ramDao().getAllCharacters()
        items_aux.forEach {
            items.add(
                RamChars(
                    it.id,
                    it.nombre,
                it.estado,
            it.especie,
            it.ubicacion,
            it.origen,
            it.imagen,
            it.episode
                )
            )
        }
        return items
    }

    suspend fun insertRamCharstoDB(items: List<RamChars>){

        var itemsDB = arrayListOf<RamCharsDB>()


        items.forEach {
            itemsDB.add(it.getRamCharsDB())
        }

        DispositivosMoviles.getDbInstance().ramDao().insertRamChar(itemsDB)

    }


}