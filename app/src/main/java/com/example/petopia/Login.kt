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

class Login : AppCompatActivity() {
    private lateinit var firebaseAuth:FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val btningresarlogin : Button = findViewById(R.id.iniciarSesion)
        val btnRecuperarContra : TextView = findViewById(R.id.btnRecuperarContra)
        val txtCorreo : TextView = findViewById(R.id.correoUsuario)
        val txtContra : TextView = findViewById(R.id.contraUsuario)
        val btnBack: Button = findViewById(R.id.btnBack)

        firebaseAuth = Firebase.auth
        btningresarlogin.setOnClickListener()
        {
            signIn(txtCorreo.text.toString(), txtContra.text.toString())
        }
        btnRecuperarContra.setOnClickListener() {
            val i = Intent(this, RecuperarContra::class.java)
            startActivity(i)
        }
        btnBack.setOnClickListener()
        {
            val j = Intent(this,MainActivity::class.java)
            startActivity(j)
        }
    }

    private fun signIn(email:String, password:String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {task ->
                if(task.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val verifica = user?.isEmailVerified
                    if(verifica == true){
                        Toast.makeText(baseContext, "Sesión Iniciada", Toast.LENGTH_SHORT).show()
                        //aquí vamos a ir a la segunda activity
                        val i = Intent(this, MenuApp::class.java)
                        startActivity(i)
                    }else{
                        Toast.makeText(baseContext, "¡Debe verificar su correo electrónico!", Toast.LENGTH_SHORT).show()
                    }

                }
                else {
                    Toast.makeText(baseContext, "Error de correo y/o contraseña", Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onBackPressed() {
        return
    }

}