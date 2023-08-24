package com.example.dispositivosmoviles.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityDetailsMarvelItemBinding
import com.example.dispositivosmoviles.databinding.ActivityDetailsRamItemBinding
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars
import com.example.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.example.dispositivosmoviles.logic.ramLogic.RamLogic
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsRamItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsRamItemBinding
    private var ramCharsItemsDB: MutableList<RamChars> = mutableListOf<RamChars>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsRamItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val item = intent.getParcelableExtra<RamChars>("name")

        if (item != null) {
            binding.txtName.text = item.nombre
            binding.txtDescripcion.text = item.especie
            Picasso.get().load(item.imagen).into(binding.imgRam)
            binding.btnFavoritos.setOnClickListener {
                var checkInsert: Boolean = saveRamItem(
                    RamChars(
                        item.id,
                        item.nombre,
                        item.estado,
                        item.especie,
                        item.ubicacion,
                        item.origen,
                        item.imagen,
                        item.episode
                    )
                )
                if (checkInsert) {
                    Snackbar.make(
                        binding.imgRam,
                        "Se agrego a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()

                } else {
                    Snackbar.make(
                        binding.imgRam,
                        "No se puedo agregar o Ya esta agregado",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    private fun saveRamItem(item: RamChars): Boolean {

        return if (item == null || ramCharsItemsDB.contains(item)) {
            false
        } else {

            lifecycleScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    RamLogic().insertRamCharstoDB(listOf(item))
                    ramCharsItemsDB = RamLogic().getAllRamCharsDB().toMutableList()
                }

            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                ramCharsItemsDB = RamLogic().getAllRamCharsDB().toMutableList()
            }
        }
    }

}