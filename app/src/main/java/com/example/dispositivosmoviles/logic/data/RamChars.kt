package com.example.dispositivosmoviles.logic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class RamChars(
    val nombre: String,
    val estado: String,
    val especie: String,
    val ubicacion: String,
    val origen: String,
    val imagen: String,
    val episode: String
    ) : Parcelable
