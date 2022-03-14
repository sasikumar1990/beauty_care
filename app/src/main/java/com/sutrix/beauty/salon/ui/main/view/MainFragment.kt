package com.sutrix.beauty.salon.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sutrix.beauty.salon.R.*
import com.sutrix.beauty.salon.data.api.ApiHelper
import com.sutrix.beauty.salon.data.api.RetrofitBuilder
import com.sutrix.beauty.salon.data.model.Data
import com.sutrix.beauty.salon.ui.base.ViewModelFactory
import com.sutrix.beauty.salon.ui.main.adapter.MainAdapter
import com.sutrix.beauty.salon.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.progressBar
import kotlinx.android.synthetic.main.fragment_main.recyclerView
import android.content.Intent


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_main, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        setupViewModel()
        setupUI()
        setupObservers()
    }
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf())
        recyclerView.adapter = adapter
        adapter.onItemClick = { data ->
            val id = data.id
            val name = data.name
            val price = data.price
            val imageUrl = data.image
            activity?.let {
                val intent = Intent(it, EmployeeActivity::class.java)
                // send data via bundle
                val bundle = Bundle()
                bundle.putString("id", id.toString())
                bundle.putString("name", name)
                bundle.putString("price", "$ $price")
                bundle.putString("image", imageUrl)
                intent.putExtras(bundle);
                it.startActivity(intent)
            }
        }
    }

    private fun setupObservers() {
        viewModel.getList()?.observe(this, Observer {
            progressBar.visibility = View.GONE
            retrieveList(it.data)
        })
    }

    private fun retrieveList(list: List<Data>) {
        adapter.apply {
            addList(list)
            notifyDataSetChanged()
        }
    }
}
