package com.example.finalfilm.Fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfilm.R
import com.example.finalfilm.RecyclerView.Adapter.FotosAdapter
import com.example.finalfilm.RecyclerView.Foto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class rvFragment : Fragment() {

    private val fotos = listOf(Foto("Hola, Buenas tardes", ""),Foto("Hola, Buenas tardes", ""))
    private lateinit var rvFotos: RecyclerView
    private lateinit var Titulo: String
    val db = Firebase.firestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rv, container, false)

        rvFotos = view.findViewById(R.id.rvFotos)
       //initRecycler(fotos)
        descargarDatos()
        return view
    }


    fun initRecycler(fotos:List<Foto>) {
        rvFotos.layoutManager = LinearLayoutManager(activity)
        val adapter = FotosAdapter(fotos)
        rvFotos.adapter = adapter


    }


    fun descargarDatos(){

        val listaFotos = ArrayList<Foto>()


        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    listaFotos.add(Foto(document.data.get("Titulo").toString(),document.data.get("Code64").toString()))
                    initRecycler(listaFotos)

                    Titulo = document.data.get("Titulo").toString()
                    Toast.makeText(activity,Titulo, Toast.LENGTH_SHORT).show()

                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }





    }
}
