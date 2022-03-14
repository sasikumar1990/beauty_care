package com.sutrix.beauty.salon.ui.main.adapter
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sutrix.beauty.salon.R
import com.sutrix.beauty.salon.data.model.Employee
import kotlinx.android.synthetic.main.employee_item_layout.view.*


class EmployeeAdapter(private val employee: ArrayList<Employee>) : RecyclerView.Adapter<EmployeeAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun bind(user: Employee) {
            itemView.apply {
                userName.text = user.name
                Glide.with(userImage.context)
                    .load(user.image)
                    .into(userImage)

            }

            itemView.setOnClickListener {
                if(!user.isSelected) {
                    user.isSelected = true
                    itemView.relativelayout.setBackgroundColor(Color.parseColor("#9ADFCD"))
                }else{
                    user.isSelected = false
                    itemView.relativelayout.setBackgroundColor(Color.parseColor("#FFFFFF"))

                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.employee_item_layout, parent, false))

    override fun getItemCount(): Int = employee.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(employee[position])
    }

    fun addEmployee(employee: List<Employee>) {
        this.employee.apply {
            clear()
            addAll(employee)
        }
    }
    fun getSelected(): ArrayList<Employee>? {
        val selected: ArrayList<Employee> = ArrayList()
        for (i in 0 until employee.size) {
            if (employee[i].isSelected) {
                selected.add(employee[i])
            }
        }
        return selected
    }
}