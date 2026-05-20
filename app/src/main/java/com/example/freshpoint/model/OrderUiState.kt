package com.example.freshpoint.model

import android.view.MenuItem
import com.example.freshpoint.data.DataMenuItem
import com.example.freshpoint.data.DataMenuItem.burgerMenuList
import com.example.freshpoint.data.DataMenuItem.drinkMenuList
import com.example.freshpoint.data.DataMenuItem.souseMenuList

data class OrderUiState (
    val bonuses:Int =0,
    val isBonusesUsed: Boolean = false,
    val orderType: OrderType? = null,
    val deliveryAddress: String = "",
    val showDeliveryAddress: String = "",
    val isDeliveryAddressSaved: Boolean = false,
    val selectedRestaurantAddress: String = "",
    val isRestaurantAddressSaved: Boolean =false,
    val burgerMenuItems: List<BurgerMenuItem> = burgerMenuList,
    val selectedBurgerItem: BurgerMenuItem? = null,
    val souseMenuItem: List<SouseMenuItem> = souseMenuList,
    val selectedSouseMenuItem: SouseMenuItem? = null,
    val drinkMenuItem: List<DrinkMenuItem> = drinkMenuList,
    val selectedDrinkMenuItem: DrinkMenuItem? = null,
    val editingOrder: FullOrder? = null,

    val fullOrder: List<FullOrder> = emptyList()
)