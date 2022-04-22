package com.example.finalfilm.Fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.finalfilm.R
import java.io.ByteArrayOutputStream
import java.io.IOException

class FotosFragment : Fragment() {

    lateinit var ImageUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fotos, container, false)


seleccionarImagen()



    }

    private fun seleccionarImagen() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        resultLauncher.launch(intent)

    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            if (result.resultCode == AppCompatActivity.RESULT_OK){
                ImageUri = data?.data!!
                // when result is ok
                // initialize uri
                val uri = data.data
                // Initialize bitmap
                try {
                   // val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    // initialize byte stream
                   // val stream = ByteArrayOutputStream()
                    // compress Bitmap
                   // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    // Initialize byte array
                   // val bytes = stream.toByteArray()
                    // get base64 encoded string
                   // sImage = Base64.encodeToString(bytes, Base64.DEFAULT)
                    // set encoded text on textview
                    // tvCodigo!!.text = sImage
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //  ivFoto.setImageURI(ImageUri)
            }

        }}






}