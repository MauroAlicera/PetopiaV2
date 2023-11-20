package com.example.petopia

import com.google.firebase.Timestamp

data class Publicacion(
    val descripcion:String,
    val fechaPublicacion:Timestamp,
    val tipoPublicacion:String,
    val titulo:String,
    val ubicacion:String,
    val usuarioPublica:String
)
