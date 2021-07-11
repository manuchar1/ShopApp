package com.tbcacademy.shopapp.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tbcacademy.shopapp.databinding.DrawerMenuTextBinding
import com.tbcacademy.shopapp.model.Menu

typealias menuItemOnClick = (actionId: Int) -> Unit

class MenuAdapter(private val items: Array<Menu>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: DrawerMenuTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Menu
        fun bind() {
            model = items[adapterPosition]
            binding.tvTitle.text = model.title
            binding.root.setOnClickListener {
                menuItemOnClick.invoke(model.id)
            }

        }

    }
    lateinit var menuItemOnClick: menuItemOnClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DrawerMenuTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MenuAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size


}