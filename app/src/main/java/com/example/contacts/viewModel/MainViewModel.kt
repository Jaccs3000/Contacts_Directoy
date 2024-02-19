package com.example.contacts.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.models.Personal
import androidx.lifecycle.viewModelScope
import com.example.contacts.config.PersonalApp
import com.example.contacts.config.PersonalApp.Companion.db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    val personalList = MutableLiveData<List<Personal>>()
    var parametroBusqueda = MutableLiveData<String>()

    fun iniciar(){
        viewModelScope.launch {
            personalList.value = withContext(Dispatchers.IO){
                db.personalDao().getAll()
            }

            for (personal in personalList.value!!){
                Log.d("Mensaje", "id ${personal.idEmpleado}, nombre ${personal.nombre}")
            }
        }
    }

    fun buscarPersonal() {
        viewModelScope.launch {
            personalList.value = withContext(Dispatchers.IO){
                db.personalDao().getByName(parametroBusqueda.value!!)
            }

            for (personal in personalList.value!!){
                Log.d("Mensaje", "id ${personal.idEmpleado}, nombre ${personal.nombre}")
            }
        }
    }
}