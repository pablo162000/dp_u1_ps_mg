package com.example.dispositivosmoviles.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityRickAndMortyBinding
import com.example.dispositivosmoviles.databinding.FragmentRickAndMortyBinding
import com.example.dispositivosmoviles.ui.fragments.FirstFragment
import com.example.dispositivosmoviles.ui.fragments.RickAndMortyFragment
import com.example.dispositivosmoviles.ui.utilities.FragmentsManager

class RickAndMorty : AppCompatActivity() {

    private lateinit var binding: ActivityRickAndMortyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRickAndMortyBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        FragmentsManager().replaceFragmet(supportFragmentManager,
            binding.frmContainer.id, RickAndMortyFragment()
        )



    }
}