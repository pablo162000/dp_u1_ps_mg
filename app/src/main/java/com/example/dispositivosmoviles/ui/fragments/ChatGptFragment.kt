package com.example.dispositivosmoviles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dispositivosmoviles.databinding.FragmentChatGptBinding

class ChatGptFragment : Fragment() {
    private lateinit var binding: FragmentChatGptBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatGptBinding.inflate(layoutInflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }


}