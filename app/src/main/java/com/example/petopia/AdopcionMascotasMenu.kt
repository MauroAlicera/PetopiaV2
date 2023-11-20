package com.example.petopia
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class AdopcionMascotasMenu : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private val coleccionPublicacion = db.collection("Publicación")
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adapterPublicacion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adopcion_mascotas_menu)
        val btnpublicarAviso : Button = findViewById(R.id.botonPublicarAdopcionMascotas)
        val btnMenuMp2 : Button = findViewById(R.id.botonVolverMenuMP2)

        recyclerView = findViewById(R.id.listaRegistrosAdopcion)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = adapterPublicacion()
        recyclerView.adapter = adapter

        val btnConsultar : Button = findViewById(R.id.botonAvisosAdopcionMascotas)
        val btnPublicar : Button = findViewById(R.id.botonPublicarAdopcionMascotas)

        consultarColeccion()

        btnConsultar.setOnClickListener()
        {
            //consultarColeccion()
            val i = Intent(this,BuscadorMascotasAdopcion::class.java)
            startActivity(i)
        }
        btnPublicar.setOnClickListener()
        {
            val db = FirebaseFirestore.getInstance()
            val txt_titulo : TextView = findViewById(R.id.tituloRegistrarMascotaPerdida2)
            val txt_descipcion:TextView = findViewById(R.id.descripcionRegistrarMascotaPerdida2)
            var titulo:String = txt_titulo.text.toString()
            var descipcion:String =txt_descipcion.text.toString()
            var ubicacion:String = "Viña del mar"
            var tipo:String = "Adopción"
            var usuario:String = "Diego"
            val dateString = "20-11-2023"
            val format = SimpleDateFormat("dd-MM-yyyy")
            val date = format.parse(dateString)
            val timestamp = Timestamp(date.time)
            val data = hashMapOf(
                "Titulo" to titulo,
                "Descripcion" to descipcion,
                "TipoPublicacion" to tipo,
                "Ubicacion" to  ubicacion,
                "UsuarioPublica" to usuario,
                "FechaPublicacion" to timestamp

            )
            db.collection("Publicación")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"Registro Exitoso",Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this, "Error al registrar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        btnpublicarAviso.setOnClickListener()
        {
            val i = Intent(this,RegistrarMascotasAdopcion::class.java)
            startActivity(i)
        }

        btnMenuMp2.setOnClickListener()
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
                        if ((descripcion != null && fechaPublicacion != null && tipoPublicacion != null && titulo != null && ubicacion != null && usuarioPublica != null) && tipoPublicacion == "Adopcion") {
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