package com.example.contacts.config

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class PersonalApp: Application() {

    companion object{
        lateinit var db: PersonalDb
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            PersonalDb::class.java,
            "personal"
        ).build()
    }
}