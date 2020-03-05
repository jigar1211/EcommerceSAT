package com.sat.ecommercesat.model

import kotlinx.serialization.Serializable

@Serializable
data class RoomModel (var roomCount:Int = 1, var arrayListOfAdults: ArrayList<AdultModel> = ArrayList(), var arrayListOfChildren: ArrayList<ChildModel> = ArrayList()){
}