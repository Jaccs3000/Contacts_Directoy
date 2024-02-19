package com.example.contacts.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Personal (
    @PrimaryKey(autoGenerate = true)
    var idEmpleado: Long,
    val nombre : String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val edad: Int
)