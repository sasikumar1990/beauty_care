package com.sutrix.beauty.salon.ui.main.view
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sutrix.beauty.salon.R.*
import com.sutrix.beauty.salon.data.api.ApiHelper
import com.sutrix.beauty.salon.data.api.RetrofitBuilder
import com.sutrix.beauty.salon.data.database.CartInfo
import com.sutrix.beauty.salon.data.model.Employee
import com.sutrix.beauty.salon.data.repository.SaveCartInfoRepository
import com.sutrix.beauty.salon.ui.base.ViewModelFactory
import com.sutrix.beauty.salon.ui.main.adapter.EmployeeAdapter
import com.sutrix.beauty.salon.ui.main.viewmodel.EmployeeViewModel
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.android.synthetic.main.main_item_layout.view.*

class EmployeeActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var adapter: EmployeeAdapter
    private lateinit var saveUserInfoRepository: SaveCartInfoRepository
    var name: String? = ""
    var price: String? = ""
    var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_employee)
        setupBundle()
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupBundle() {
        val bundle = intent.extras
        // retrieve data from bundle
        if(bundle!= null) {
            id =  bundle!!.getString("id")
            name = bundle!!.getString("name")
            itemName.text = name
            price = bundle!!.getString("price")
            itemPrice.text = price

            Glide.with(itemImage.context)
                .load(bundle!!.getString("image"))
                .into(itemImage)
        }

    }
    private fun setupViewModel() {
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        saveUserInfoRepository = SaveCartInfoRepository(applicationContext)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(EmployeeViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeeAdapter(arrayListOf())
        recyclerView.adapter = adapter

        arrowIcon.setOnClickListener {
            var intent = Intent(this@EmployeeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        saveBtn.setOnClickListener {
            if (adapter.getSelected()!!.size > 0) {
                for (i in adapter.getSelected()!!.indices) {
                    val getList =
                        saveUserInfoRepository.getDataByNameAndUser(
                            name!!,
                            adapter.getSelected()!![i].name
                        )
                    if(getList.isEmpty()) {
                        val userInfo = CartInfo(
                            nid = id,
                            name = name,
                            price = price,
                            userName = adapter.getSelected()!![i].name,
                            userImage = adapter.getSelected()!![i].image
                        )
                        saveUserInfoRepository.insertUserInfo(userInfo)
                    }else{
                        id?.let { it1 ->
                            saveUserInfoRepository.updateName(
                                it1,
                                name!!,
                                adapter.getSelected()!![i].name
                            )
                        }
                    }
                }
                Toast.makeText(applicationContext,"Successfully saved to cart",Toast.LENGTH_LONG).show()
                var intent = Intent(this@EmployeeActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext,"No selection found",Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun setupObservers() {
        viewModel.getList()?.observe(this, Observer {
            progressBar.visibility = View.GONE
            retrieveList(it.data)
        })
    }

    private fun retrieveList(list: List<Employee>) {
        adapter.apply {
            addEmployee(list)
            notifyDataSetChanged()
        }
    }
}
