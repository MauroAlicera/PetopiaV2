package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btningresar : Button= findViewById(R.id.iniciarsesion)
        val btnregistrarse : Button = findViewById(R.id.registrarse)
        btningresar.setOnClickListener()
        {
              val i = Intent(this,Login::class.java)
               startActivity(i)
        }
        btnregistrarse.setOnClickListener()
        {
            val j = Intent(this,Registrarse::class.java)
            startActivity(j)
        }
    }
    }
