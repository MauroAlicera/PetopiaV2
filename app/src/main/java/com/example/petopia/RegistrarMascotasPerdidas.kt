package com.example.petopia

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text

class RegistrarMascotasPerdidas : AppCompatActivity() {
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private var ubicacion: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_mascotas_perdidas)
        val regions = arrayOf(
            "Región de Arica y Parinacota",
            "Región de Tarapacá",
            "Región de Antofagasta",
            "Región de Atacama",
            "Región de Coquimbo",
            "Región de Valparaíso",
            "Región Metropolitana",
            "Región de O’Higgins",
            "Región del Maule",
            "Región del Ñuble",
            "Región del Biobío",
            "Región de La Araucanía",
            "Región de Los Ríos",
            "Región de Los Lagos",
            "Región de Aysén",
            "Región de Magallanes"

        )

        val comunasPorRegion = mapOf(
            "Región de Arica y Parinacota" to arrayOf("Arica", "Camarones", "General Lagos", "Putre"),
            "Región de Tarapacá" to arrayOf("Alto Hospicio", "Camiña", "Colchane", "Huara", "Iquique", "Pica", "Pozo Almonte"),
            "Región de Antofagasta" to arrayOf("Antofagasta", "Calama", "Tocopilla", "Mejillones", "María Elena", "Taltal", "Sierra Gorda", "San Pedro de Atacama", "Ollagüe"),
            "Región de Atacama" to arrayOf("Chañaral", "Diego de Almagro", "Caldera", "Copiapó", "Tierra Amarilla", "Huasco", "Freirina", "Vallenar", "Alto del Carmen"),
            "Región de Coquimbo" to arrayOf("Canela", "Illapel", "Los Vilos", "Salamanca", "Andacollo", "Coquimbo", "La Higuera", "La Serena", "Paihuano", "Vicuña", "Combarbalá", "Monte Patria", "Ovalle", "Punitaqui", "Río Hurtado"),
            "Región de Valparaíso" to arrayOf("Hanga Roa", "Calle Larga", "Los Andes", "Rinconada", "San Esteban", "Cabildo", "La Ligua", "Papudo", "Petorca", "Zapallar", "Hijuelas", "La Calera", "La Cruz", "Nogales", "Quillota", "Algarrobo", "Cartagena", "El Quisco", "El Tabo", "San Antonio", "Santo Domingo", "Catemu", "Llay-Llay", "Panquehue", "Putaendo", "San Felipe", "Santa María", "Casablanca", "Concón", "Juan Fernández", "Puchuncaví", "Quintero", "Valparaíso", "Viña del Mar", "Limache", "Olmué", "Quilpué", "Villa Alemana"),
            "Región Metropolitana" to arrayOf(
                "Santiago", "Cerrillos", "Cerro Navia", "Conchalí", "El Bosque", "Estación Central", "Huechuraba",
                "Independencia", "La Cisterna", "La Florida", "La Granja", "La Pintana", "La Reina", "Las Condes",
                "Lo Barnechea", "Lo Espejo", "Lo Prado", "Macul", "Maipú", "Ñuñoa", "Pedro Aguirre Cerda", "Peñalolén",
                "Providencia", "Pudahuel", "Quilicura", "Quinta Normal", "Recoleta", "Renca", "San Joaquín", "San Miguel",
                "San Ramón", "Vitacura", "Puente Alto", "Pirque", "San José de Maipo", "Colina", "Lampa", "Til Til",
                "San Bernardo", "Buin", "Calera de Tango", "Paine", "Melipilla", "Alhué", "Curacaví", "María Pinto",
                "San Pedro", "Talagante", "El Monte", "Isla de Maipo", "Padre Hurtado", "Peñaflor"
            ),
            "Región de O'Higgins" to arrayOf(
                "Rancagua", "Codegua", "Coinco", "Coltauco", "Doñihue", "Graneros", "Las Cabras", "Machalí", "Malloa",
                "Mostazal", "Olivar", "Peumo", "Pichidegua", "Quinta de Tilcoco", "Rengo", "Requínoa", "San Vicente",
                "Pichilemu", "La Estrella", "Litueche", "Marchihue", "Navidad", "Paredones", "San Fernando", "Chépica",
                "Chimbarongo", "Lolol", "Nancagua", "Palmilla", "Peralillo", "Placilla", "Pumanque", "Santa Cruz"
            ),
            "Región del Maule" to arrayOf(
                "Talca", "Constitución", "Curepto", "Empedrado", "Maule", "Pelarco", "Pencahue", "Río Claro", "San Clemente",
                "San Rafael", "Cauquenes", "Chanco", "Pelluhue", "Curicó", "Hualañé", "Licantén", "Molina", "Rauco", "Romeral",
                "Sagrada Familia", "Teno", "Vichuquén", "Linares", "Colbún", "Longaví", "Parral", "Retiro", "San Javier",
                "Villa Alegre", "Yerbas Buenas"
            ),
            "Región del Ñuble" to arrayOf(
                "Chillán", "Bulnes", "Chillán Viejo", "El Carmen", "Pemuco", "Pinto", "Quillón", "San Ignacio", "Yungay",
                "Quirihue", "Cobquecura", "Coelemu", "Ninhue", "Portezuelo", "Ránquil", "Treguaco", "San Carlos", "Coihueco",
                "Ñiquén", "San Fabián", "San Nicolás"
            ),
            "Región del Bíobío" to arrayOf(
                "Concepción", "Coronel", "Chiguayante", "Florida", "Hualqui", "Lota", "Penco", "San Pedro de La Paz",
                "Santa Juana", "Talcahuano", "Tomé", "Hualpén", "Lebu", "Arauco", "Cañete", "Contulmo", "Curanilahue",
                "Los Álamos", "Tirúa", "Los Ángeles", "Antuco", "Cabrero", "Laja", "Mulchén", "Nacimiento", "Negrete",
                "Quilaco", "Quilleco", "San Rosendo", "Santa Bárbara", "Tucapel", "Yumbel", "Alto Biobío"
            ),
            "Región de La Araucanía" to arrayOf(
                "Temuco", "Carahue", "Cunco", "Curarrehue", "Freire", "Galvarino", "Gorbea", "Lautaro", "Loncoche",
                "Melipeuco", "Nueva Imperial", "Padre Las Casas", "Perquenco", "Pitrufquén", "Pucón", "Saavedra",
                "Teodoro Schmidt", "Toltén", "Vilcún", "Villarrica", "Cholchol", "Angol", "Collipulli", "Curacautín",
                "Ercilla", "Lonquimay", "Los Sauces", "Lumaco", "Purén", "Renaico", "Traiguén", "Victoria"
            ),
            "Región de Los Ríos" to arrayOf(
                "Corral", "Corral", "Lanco", "Los Lagos", "Máfil", "Mariquina", "Paillaco", "Panguipulli", "La Unión",
                "Futrono", "Lago Ranco", "Río Bueno"
            ),
            "Región de Los Lagos" to arrayOf(
                "Puerto Montt", "Calbuco", "Cochamó", "Fresia", "Frutillar", "Los Muermos", "Llanquihue", "Maullín",
                "Puerto Varas", "Castro", "Ancud", "Chonchi", "Curaco de Vélez", "Dalcahue", "Puqueldón", "Queilén",
                "Quellón", "Quemchi", "Quinchao", "Osorno", "Puerto Octay", "Purranque", "Puyehue", "Río Negro",
                "San Juan de la Costa", "San Pablo", "Chaitén", "Futaleufú", "Hualaihué", "Palena"
            ),
            "Región de Aysén" to arrayOf(
                "Coyhaique", "Lago Verde", "Aysén", "Cisnes", "Guaitecas", "Cochrane", "O'Higgins", "Tortel",
                "Chile Chico", "Río Ibáñez"
            ),
            "Región de Magallanes" to arrayOf("Punta Arenas", "Laguna Blanca", "Río Verde", "San Gregorio", "Cabo de Hornos", "Antártica", "Porvenir", "Primavera", "Timaukel", "Natales", "Torres del Paine")
        )

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, regions)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1 = findViewById(R.id.spinner1)
        spinner1.adapter = adapter1

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, emptyArray<String>())
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner2 = findViewById(R.id.spinner2)
        spinner2.adapter = adapter2

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                val selectedRegion = regions[position]
                val comunas = comunasPorRegion[selectedRegion] ?: emptyArray()
                updateComunasSpinner(comunas)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // No se seleccionó nada, puedes manejar esto según tus necesidades.
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Obtiene la comuna seleccionada y la asigna a la variable ubicacion
                ubicacion = parentView?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // No se seleccionó nada, puedes manejar esto según tus necesidades.
            }
        }
        val btnFinalizarRegistroMascotasPerdidas: Button = findViewById(R.id.botonFinalRegisroMascotasPerdidas)
        val btnPublicar : Button = findViewById(R.id.botonFinalRegisroMascotasPerdidas)
        btnPublicar.setOnClickListener()
        {
            val db = FirebaseFirestore.getInstance()
            val txt_titulo : TextView = findViewById(R.id.tituloRegistrarMascotaPerdida)
            val txt_descipcion: TextView = findViewById(R.id.descripcionRegistrarMascotaPerdida)
            var titulo:String = txt_titulo.text.toString()
            var descipcion:String =txt_descipcion.text.toString()

            var tipo:String = "Mascota Perdida"
            val usuario = FirebaseAuth.getInstance().currentUser
            val email = usuario?.email
            val currentDate = Date()
            val timestamp = Timestamp(currentDate.time)
            val data = hashMapOf(
                "Titulo" to titulo,
                "Descripcion" to descipcion,
                "TipoPublicacion" to tipo,
                "Ubicacion" to  ubicacion,
                "UsuarioPublica" to email,
                "FechaPublicacion" to timestamp

            )
            db.collection("Publicación")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,MascotasPerdidasMenu::class.java)
                    startActivity(i)
                }
                .addOnFailureListener{ e ->
                    Toast.makeText(this, "Error al registrar: ${e.message}", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,MascotasPerdidasMenu::class.java)
                    startActivity(i)
                }


    }

}
    private fun updateComunasSpinner(comunas: Array<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, comunas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter
    }
}