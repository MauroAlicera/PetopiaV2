package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import org.checkerframework.common.subtyping.qual.Bottom

class RecuperarContra : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contra)

        val txtCorreo:TextView = findViewById(R.id.correoRecuperarContra)
        val btnCambiar:Button = findViewById(R.id.btnRecuperarContra)
        val btnBack2: Button = findViewById(R.id.btnBack2)

        btnCambiar.setOnClickListener(){
            sendPasswordReset(txtCorreo.text.toString())
        }

        btnBack2.setOnClickListener()
        {
            val j = Intent(this,MainActivity::class.java)
            startActivity(j)
        }

        firebaseAuth = Firebase.auth
    }

    private fun sendPasswordReset(email:String){
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(){task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext, "¡Correo enviado!", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(baseContext, "¡Error al realizar acción!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
