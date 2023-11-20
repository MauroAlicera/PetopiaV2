package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BuscadorServiciosMascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador_servicios_mascota)
        val btnFinalizarBuscarServiciosMascotas: Button = findViewById(R.id.botonFinalBuscarServiciosMascota)
        btnFinalizarBuscarServiciosMascotas.setOnClickListener()
        {
            val i = Intent(this,ServiciosMascotasMenu::class.java)
            startActivity(i)
        }
    }
}