package com.sat.ecommercesat.adapter

import android.app.Activity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sat.ecommercesat.R
import com.sat.ecommercesat.model.RoomModel
import com.sat.ecommercesat.viewModel.HomeViewModel


class HomeAdapter(
    private val activity: Activity,
    private val homeViewModel: HomeViewModel,
    private val roomModel: RoomModel
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvRoomTitle: TextView = itemView.findViewById(R.id.tvRoomTitle)
        private val rvAdultDetails: RecyclerView = itemView.findViewById(R.id.rvAdultDetails)
        private val rvChildDetails: RecyclerView = itemView.findViewById(R.id.rvChildDetails)
        private val etAdultNumber: EditText = itemView.findViewById(R.id.etAdultNUmber)
        private val etChildNumber: EditText = itemView.findViewById(R.id.etChildNumber)
        private val layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        private val layoutManagerForChild =
            LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        private var adultAdapter: AdultAdapter? = null
        private var childAdapter: ChildAdapter? = null

        fun bind(
            position: Int,
            homeViewModel: HomeViewModel,
            roomModel: RoomModel
        ) {
            tvRoomTitle.text = "Room $position"
            rvAdultDetails.layoutManager = layoutManager
            rvChildDetails.layoutManager = layoutManagerForChild

            adultAdapter = AdultAdapter(homeViewModel,roomModel)
            childAdapter = ChildAdapter(homeViewModel,roomModel)
            rvAdultDetails.adapter = adultAdapter
            rvChildDetails.adapter = childAdapter
            etAdultNumber.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!TextUtils.isEmpty(s.toString())) {
                        homeViewModel.addAdultCount(s.toString().toInt())
                        adultAdapter!!.notifyDataSetChanged()
                    }
                }

            })
            etChildNumber.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!TextUtils.isEmpty(s.toString())) {
                        homeViewModel.addChildCount(s.toString().toInt())
                        childAdapter!!.notifyDataSetChanged()
                    }
                }

            })
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_room_count, parent, false)

        return HomeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return homeViewModel.roomCount
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        roomModel.roomCount = homeViewModel.roomCount
        holder.bind(position, homeViewModel,roomModel)
    }
}