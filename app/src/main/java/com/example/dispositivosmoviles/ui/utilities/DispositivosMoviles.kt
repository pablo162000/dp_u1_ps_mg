package com.example.dispositivosmoviles.ui.utilities

import android.app.Application
import androidx.room.Room
import com.example.dispositivosmoviles.data.connections.MarvelConnectionDB
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD

class DispositivosMoviles : Application() {

    //val name_class: String = "Admin"

    override fun onCreate() {
        super.onCreate()
        //conexion a la base de datos

        db = Room.databaseBuilder(
            applicationContext,
            MarvelConnectionDB::class.java,
            "marvelDB"
        ).build()

    }

    //objeto que se crea dentro de una clase
    companion object {

        private var db: MarvelConnectionDB? = null
        //val name_companion : String = "Admin"

        fun getDbInstance(): MarvelConnectionDB {
            //!! significa que no puede ser nulo
             return db!!
        }
    }

}