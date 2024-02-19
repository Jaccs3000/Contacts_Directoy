package com.example.contacts.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.contacts.models.Personal

@Dao
interface PersonalDao {

    @Query("select * from Personal")
    suspend fun getAll(): List<Personal>    // suspend para ejecutar en otro hilo (corrutina)

    @Query("select * from Personal where idEmpleado = :id")
    suspend fun getById(id: Long): Personal

    @Query("select * from Personal where nombre like '%'||:name||'%' or apellido like '%'||:name||'%'")
    suspend fun getByName(name: String): List<Personal>

    @Insert
    suspend fun insert(personas: List<Personal>): List<Long>

    @Update
    suspend fun update(personal: Personal): Int

    @Delete
    suspend fun delete(personal: Personal): Int

}