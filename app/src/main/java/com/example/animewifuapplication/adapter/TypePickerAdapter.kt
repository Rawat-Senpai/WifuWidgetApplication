package com.example.animewifuapplication.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animewifuapplication.R

class TypePickerAdapter (private val items: List<String>) :
    RecyclerView.Adapter<TypePickerAdapter.PickerViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        val layoutParams = view.layoutParams
        layoutParams.width = (parent.width / 2)
        view.layoutParams = layoutParams
        return PickerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PickerViewHolder, position: Int) {
//        holder.bind(items[position], position)

        val actualPosition = position % items.size
        holder.bind(items[actualPosition],actualPosition)

    }

    override fun getItemCount(): Int = items
        .size

    inner class PickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.itemText)

        fun bind(item: String, position: Int) {
            textView.text = item

            // Highlight the selected item
            if (selectedPosition == position) {
                textView.setTextColor(Color.BLUE)
                textView.setTypeface(null, Typeface.BOLD)
            } else {
                textView.setTextColor(Color.BLACK)
                textView.setTypeface(null, Typeface.NORMAL)
            }

            itemView.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition % items.size
                notifyItemChanged(selectedPosition)
            }
        }
    }
}