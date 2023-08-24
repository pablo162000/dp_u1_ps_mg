package com.example.dispositivosmoviles.data.dao.marvel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import com.example.dispositivosmoviles.data.entities.ram.database.RamCharsDB

@Dao
interface RamCharsDAO {
    @Query("select * from RamCharsDB")
    fun getAllCharacters(): List<RamCharsDB>

    @Insert
    fun insertRamChar(ch: List<RamCharsDB>)
}