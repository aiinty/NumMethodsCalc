package com.aiinty.nmethods.screens.operations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aiinty.nmethods.NumericalMethods
import com.aiinty.nmethods.R
import java.math.BigDecimal

private const val TAG = "NumbersRecyclerAdapter"

class NumbersRecyclerAdapter(
    private var numbers: MutableList<BigDecimal>,
    private val numberClickInfoInterface: NumberClickInfoInterface,
    private val numberClickDeleteInterface: NumberClickDeleteInterface
)
    : RecyclerView.Adapter<NumbersRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val itemInfo: ImageView = itemView.findViewById(R.id.item_info)
        val itemDelete: ImageView = itemView.findViewById(R.id.item_delete)
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
        holder.itemText.text = numbers[position].toPlainString()
        holder.itemInfo.setOnClickListener {
            numberClickInfoInterface.onInfoIconClick(position)
        }
        holder.itemDelete.setOnClickListener {
            numberClickDeleteInterface.onDeleteIconClick(position)
        }
    }
    override fun getItemCount(): Int {
        return numbers.size
    }
}

interface NumberClickInfoInterface {
    fun onInfoIconClick(idx: Int)
}

interface NumberClickDeleteInterface {
    fun onDeleteIconClick(idx: Int)
}