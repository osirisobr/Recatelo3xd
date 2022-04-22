package com.example.finalfilm.Fragments

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

    val fotos = listOf(Fotos("hola que lo que","fsadfdsfsfsfs"))
    lateinit var rvFotos : RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_rv, container, false)

        rvFotos = view?.findViewById(R.id.rvFotos)!!
        val context = this
        initRecycler()

    }


    fun initRecycler(){
        rvFotos.layoutManager = LinearLayoutManager(context)
        val adapter = FotosAdapter(fotos)
        rvFotos.adapter = adapter


    }
    }
