package com.aiinty.nmethods.screens.approxnumbers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aiinty.nmethods.R
import java.math.BigDecimal

private const val TAG = "ApproxNumbersRecyclerAdapter"

class ApproximateNumbersRecyclerAdapter(
    private var numbers: MutableList<BigDecimal>,
    private val numberClickInfoInterface: NumberClickInfoInterface,
    private val numberClickDeleteInterface: NumberClickDeleteInterface
)
    : RecyclerView.Adapter<ApproximateNumbersRecyclerAdapter.ItemHolder>() {

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemText: TextView = itemView.findViewById(R.id.item_text)
        val itemInfo: ImageView = itemView.findViewById(R.id.item_info)
        val itemDelete: ImageView = itemView.findViewById(R.id.item_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
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