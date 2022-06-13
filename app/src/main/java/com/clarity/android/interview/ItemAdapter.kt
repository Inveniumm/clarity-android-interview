package com.clarity.android.interview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clarity.android.interview.ItemAdapter.ViewHolder
import com.clarity.android.interview.network.DeliveryItem
import kotlinx.android.synthetic.main.item_row.view.*
import java.util.ArrayList

class ItemAdapter : RecyclerView.Adapter<ViewHolder>() {

   var items = ArrayList<DeliveryItem>()

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(row: DeliveryItem) {
        itemView.recycler_text.text = row.name
      }
  }

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ViewHolder {
    val view = LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_row, parent, false)
    return ViewHolder(view)

  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position])
  }

  override fun getItemCount(): Int {
    return items.size
  }

  fun update(newItems: List<DeliveryItem>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }

}
