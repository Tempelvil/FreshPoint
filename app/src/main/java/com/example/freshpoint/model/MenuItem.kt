package com.example.freshpoint.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.descriptors.SerialDescriptor

data class BurgerMenuItem(
    @StringRes val name: Int,
    val description:String = "",
    val price: Float,
    @DrawableRes val imageRes : Int,
)
data class DrinkMenuItem(
    @StringRes val name: Int,
    val description: String="Современная и невероятно вкусная кола от бренда премиальных газированных" +
            " напитков с более чем полувековой историей. Насладитесь ярким богатым вкусом и бодрящей свежестью",
    val price: Float,
    @DrawableRes val imageRes : Int

)
data class SouseMenuItem(
    @StringRes val name: Int,
    val description:String = "Добавьте в любимые закуски приятный аромат, остроту и восхитительный" +
            " нежный вкус соуса. Это то, без чего не обойтись!",
    val price: Float,
    @DrawableRes val imageRes : Int,
)
data class Restaurant(
    val address: String
)