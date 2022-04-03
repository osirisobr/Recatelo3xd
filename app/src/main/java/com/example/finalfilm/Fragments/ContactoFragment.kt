package com.example.finalfilm.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.finalfilm.R


class ContactoFragment : Fragment() {

    lateinit var ibLlamar:ImageButton
    lateinit var tvNumeroTelefono:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {

       val view = inflater.inflate(R.layout.fragment_contacto, container, false)





        val ibLlamar = view.findViewById<ImageButton>(R.id.IbLlamar)
//


        ibLlamar.setOnClickListener {

            val telefono = "tel:982284187"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))

        }
        return view








    }


}