package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BuscadorMascotasAdopcion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador_mascotas_adopcion)
        val btnFinalizarBuscarAdopcion: Button = findViewById(R.id.botonFinalBuscarAdopcion)
        btnFinalizarBuscarAdopcion.setOnClickListener()
        {
            val i = Intent(this,AdopcionMascotasMenu::class.java)
            startActivity(i)
        }
    }
}