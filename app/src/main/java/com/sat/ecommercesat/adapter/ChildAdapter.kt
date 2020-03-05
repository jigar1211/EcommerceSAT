package com.sat.ecommercesat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sat.ecommercesat.R
import com.sat.ecommercesat.model.ChildModel
import com.sat.ecommercesat.model.RoomModel
import com.sat.ecommercesat.viewModel.HomeViewModel

class ChildAdapter(
    private val homeViewModel: HomeViewModel,
    private val roomModel: RoomModel
) :
    RecyclerView.Adapter<ChildAdapter.AdultViewHolder>() {

    class AdultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitleAdult: TextView = itemView.findViewById(R.id.tvTitleAdult)
        private val etName: EditText = itemView.findViewById(R.id.etName)
        private val etAge: EditText = itemView.findViewById(R.id.etAge)
        private val childModel = ChildModel()

        fun bind(position: Int, roomModel: RoomModel) {
            val position = position + 1
            tvTitleAdult.text = "$position"
            childModel.childName = etName.text.toString()
            childModel.childAge = etAge.text.toString()
            roomModel.arrayListOfChildren.add(childModel)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdultViewHolder {

        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_adult, parent, false)

        return AdultViewHolder(v)
    }

    override fun getItemCount(): Int {

        return homeViewModel.childCount
    }

    override fun onBindViewHolder(holder: AdultViewHolder, position: Int) {

        holder.bind(position, roomModel)
    }
}