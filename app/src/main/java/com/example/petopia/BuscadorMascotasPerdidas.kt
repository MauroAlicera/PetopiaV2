package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BuscadorMascotasPerdidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador_mascotas_perdidas)
        val btnFinalizarBuscarMascotasPerdidas: Button = findViewById(R.id.botonFinalBuscadorMascotasPerdidas)
        btnFinalizarBuscarMascotasPerdidas.setOnClickListener()
        {
            val i = Intent(this,MascotasPerdidasMenu::class.java)
            startActivity(i)
        }
    }
}