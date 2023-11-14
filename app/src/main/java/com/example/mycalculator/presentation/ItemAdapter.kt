package com.example.mycalculator.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalculator.R
import com.example.mycalculator.domain.helpers.Item
import java.lang.RuntimeException


class ItemAdapter(private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var list = listOf<Item>()

    interface ItemClickListener {
        fun onItemClick(id: Long, itemView: View)
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return when(item.isPressed){
            true -> {100}
            false -> { 200 }
        }
    }

    override fun getItemCount(): Int { return list.size }

    inner class ItemViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        private lateinit var titleTextView: TextView
        init {
            when(viewType){
                100 -> {titleTextView = itemView.findViewById(R.id.textView_item_pressed)}

                200 -> {titleTextView = itemView.findViewById(R.id.textView_item_not_pressed)}

            }

            itemView.setOnClickListener { itemClickListener.onItemClick(list[adapterPosition].id, itemView) }
        }


        fun bind(taskModel: Item) {
            titleTextView.text = taskModel.action.codeString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return when(viewType){
            100 -> {ItemViewHolder(itemView.inflate(R.layout.item_pressed, parent, false),viewType)}

            200 -> {ItemViewHolder(itemView.inflate(R.layout.item_not_pressed, parent, false),viewType)}

            else -> {throw RuntimeException("error in onCreateViewHolder")}
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }


    fun setList(listNew : List<Item>){
            list = listNew
            notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }
}