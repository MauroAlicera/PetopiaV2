package com.example.petopia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ServiciosMascotasMenu : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccionPublicacion = db.collection("Publicación")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adapterPublicacion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios_mascotas_menu)
        val btnpublicarAviso : Button = findViewById(R.id.botonPublicarServicioMascota)
        val btnMenuMp3 : Button = findViewById(R.id.botonVolverMenuMP3)

        recyclerView = findViewById(R.id.listaRegistrosServicios)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = adapterPublicacion()
        recyclerView.adapter = adapter

        val btnConsultar : Button = findViewById(R.id.botonAvisosServiciosMascota)
        val btnPublicar : Button = findViewById(R.id.botonPublicarServicioMascota)

        consultarColeccion()

        btnConsultar.setOnClickListener()
        {
            //consultarColeccion()
            val i = Intent(this,BuscadorServiciosMascota::class.java)
            startActivity(i)
        }

        btnpublicarAviso.setOnClickListener()
        {
            val i = Intent(this,RegistrarSeviciosMascota::class.java)
            startActivity(i)
        }

        btnMenuMp3.setOnClickListener()
        {
            val j = Intent(this,MenuApp::class.java)
            startActivity(j)
        }
    }

    private fun consultarColeccion()
    {
        coleccionPublicacion.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val listaMutable = mutableListOf<Publicacion>()

                    // Obtener el QuerySnapshot
                    val querySnapshot = task.result

                    // Verificar si el QuerySnapshot no es nulo
                    querySnapshot?.forEach { document ->
                        // Obtener datos del documento
                        val descripcion = document.getString("Descripcion")
                        val fechaPublicacion = document.getTimestamp("FechaPublicacion")
                        val tipoPublicacion = document.getString("TipoPublicacion")
                        val titulo = document.getString("Titulo")
                        val ubicacion = document.getString("Ubicacion")
                        val usuarioPublica = document.getString("UsuarioPublica")

                        // Verificar que los datos no son nulos antes de agregar a la lista
                        if ((descripcion != null && fechaPublicacion != null && tipoPublicacion != null && titulo != null && ubicacion != null && usuarioPublica != null) && tipoPublicacion == "Servicio") {
                            val tuModelo = Publicacion(descripcion, fechaPublicacion, tipoPublicacion, titulo, ubicacion, usuarioPublica)
                            listaMutable.add(tuModelo)
                        }
                    }

                    // Configurar los datos en tu adaptador (asumiendo que 'adapter' es tu adaptador)
                    adapter.setDatos(listaMutable)
                } else {
                    // Manejar el caso en el que la operación no fue exitosa
                    // task.exception contiene información sobre el error
                }
            }

    }

}