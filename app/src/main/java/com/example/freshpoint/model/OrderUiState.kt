package com.example.freshpoint.model

data class OrderUiState (
    val bonuses:Int =0,
    val orderType: OrderType? = null,
    val deliveryAddress: String = "",
    val selectedRestaurantAddress: String = "",

)