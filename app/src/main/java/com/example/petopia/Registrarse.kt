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

class Registrarse : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        val btnregistrar : Button = findViewById(R.id.btnRegistrarse)
        val btnregresar: Button = findViewById(R.id.btnRegresar)
        val txtNombre:TextView = findViewById(R.id.tituloRegistrarMascotaPerdida)
        val txtCorreo:TextView = findViewById(R.id.correoRegistro)
        val txtPassword:TextView = findViewById(R.id.contraRegistro)
        val txtPasswordConfirmed:TextView = findViewById(R.id.contraRegistroConfirmar)

        btnregistrar.setOnClickListener()
        {
            var pass1 = txtPassword.text.toString()
            var pass2 = txtPasswordConfirmed.text.toString()
            if(pass1.equals(pass2)){
                createAccount(txtCorreo.text.toString(), txtPassword.text.toString())
            }else{
                Toast.makeText(baseContext,"Error: Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
                txtPassword.requestFocus()
            }
        }

        btnregresar.setOnClickListener()
        {
            val j = Intent(this,MainActivity::class.java)
            startActivity(j)
        }

        firebaseAuth = Firebase.auth
    }

    private fun createAccount(email:String, password:String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext,"¡Usuario creado correctamente!, se requiere verificación",Toast.LENGTH_SHORT).show()
                    sendEmailVerification()
                }else{
                    Toast.makeText(baseContext,"Error: "+ task.exception,Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendEmailVerification(){
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){task ->
        }
    }

}