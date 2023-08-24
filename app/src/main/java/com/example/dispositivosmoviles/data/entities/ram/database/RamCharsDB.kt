package com.example.dispositivosmoviles.data.entities.ram.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
class RamCharsDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val estado: String,
    val especie: String,
    val ubicacion: String,
    val origen: String,
    val imagen: String,
    val episode: String
) : Parcelable {

}