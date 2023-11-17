package com.example.mycalculator.presentation.mainActivity

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mycalculator.databinding.ActionItemModelBinding
import com.example.mycalculator.domain.helpers.models.Action


class ActionsAdapter( private val context: Context) : RecyclerView.Adapter<ActionsAdapter.ItemViewHolder>() {


    companion object {
        const val TAG = "ActionsAdapter"
    }



    private var data : List<Action> = listOf()

    lateinit var onClickListener: OnClickListener

    inner class ItemViewHolder(binding: ActionItemModelBinding) : RecyclerView.ViewHolder(binding.root) {
        private val expressionTextView: TextView = binding.textView1ActionItemModel
        private val resultTextView: TextView = binding.textView2ActionItemModel
        private val container: ConstraintLayout = binding.containerActionItemModel



        fun bind(itemModel: Action) {
            container.setOnClickListener {
                onClickListener.onClick(itemModel)
            }

            expressionTextView.text = itemModel.expression
            resultTextView.text = itemModel.result.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ItemViewHolder(ActionItemModelBinding.inflate(itemView, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemId(position: Int): Long = data[position].idItem.toLong()

    override fun getItemCount() = data.size

    fun setData(newList : List<Action>){

        Log.d(TAG, "newList ${newList.toString()}")

        data = newList.toMutableList().reversed()

        Log.d(TAG, "newList ${data.toString()}")
    }

    interface OnClickListener {
        fun onClick(itemData: Action)
    }
}