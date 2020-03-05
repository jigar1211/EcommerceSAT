package com.sat.ecommercesat.viewModel

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    var roomCount = 1
    var adultCount = 1
    var childCount = 1

    fun addRooms(roomCount: Int) {
        this.roomCount = roomCount
    }

    fun addAdultCount(adultCount: Int) {
        this.adultCount = adultCount
    }

    fun addChildCount(childCount: Int) {
        this.childCount = childCount
    }
}