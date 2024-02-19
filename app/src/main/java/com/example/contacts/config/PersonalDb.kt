package com.example.contacts.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contacts.dao.PersonalDao
import com.example.contacts.models.Personal

@Database(  entities = [Personal::class],
            version = 1 )
abstract class PersonalDb: RoomDatabase() {
    abstract fun personalDao(): PersonalDao
}