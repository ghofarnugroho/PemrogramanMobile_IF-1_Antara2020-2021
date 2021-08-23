package id.nugroho.ghofar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.nugroho.ghofar.RecyclerviewItemAdapter.MyViewHolder

class RecyclerviewItemAdapter internal constructor(private val itemsList: List<Province>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.name
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        private val itemLayout: LinearLayout

        init {
            name = itemView.findViewById(R.id.tvName)
            itemLayout = itemView.findViewById(R.id.itemLayout)
        }
    }
}