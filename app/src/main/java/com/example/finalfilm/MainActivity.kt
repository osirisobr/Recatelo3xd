package com.example.finalfilm

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.example.finalfilm.Fragments.ComoLlegarFragment
import com.example.finalfilm.Fragments.ContactoFragment
import com.example.finalfilm.Fragments.NovedadesFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle



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
        

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_novedades -> {Toast.makeText(this,"Contacto", Toast.LENGTH_SHORT).show()
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragmentContainerView, NovedadesFragment())
                ft.commit()

            }
            R.id.nav_item_contacto ->{Toast.makeText(this,"Contacto", Toast.LENGTH_SHORT).show()
                val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                ft.replace(R.id.fragmentContainerView, ContactoFragment())
                ft.commit()

            }
            R.id.nav_item_comollegar -> {
                Toast.makeText(this, "Como llegar", Toast.LENGTH_SHORT).show()
                ponermap()

                //val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
                //ft.replace(R.id.fragmentContainerView, ComoLlegarFragment())
                //ft.commit()

            }}
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
   fun ponermap(){
       val gmmIntentUri = Uri.parse("geo:43.006924040252656, -7.559487242896649")
       val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
       mapIntent.setPackage("com.google.android.apps.maps")
       startActivity(mapIntent)




   }

}