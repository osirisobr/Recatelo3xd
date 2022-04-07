package com.example.finalfilm.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.finalfilm.R


class ContactoFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {

       val view = inflater.inflate(R.layout.fragment_contacto, container, false)





        val tvCorreo = view.findViewById<TextView>(R.id.tvCorreo)
        val tvNumeroTelefono = view.findViewById<TextView>(R.id.tvNumeroTelefono)


        tvCorreo.setOnClickListener{
            val correo = "tel:academiarecatelo@gmail.com"
            val chooserTitle = "Academia Recatelo app"

            val uri = Uri.parse("mailto:$correo")
                .buildUpon()
                .build()

            val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
            startActivity(Intent.createChooser(emailIntent, chooserTitle))




        }


        tvNumeroTelefono.setOnClickListener{

            val telefono = "tel:982284187"
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(telefono)))


        }


        return view








    }


}