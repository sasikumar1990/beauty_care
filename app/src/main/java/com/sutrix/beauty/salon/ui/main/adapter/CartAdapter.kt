package com.sutrix.beauty.salon.ui.main.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sutrix.beauty.salon.R
import com.sutrix.beauty.salon.data.model.CartData
import kotlinx.android.synthetic.main.cart_item_layout.view.*

class CartAdapter(private val data: ArrayList<CartData>) : RecyclerView.Adapter<CartAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: CartData) {
            itemView.apply {
                name.text = data.name
                userName.text = data.userName
                price.text = data.price
                Glide.with(userImage.context)
                    .load(data.userImage)
                    .into(userImage)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addData(data: List<CartData>) {
        this.data.apply {
            clear()
            addAll(data)
        }
    }
}