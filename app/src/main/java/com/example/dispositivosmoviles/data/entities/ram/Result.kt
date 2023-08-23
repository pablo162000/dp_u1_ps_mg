package com.example.dispositivosmoviles.data.entities.ram

import com.example.dispositivosmoviles.data.entities.ram.Result
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)


fun Result.getRamChars(): RamChars {


    val a = RamChars(
        name,status,species,location.name,origin.name,image,gender)


    return a
}