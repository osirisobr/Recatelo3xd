package com.example.finalfilm.RecyclerView.Adapter

import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfilm.R
import com.example.finalfilm.RecyclerView.Foto

class FotosAdapter(val fotos:List<Foto>):RecyclerView.Adapter<FotosAdapter.FotosHolder>(){


    lateinit var sImage: String
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotosHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return FotosHolder(layoutInflater.inflate(R.layout.item_foto,parent,false))
    }

    override fun onBindViewHolder(holder:FotosHolder, position: Int) {
       holder.render(fotos[position])
    }

    override fun getItemCount(): Int {
        return fotos.size
    }
    class FotosHolder(val view: View):RecyclerView.ViewHolder(view){

        fun render(fotos:Foto){

            var sImage: String
            var tvTitulo = view.findViewById<TextView>(R.id.tvTitulo)
            var ivFoto = view.findViewById<ImageView>(R.id.ivFotos)
            tvTitulo.text=fotos.titulo
            sImage = fotos.Image


            val bytes = Base64.decode(sImage, Base64.DEFAULT)
            // Initialize bitmap
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            // set bitmap on imageView
            ivFoto.setImageBitmap(bitmap)

            Toast.makeText(view.context, "Imagen decodificada", Toast.LENGTH_SHORT).show()



        }

    }






}