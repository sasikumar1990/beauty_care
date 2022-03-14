package com.sutrix.beauty.salon.ui.main.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sutrix.beauty.salon.R.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val mainFragment = MainFragment()
        val cartFragment = CartFragment()
        setCurrentFragment(mainFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                id.service -> setCurrentFragment(mainFragment)
                id.cart -> setCurrentFragment(cartFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(id.flFragment, fragment)
            commit()
        }

    override fun onBackPressed() {

        try {
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle("Exit App")
            //set message for alert dialog
            builder.setMessage("Do you want to close this application?")

            //performing positive action
            builder.setPositiveButton("Exit") { dialogInterface, which ->

                dialogInterface.dismiss()
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }

            //performing negative action
            builder.setNegativeButton("No") { dialogInterface, which ->
                dialogInterface.dismiss()

            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        } catch (e: Exception) {
        }

    }

}
