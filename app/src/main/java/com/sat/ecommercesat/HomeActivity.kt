package com.sat.ecommercesat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sat.ecommercesat.adapter.HomeAdapter
import com.sat.ecommercesat.model.RoomModel
import com.sat.ecommercesat.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel
    private var homeAdapter: HomeAdapter? = null
    private val layoutManager =
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
   private val roomModel:RoomModel = RoomModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        setContentView(R.layout.activity_home)
        mappingData()
    }

    private fun mappingData() {
        homeAdapter = HomeAdapter(this, homeViewModel,roomModel)
        rvHome.layoutManager = layoutManager
        rvHome.adapter = homeAdapter
        spRoomData.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                homeViewModel.addRooms(spRoomData.selectedItem.toString().toInt())
                homeAdapter!!.notifyDataSetChanged()
            }
        }
        btnSubmit.setOnClickListener(View.OnClickListener {

            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("roomDetails",roomModel)
            startActivity(intent)
        })
    }
}
