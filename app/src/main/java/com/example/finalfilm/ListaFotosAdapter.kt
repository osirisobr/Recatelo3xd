package com.example.finalfilm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaFotosAdapter(val fotos: List<Fotos>, val context: Context ) :
    RecyclerView.Adapter<ListaFotosAdapter.FotosHolder>() {


    class FotosHolder  (view: View) : RecyclerView.ViewHolder(view){

        val tvTitulo = itemView.findViewById<TextView>(R.id.tvTitulo)
        val ivFoto = itemView.findViewById<TextView>(R.id.ivFoto)
        val context = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotosHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.item_fotos, parent, false)

        return FotosHolder(inflater)
    }

    override fun onBindViewHolder(holder: FotosHolder, position: Int) {



       // holder.tvTitulo.setText(fotos.toString())

    }

    override fun getItemCount(): Int {
        return 0
    }



}


