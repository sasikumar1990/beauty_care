package com.sutrix.beauty.salon.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sutrix.beauty.salon.R.*
import com.sutrix.beauty.salon.data.model.CartData
import com.sutrix.beauty.salon.data.repository.SaveCartInfoRepository
import com.sutrix.beauty.salon.ui.main.adapter.CartAdapter
import kotlinx.android.synthetic.main.fragment_cart.*
import java.lang.StringBuilder

class CartFragment : Fragment() {

    private lateinit var adapter: CartAdapter
    private lateinit var saveUserInfoRepository: SaveCartInfoRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setupUI()
        setupData()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter(arrayListOf())
        recyclerView.adapter = adapter
    }

    // retrieving user data based on saved items
    private fun setupData() {
        try {
            saveUserInfoRepository = SaveCartInfoRepository(requireContext())
            val getData =
                saveUserInfoRepository.getAllUserInfo()
            val list = mutableMapOf<String, CartData>()

            if(getData.isNotEmpty()) {
                no_data.visibility = View.INVISIBLE
                getData.forEach {
                    val getName = saveUserInfoRepository.getDataByUserName(it.userName!!)
                    val builderName = StringBuilder()
                    val builderPrice = StringBuilder()
                    getName.forEach { data ->
                        builderName.append(data.name + "\n")
                        builderPrice.append(data.price + "\n")
                    }
                    list[it.userName!!] =
                        CartData(
                            it.id,
                            it.userName!!,
                            it.userImage!!,
                            builderName.toString(),
                            builderPrice.toString()
                        )
                }
                retrieveList(list.values.toList())
            }else{
                no_data.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
        }

    }

    private fun retrieveList(list: List<CartData>) {
        adapter.apply {
            addData(list)
            notifyDataSetChanged()
        }
    }
}
