package com.example.contacts.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.config.Constantes
import com.example.contacts.databinding.ItemListBinding
import com.example.contacts.models.Personal
import com.example.contacts.ui.FormularioActivity

class PersonalAdapter(private val dataSet: List<Personal>?) :
    RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet?.get(position)
        viewHolder.enlazarItem(item!!)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemListBinding.bind(view)
        var contexto = view.context

        fun enlazarItem(p: Personal) {
            binding.tvNombre.text = "${p.nombre} ${p.apellido}"
            binding.tvEmail.text = p.email
            binding.tvTelefono.text = p.telefono
            binding.tvEdad.text = p.edad.toString()
            binding.tvEmail.text = p.email

            binding.root.setOnClickListener {
                val intent = Intent(contexto, FormularioActivity::class.java)
                intent.putExtra(Constantes.OPERACION_KEY, Constantes.OPERACION_EDITAR)
                intent.putExtra(Constantes.ID_OPERACION_KEY, p.idEmpleado)
                contexto.startActivity(intent)

            }
        }
    }

}
