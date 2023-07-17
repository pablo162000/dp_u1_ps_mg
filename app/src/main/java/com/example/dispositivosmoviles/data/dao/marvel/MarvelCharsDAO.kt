package com.example.dispositivosmoviles.data.dao.marvel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD

@Dao
interface MarvelCharsDAO {

    @Query("select * from MarvelCharsBD")
    fun getAllCharacters(): List<MarvelCharsBD>

    @Query("select * from MarvelCharsBD where id= :pk")
    fun getOneCharacter(pk: Int): MarvelCharsBD

    @Query("select * from MarvelCharsBD where name= :nombre")
    fun getOneCharacter(nombre: String): MarvelCharsBD

    @Insert
    fun insertMarvelChar(ch : List<MarvelCharsBD>)


}