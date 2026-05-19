package com.example.freshpoint.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshpoint.data.DataMenuItem.burgerMenuList
import com.example.freshpoint.data.DataMenuItem.drinkMenuList
import com.example.freshpoint.data.DataMenuItem.souseMenuList
import com.example.freshpoint.model.BurgerMenuItem
import com.example.freshpoint.model.DrinkMenuItem
import com.example.freshpoint.model.FullOrder
import com.example.freshpoint.model.SelectedDrink
import com.example.freshpoint.model.SelectedSouse
import com.example.freshpoint.model.SouseMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToShoppingCart(
    modifier: Modifier = Modifier,
    souseMenuItems: List<SouseMenuItem>,
    drinkMenuItems: List<DrinkMenuItem>,
    burgerMenuItem: BurgerMenuItem?,
    onBackClick: () -> Unit,
    addToCart: (FullOrder) -> Unit,
){
    var souseCounts by rememberSaveable {
        mutableStateOf<Map<Int, Int>>(emptyMap())
    }
    var drinkCounts by rememberSaveable {
        mutableStateOf<Map<Int, Int>>(emptyMap())
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад"
                    )
                }

                Text(
                    text = "Выбор добавок",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(14.dp)
                .padding(innerPadding)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (burgerMenuItem != null) {
                        Image(
                            painter = painterResource(burgerMenuItem.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .padding(12.dp),
                            contentScale = ContentScale.Fit
                        )

                        Text(
                            text = stringResource(burgerMenuItem.name),
                            modifier = Modifier.padding(horizontal = 16.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                        )
                    } else {
                        Text(text = "Бургер не выбран")
                    }
                }
            }

            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(200.dp)
            ) {
                items(souseMenuItems) { souse ->
                    val count = souseCounts[souse.id] ?: 0

                    oneItemCard(
                        imageRes = souse.imageRes,
                        nameRes = souse.name,
                        price = souse.price,
                        count = count,
                        onAddClick = {
                            souseCounts = souseCounts + (souse.id to 1)
                        },
                        onPlusClick = {
                            souseCounts = souseCounts + (souse.id to count + 1)
                        },
                        onMinusClick = {
                            val newCount = count - 1

                            souseCounts =
                                if (newCount <= 0) {
                                    souseCounts - souse.id
                                } else {
                                    souseCounts + (souse.id to newCount)
                                }
                        }
                    )
                }
            }
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.height(200.dp)
            ) {
                items(drinkMenuItems) { drink ->
                    val count = drinkCounts[drink.id] ?: 0

                    oneItemCard(
                        imageRes = drink.imageRes,
                        nameRes = drink.name,
                        price = drink.price,
                        count = count,
                        onAddClick = {
                            drinkCounts = drinkCounts + (drink.id to 1)
                        },
                        onPlusClick = {
                            drinkCounts = drinkCounts + (drink.id to count + 1)
                        },
                        onMinusClick = {
                            val newCount = count - 1

                            drinkCounts =
                                if (newCount <= 0) {
                                    drinkCounts - drink.id
                                } else {
                                    drinkCounts + (drink.id to newCount)
                                }
                        }
                    )
                }
            }
            // Промежуточная цена заказа
            val currentOrderPrice =
                (burgerMenuItem?.price ?: 0f) +
                        souseMenuItems.sumOf { souse ->
                            ((souseCounts[souse.id] ?: 0) * souse.price).toDouble()
                        }.toFloat() +
                        drinkMenuItems.sumOf { drink ->
                            ((drinkCounts[drink.id] ?: 0) * drink.price).toDouble()
                        }.toFloat()
            Card(
                modifier = Modifier
            ) {
                Column(

                ) {
                    Button(
                        modifier = Modifier
                            .padding(25.dp)
                            .fillMaxWidth(),
                        onClick = {
                            if (burgerMenuItem != null) {
                                val selectedSouses = souseMenuItems.mapNotNull { souse ->
                                    val count = souseCounts[souse.id] ?: 0

                                    if (count > 0) {
                                        SelectedSouse(
                                            souse = souse,
                                            count = count
                                        )
                                    } else {
                                        null
                                    }
                                }

                                val selectedDrinks = drinkMenuItems.mapNotNull { drink ->
                                    val count = drinkCounts[drink.id] ?: 0

                                    if (count > 0) {
                                        SelectedDrink(
                                            drink = drink,
                                            count = count
                                        )
                                    } else {
                                        null
                                    }
                                }

                                val order = FullOrder(
                                    burger = burgerMenuItem,
                                    souses = selectedSouses,
                                    drinks = selectedDrinks
                                )

                                addToCart(order)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9800),
                            contentColor = Color.Black
                        )
                    ) { Text(text = "${"%.2f".format(currentOrderPrice)} ₽")

                    }
                }
            }

        }
    }


}
@Composable
fun oneItemCard(
    imageRes: Int,
    nameRes: Int,
    price: Float,
    count: Int,
    onAddClick: () ->Unit,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
){
    Card(
        modifier = Modifier.height(130.dp).width(150.dp)
    ) {
       Column(
           modifier = Modifier.padding(12.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Image(
               painter = painterResource(imageRes),
               contentDescription = null,
               modifier = Modifier.fillMaxWidth()
                   .height(80.dp).width(80.dp),
               contentScale = ContentScale.Fit
           )
           Text(
               text = stringResource(nameRes),
               maxLines = 1,
               overflow = TextOverflow.Ellipsis
           )
           if(count==0){
               Button(
                   onClick = onAddClick,
                   modifier = Modifier.fillMaxWidth().padding(8.dp).height(50.dp),
                   shape = RoundedCornerShape(14.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color(0xFFFF9800),
                       contentColor = Color.Black
                   )
               ){
                   Text(
                       text = "$price",
                       maxLines = 1,
                       style = MaterialTheme.typography.bodyMedium

                   )
               }
           }else{
               Spacer(modifier = Modifier.height(20.dp))
               Row(
                   modifier = Modifier.fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween,
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Button(
                       onClick = onMinusClick,
                       modifier = Modifier.size(36.dp),
                       contentPadding = PaddingValues(0.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color(0xFFFF9800),
                           contentColor = Color.Black
                       )
                   ) {
                       Text(text = "-")
                   }

                   Text(text = count.toString())

                   Button(
                       onClick = onPlusClick,
                       modifier = Modifier.size(36.dp),
                       contentPadding = PaddingValues(0.dp),
                       colors = ButtonDefaults.buttonColors(
                           containerColor = Color(0xFFFF9800),
                           contentColor = Color.Black
                       )
                   ) {
                       Text(text = "+")
                   }
               }
           }
       }
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
        addToCart = {},
        onBackClick = {}

    )
}