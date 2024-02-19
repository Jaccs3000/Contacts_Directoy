package com.example.contacts.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.contacts.MainActivity
import com.example.contacts.R
import com.example.contacts.config.Constantes
import com.example.contacts.databinding.ActivityFormularioBinding
import com.example.contacts.databinding.ItemListBinding
import com.example.contacts.dialogos.BorrarDialogo
import com.example.contacts.viewModel.FormularioViewModel

class FormularioActivity : AppCompatActivity(), BorrarDialogo.BorrarListener {
    lateinit var binding: ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel
    lateinit var dialgoBorrar: BorrarDialogo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialgoBorrar = BorrarDialogo(this)

        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!
        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe(this, Observer {
            if(it){
                mostrarMensaje("Operación Exitosa")
                irAlInicio()
            }else{
                mostrarMensaje("Ocurrió un Error")
            }
        })

        if(viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_OPERACION_KEY, 0)
            viewModel.cargarDatos()
            binding.linearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility = View.GONE
        }else{
            binding.linearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE
        }
        binding.btnBorrar.setOnClickListener{
            mostrarDialogo()
        }
    }

    private fun mostrarDialogo() {
        dialgoBorrar.show(supportFragmentManager, "Dialogo Borrar")

    }

    private fun irAlInicio() {
        val intent = Intent(applicationContext, MainActivity::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    override fun borrarPersonal() {
        viewModel.eliminarPersonal()
    }
}