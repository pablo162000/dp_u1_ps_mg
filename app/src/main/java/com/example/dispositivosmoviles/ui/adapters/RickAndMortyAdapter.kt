package com.example.dispositivosmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ListadoramBinding
import com.example.dispositivosmoviles.databinding.MarvelCharactersBinding
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class RickAndMortyAdapter(
    private var fnClick: (RamChars) -> Unit,
    private var fnSave: (RamChars) -> Boolean
) :
    RecyclerView.Adapter<RickAndMortyAdapter.RamViewHolder>() {
    var items: List<RamChars> = listOf()

    class RamViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private var binding: ListadoramBinding = ListadoramBinding.bind(view)

        //conectamos el objeto con el layout
        fun render(
            item: RamChars, fnClick: (RamChars) -> Unit, fnSave: (RamChars) -> Boolean
        ) {

            //println("Recibiendo a ${item.name}")
            binding.txtNombre.text = item.nombre
            binding.txtEstado.text = item.estado
            binding.textEspecie.text = item.especie
            binding.textUbicacion.text = item.ubicacion
            binding.textOrigen.text = item.origen
            binding.textEpisode.text = item.episode

            Picasso.get().load(item.imagen).into(binding.imgRam)
            itemView.setOnClickListener {
                fnClick(item)
                Snackbar.make(
                    binding.imgRam,
                    item.nombre,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            binding.btnFav.setOnClickListener {
                var checkInsert: Boolean = false
                checkInsert = fnSave(item)
                if (checkInsert) {
                    Snackbar.make(
                        binding.imgRam,
                        "Se agrego a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()

                } else {
                    Snackbar.make(
                        binding.imgRam,
                        "No se puedo agregar  Ya esta agregado",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    //Los tres metodos se ejecutan cuando se ingresa un elemento de la lista

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyAdapter.RamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RamViewHolder(
            inflater.inflate(
                R.layout.listadoram,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RickAndMortyAdapter.RamViewHolder, position: Int) {
        holder.render(items[position], fnClick,fnSave)
    }

    override fun getItemCount(): Int = items.size

    fun updateListItems(newItems: List<RamChars>) {
        this.items = this.items.plus(newItems)
        notifyDataSetChanged()
    }

    fun replaceListItems(newItems: List<RamChars>) {
        this.items = newItems
        notifyDataSetChanged()
    }
}