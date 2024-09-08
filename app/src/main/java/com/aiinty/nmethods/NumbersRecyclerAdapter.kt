package com.aiinty.nmethods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal

class NumbersRecyclerAdapter(
    var numbers: MutableList<BigDecimal>,
    val numberClickDeleteInterface: NumberClickDeleteInterface
)
    : RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText = itemView.findViewById<TextView>(R.id.item_text)
        val deleteItem = itemView.findViewById<ImageView>(R.id.delete_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemText.setText(numbers.get(position).toPlainString())
        holder.deleteItem.setOnClickListener {
            numberClickDeleteInterface.onDeleteIconClick(position)
        }
    }
    override fun getItemCount(): Int {
        return numbers.size
    }
}

interface NumberClickDeleteInterface {
    fun onDeleteIconClick(idx: Int)
}