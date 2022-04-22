package com.example.finalfilm.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfilm.R
import com.example.finalfilm.RecyclerView.Adapter.FotosAdapter
import com.example.finalfilm.RecyclerView.Fotos

class rvFragment : Fragment() {

    private val fotos = listOf(Fotos("Hola, Buenas tardes", null),Fotos("Hola, Buenas tardes", null))
    private lateinit var rvFotos: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rv, container, false)

        rvFotos = view.findViewById(R.id.rvFotos)
        initRecycler()

        return view
    }


    fun initRecycler() {
        rvFotos.layoutManager = LinearLayoutManager(activity)
        val adapter = FotosAdapter(fotos)
        rvFotos.adapter = adapter


    }
}
