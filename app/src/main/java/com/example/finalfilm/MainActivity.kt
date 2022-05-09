package com.example.finalfilm

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.finalfilm.Fragments.ContactoFragment
import com.example.finalfilm.Fragments.rvFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private var administrador = true
    lateinit var ImageUri : Uri
    private lateinit var sTitulo:String
    private lateinit var sImage:String
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)


        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)





        abrirFragment(rvFragment())


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_novedades -> {Toast.makeText(this,"Novedades", Toast.LENGTH_SHORT).show()
                abrirFragment(rvFragment())

            }
            R.id.nav_item_contacto ->{Toast.makeText(this,"Contacto", Toast.LENGTH_SHORT).show()
                abrirFragment(ContactoFragment())

            }
            R.id.nav_item_comollegar -> {
                Toast.makeText(this, "Como llegar", Toast.LENGTH_SHORT).show()
                ponermap()


            }
            R.id.nav_item_fotos ->{
                if ( administrador == true   ){
                Toast.makeText(this,"Subir fotos", Toast.LENGTH_SHORT).show()

                    seleccionarImagen()




                        }else {

                    Toast.makeText(this, "No puedes subir fotos", Toast.LENGTH_SHORT).show()
                }


                        }




        }
       drawer.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
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
                     val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    // initialize byte stream
                     val stream = ByteArrayOutputStream()
                    // compress Bitmap
                     bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    // Initialize byte array
                     val bytes = stream.toByteArray()
                    // get base64 encoded string
                     sImage = Base64.encodeToString(bytes, Base64.DEFAULT)
                    // set encoded text on textview



                    MandarDatos(sImage)

                    // tvCodigo!!.text = sImage
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                //  ivFoto.setImageURI(ImageUri)
            }

        }}










    fun ponermap(){
       var ubicacion = "geo:43.006924040252656, -7.559487242896649?q="
       val gmmIntentUri =  Uri.parse(ubicacion + Uri.encode("academia recatelo"))
       val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
       mapIntent.setPackage("com.google.android.apps.maps")
       startActivity(mapIntent)

   }
    fun abrirFragment(fragment: Fragment)
    {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainerView, fragment)
        ft.commit()

    }







    private fun MandarDatos( foto : String){

        // Create a new user with a first and last name


        val user = hashMapOf(
            "Titulo" to "Foto Recatelo",
            "Code64" to foto
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                Toast.makeText(this@MainActivity, "Imagen subida", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
                Toast.makeText(this@MainActivity, "Imagen no subida", Toast.LENGTH_SHORT).show()

            }
        abrirFragment(rvFragment())

    }

}