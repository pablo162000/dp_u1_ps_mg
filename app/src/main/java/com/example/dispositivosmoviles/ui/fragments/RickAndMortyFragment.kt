package com.example.dispositivosmoviles.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityRickAndMortyBinding
import com.example.dispositivosmoviles.databinding.FragmentFirstBinding
import com.example.dispositivosmoviles.databinding.FragmentRickAndMortyBinding
import com.example.dispositivosmoviles.logic.Metodos
import com.example.dispositivosmoviles.logic.data.MarvelChars
import com.example.dispositivosmoviles.logic.data.RamChars
import com.example.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.example.dispositivosmoviles.logic.ramLogic.RamLogic
import com.example.dispositivosmoviles.ui.activities.DetailsMarvelItem
import com.example.dispositivosmoviles.ui.activities.RickAndMorty
import com.example.dispositivosmoviles.ui.activities.dataStore
import com.example.dispositivosmoviles.ui.adapters.MarvelAdapter
import com.example.dispositivosmoviles.ui.adapters.RickAndMortyAdapter
import com.example.dispositivosmoviles.ui.data.UserDataStore
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RickAndMortyFragment : Fragment() {
    private lateinit var binding: FragmentRickAndMortyBinding

    private lateinit var lmanager: CarouselLayoutManager
    private var rvAdapter: RickAndMortyAdapter =
        RickAndMortyAdapter({ sendRamItem(it) }, { saveRamItem(it) })
    private var page: Int = 1
    private var offset: Int = 0
    private val limit: Int = 99


    private var ramCharsItems: MutableList<RamChars> = mutableListOf<RamChars>()
    //private var marvelCharsItemsDB: MutableList<MarvelChars> = mutableListOf<MarvelChars>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRickAndMortyBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        lmanager = CarouselLayoutManager()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        chargeDataRVInit()
        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }

    }


    private fun sendRamItem(item: RamChars): Unit {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    private fun saveRamItem(item: RamChars): Boolean {

//        return if (item == null || marvelCharsItemsDB.contains(item)) {
//            false
//        } else {
//
//            lifecycleScope.launch(Dispatchers.Main) {
//                withContext(Dispatchers.IO) {
//                    MarvelLogic().insertMarvelCharstoDB(listOf(item))
//                    marvelCharsItemsDB = MarvelLogic().getAllMarvelChardDB().toMutableList()
//                }
//
//            }
//            true
//        }

        return false
    }


    fun chargeDataRV() {


        lifecycleScope.launch(Dispatchers.Main) {

            ramCharsItems = withContext(Dispatchers.IO) {


                return@withContext (RamLogic().getAllCharactersRam())
            } as MutableList<RamChars>


            rvAdapter.items = ramCharsItems

            binding.rvdatos.apply {
                this.adapter = rvAdapter
                this.layoutManager = lmanager
            }

        }
    }

//    fun updateDataRV(limit: Int, offset: Int) {
//
//        var items: List<MarvelChars> = listOf()
//        lifecycleScope.launch(Dispatchers.Main) {
//
//            items = withContext(Dispatchers.IO) {
//
//
//                return@withContext (MarvelLogic().getAllMarvelChars(offset, limit))
//            }
//
//
//            rvAdapter.updateListItems(items)
//
//            binding.rvMarvelChars.apply {
//                this.adapter = rvAdapter
//                this.layoutManager = gManager
//            }
//        }
//
//
//    }

//    fun updateAdapterRV() {
//        lifecycleScope.launch(Dispatchers.Main) {
//            binding.rvMarvelChars.apply {
//                this.adapter = rvAdapter
//                this.layoutManager = gManager
//            }
//        }
//
//    }

    fun chargeDataRVInit() {

        if (Metodos().isOnline(requireActivity())) {


            lifecycleScope.launch(Dispatchers.Main) {

                ramCharsItems = withContext(Dispatchers.IO) {


                    return@withContext (RamLogic().getAllCharactersRam())
                } as MutableList<RamChars>

                rvAdapter.items = ramCharsItems

                binding.rvdatos.apply {
                    this.adapter = rvAdapter
                    this.layoutManager = lmanager
                }


            }

        } else {
            Snackbar.make(
                binding.rvdatos,
                "No hay conexion",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }


//    override fun onResume() {
//        super.onResume()
//        lifecycleScope.launch(Dispatchers.Main) {
//            withContext(Dispatchers.IO) {
//                marvelCharsItemsDB = MarvelLogic().getAllMarvelChardDB().toMutableList()
//            }
//
//        }
//    }


//    private fun getDataStore() =
//
//        requireActivity().dataStore.data.map {
//            // si no me devuelve nada me devuelve vacio, no  devuelve valor nos devuelve una lista de valores siempre que lo ejecutamos
//                prefs ->
//
//            UserDataStore(
//                name = prefs[stringPreferencesKey("usuario")].orEmpty(),
//                email = prefs[stringPreferencesKey("email")].orEmpty(),
//                session = prefs[stringPreferencesKey("session")].orEmpty()
//
//
//            )
//
//        }


}


