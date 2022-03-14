package com.sutrix.beauty.salon.ui.main.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sutrix.beauty.salon.R
import com.sutrix.beauty.salon.data.model.Data
import kotlinx.android.synthetic.main.main_item_layout.view.imageView
import kotlinx.android.synthetic.main.main_item_layout.view.price
import kotlinx.android.synthetic.main.main_item_layout.view.name

class MainAdapter(private val list: ArrayList<Data>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    var onItemClick: ((Data)->Unit) ?= null

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Data) {
            itemView.apply {
                name.text = user.name
                price.text = "$ "+user.price.toString()
                Glide.with(imageView.context)
                    .load(user.image)
                    .into(imageView)
            }
        }
        init{
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item_layout, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun addList(list: List<Data>) {
        this.list.apply {
            clear()
            addAll(list)
        }
    }
}