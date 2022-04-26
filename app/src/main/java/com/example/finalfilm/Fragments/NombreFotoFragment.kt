package com.example.finalfilm.Fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.finalfilm.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream
import java.io.IOException
import android.content.res.Configuration
import android.view.MenuItem
import androidx.fragment.app.FragmentTransaction
import com.example.finalfilm.Fragments.ContactoFragment
import com.example.finalfilm.Fragments.NombreFotoFragment
import com.example.finalfilm.Fragments.rvFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.ktx.firestore


class NombreFotoFragment : Fragment() {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var administrador = true
    lateinit var ImageUri : Uri
    private lateinit var sTitulo:String
    private lateinit var sImage:String
    val db = Firebase.firestore



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nombre_foto, container, false)






        return view
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
                   // val bitmap = MediaStore.Images.Media.getBitmap(contentRes, uri)
                    // initialize byte stream
                    val stream = ByteArrayOutputStream()
                    // compress Bitmap
                   // bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    // Initialize byte array
                    val bytes = stream.toByteArray()
                    // get base64 encoded string
                    sImage = Base64.encodeToString(bytes, Base64.DEFAULT)
                    // set encoded text on textview



                    MandarDatos(sImage,sTitulo)

                    // tvCodigo!!.text = sImage
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //  ivFoto.setImageURI(ImageUri)
            }

        }}
    private fun MandarDatos( foto : String, titulo : String){

        // Create a new user with a first and last name
        val user = hashMapOf(
            "Titulo" to titulo,
            "Code64" to foto
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(activity, "Imagen subida", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
                Toast.makeText(activity, "Imagen no subida", Toast.LENGTH_SHORT).show()

            }

    }


}