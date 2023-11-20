package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class MenuApp : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_app)

        val btnBuscadorMascotas : Button = findViewById(R.id.botonMascotasPerdidas)
        val btnAdopcion : Button = findViewById(R.id.botonAdopcionDeMascotas)
        val btnServicios : Button= findViewById(R.id.botonServiciosMascotas)
        val btnCerrarSesion : Button = findViewById(R.id.btnCerrarSesión)

        btnBuscadorMascotas.setOnClickListener()
        {
            val i = Intent(this,MascotasPerdidasMenu::class.java)
            startActivity(i)
        }
        btnAdopcion.setOnClickListener()
        {
            val j = Intent(this,AdopcionMascotasMenu::class.java)
            startActivity(j)
        }
        btnServicios.setOnClickListener()
        {
            val m = Intent(this,ServiciosMascotasMenu::class.java)
            startActivity(m)
        }

        btnCerrarSesion.setOnClickListener(){
            signOut()
        }

        firebaseAuth = Firebase.auth
    }

    private fun signOut(){
        firebaseAuth.signOut()
        Toast.makeText(baseContext, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        val i = Intent(this, Login::class.java)
        startActivity(i)
    }

    override fun onBackPressed() {
        return
    }
}