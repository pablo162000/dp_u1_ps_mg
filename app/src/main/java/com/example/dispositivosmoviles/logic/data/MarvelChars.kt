package com.example.dispositivosmoviles.logic.data

import android.os.Parcelable
import com.example.dispositivosmoviles.data.entities.marvel.characters.database.MarvelCharsBD
import kotlinx.parcelize.Parcelize

//Este plugin me da implementando los metodos para la clase
@Parcelize
data class MarvelChars(
    val id: Int,
    val name: String,
    val comic: String,
    val image: String
) : Parcelable

fun MarvelChars.getMarvelCharsDB(): MarvelCharsBD {
    return MarvelCharsBD(
        id, name, comic, image
    )
}