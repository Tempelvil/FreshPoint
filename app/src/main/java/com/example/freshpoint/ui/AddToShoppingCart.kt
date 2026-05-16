package com.example.freshpoint.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshpoint.data.DataMenuItem.burgerMenuList
import com.example.freshpoint.data.DataMenuItem.drinkMenuList
import com.example.freshpoint.data.DataMenuItem.souseMenuList
import com.example.freshpoint.model.BurgerMenuItem
import com.example.freshpoint.model.DrinkMenuItem
import com.example.freshpoint.model.SouseMenuItem

@Composable
fun AddToShoppingCart(
    modifier: Modifier = Modifier,
    souseMenuItems: List<SouseMenuItem>,
    drinkMenuItems: List<DrinkMenuItem>,
    burgerMenuItem: BurgerMenuItem
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier= Modifier.height(40.dp))
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { }
    }

}

@Preview(showBackground = true)
@Composable
fun previewAddToShoppingCart(){
    AddToShoppingCart(
        modifier = Modifier,
        souseMenuItems = souseMenuList,
        drinkMenuItems = drinkMenuList,
        burgerMenuItem = burgerMenuList[0],

    )
}