package com.example.petopia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

class adapterPublicacion : RecyclerView.Adapter<adapterPublicacion.ViewHolder> () {
    private var datos: List<Publicacion> = ArrayList()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtFechaPublicacion: TextView = itemView.findViewById(R.id.txtFechaPublicacion)
        val txtTipoPublicacion: TextView = itemView.findViewById(R.id.txtTipoPublicacion)
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtUbicacion: TextView = itemView.findViewById(R.id.txtUbicacion)
        val txtUsuarioPublica: TextView = itemView.findViewById(R.id.txtUsuarioPublica)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    fun setDatos(datos:List<Publicacion>){
        this.datos = datos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datos[position]

        holder.txtDescripcion.text = "Descripción: ${item.descripcion}"
        if (item.fechaPublicacion != null) {
            val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm:ss z", Locale.getDefault())
            val fechaFormateada = dateFormat.format(item.fechaPublicacion.toDate())
            holder.txtFechaPublicacion.text = "Fecha de publicación: $fechaFormateada"
        } else {
            holder.txtFechaPublicacion.text = "Fecha no disponible"
        }
        holder.txtTipoPublicacion.text = "Tipo de publicación: ${item.tipoPublicacion}"
        holder.txtTitulo.text = "Título: ${item.titulo}"
        holder.txtUbicacion.text = "Ubicación: ${item.ubicacion}"
        holder.txtUsuarioPublica.text = "Publicación de: ${item.usuarioPublica}"

    }
}