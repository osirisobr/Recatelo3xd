package com.example.finalfilm.RecyclerView.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfilm.R
import com.example.finalfilm.RecyclerView.Fotos

class FotosAdapter(val fotos:List<Fotos>):RecyclerView.Adapter<FotosAdapter.FotosHolder>(){





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

        fun render(fotos:Fotos){
           var tvTitulo = view.findViewById<TextView>(R.id.tvTitulo)
           var ivFoto = view.findViewById<ImageView>(R.id.ivFotos)
            tvTitulo.text=fotos.titulo
          //  ivFoto



        }

    }

}