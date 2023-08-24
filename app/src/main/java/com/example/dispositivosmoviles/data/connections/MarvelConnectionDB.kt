package com.example.dispositivosmoviles.data.connections

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dispositivosmoviles.data.dao.marvel.MarvelCharsDAO
import com.example.dispositivosmoviles.data.dao.marvel.RamCharsDAO
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import com.example.dispositivosmoviles.data.entities.ram.database.RamCharsDB

@Database(
    entities = [MarvelCharsBD::class,RamCharsDB::class],
    version = 1
)
abstract class MarvelConnectionDB : RoomDatabase() {

    abstract fun marvelDao(): MarvelCharsDAO

    abstract fun ramDao(): RamCharsDAO
}