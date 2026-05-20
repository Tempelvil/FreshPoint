package com.example.freshpoint.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
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
import com.example.freshpoint.model.FullOrder
import com.example.freshpoint.model.OrderType
import com.example.freshpoint.model.Restaurant
import com.example.freshpoint.model.SelectedDrink
import com.example.freshpoint.model.SelectedSouse
import com.example.freshpoint.ui.theme.bonusCard

@Composable
fun CartOrderScreen(
    modifier: Modifier= Modifier,
    onBackClick: () ->Unit,
    selectedRestorauntAdres: String?,
    selectedDeliveryAdres: String?,
    orderType: OrderType? = null,
    onChangeOrderClick: () -> Unit,
    onChangeTypeOrder: (OrderType) ->Unit,
    bonuses: Int,
    isBonusesUsed: Boolean,
    onBonusesUsedChange: (Boolean) -> Unit,
    fullOrder: List<FullOrder>,
    onChangeOrderItem: (FullOrder)->Unit,
    onPlusOrderClick: (FullOrder) -> Unit,
    onMinusOrderClick: (FullOrder) -> Unit
){
    val orderPrice =fullOrder.sumOf {
        order ->
        val burgerPrice = order.burger.price.toDouble()
        val sousesPrice = order.souses.sumOf { selectedSouse ->
            (selectedSouse.souse.price*selectedSouse.count).toDouble()
        }
        val drinksPrice = order.drinks.sumOf { selectedDrink ->
            (selectedDrink.drink.price * selectedDrink.count).toDouble()
        }
        burgerPrice + sousesPrice + drinksPrice
    }.toFloat()

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
                    text = "Корзина и оплата",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                OrderDeliveryOrRestCard(
                    selectedDelivery = selectedDeliveryAdres,
                    selectedRestoraunt = selectedRestorauntAdres,
                    orderType = orderType,
                    onOrderTypeChange = onChangeTypeOrder,
                    onChangeOrderClick = onChangeOrderClick
                )
            }

            item {
                bonusesCard(
                    bonuses = bonuses,
                    orderPrice = orderPrice,
                    isBonusesUsed = isBonusesUsed,
                    onBonusesUsedChange = onBonusesUsedChange
                )
            }
            items(fullOrder) {
                order ->
                fullOrderCard(
                    fullOrderItem = order,
                    onChangeOrderItem = onChangeOrderItem,
                    onPlusOrderClick = onPlusOrderClick,
                    onMinusOrderClick = onMinusOrderClick
                )
            }
        }

    }
}

@Composable
fun bonusesCard(
    bonuses: Int,
    orderPrice: Float,
    isBonusesUsed: Boolean,
    onBonusesUsedChange: (Boolean) -> Unit
) {

    val minOrderPrice = 300f

    val maxBonusesToUse = (orderPrice - minOrderPrice)
        .coerceAtLeast(0f)

    val bonusesToUse = minOf(
        bonuses.toFloat(),
        maxBonusesToUse
    )

    val canUseBonuses = bonusesToUse > 0f

    val textModifier = Modifier.padding(start = 8.dp)

    val bonusStatusText = when (bonuses) {
        0 -> "У тебя нет бонусов для списания"
        in 1..299 -> "Минимальная сумма заказа должна быть от 300₽"
        else -> "Можно списать $bonusesToUse"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(80.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CurrencyBitcoin,
                        contentDescription = null
                    )

                    Text(
                        text = "У тебя $bonuses бонусов",
                        modifier = Modifier.padding(start = 8.dp,),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = bonusStatusText,
                    modifier = textModifier
                )
            }
            Switch(
                checked = isBonusesUsed,
                onCheckedChange = { checked ->
                    onBonusesUsedChange(checked)
                },
                enabled = canUseBonuses
            )
        }

    }
}
@Composable
fun fullOrderCard(
    fullOrderItem: FullOrder,
    onChangeOrderItem: (FullOrder) -> Unit,
    onPlusOrderClick: (FullOrder) -> Unit,
    onMinusOrderClick: (FullOrder) -> Unit,
    modifier: Modifier = Modifier,

) {
    Card(
        modifier = modifier.padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(24.dp),
        onClick = {
            onChangeOrderItem(fullOrderItem)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(fullOrderItem.burger.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 12.dp),
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(fullOrderItem.burger.name),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                fullOrderItem.souses.forEach { selectedSouse ->
                    Text(
                        text = "${stringResource(selectedSouse.souse.name)} x${selectedSouse.count}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                fullOrderItem.drinks.forEach { selectedDrink ->
                    Text(
                        text = "${stringResource(selectedDrink.drink.name)} x${selectedDrink.count}",
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "%.2f ₽".format(calculateFullOrderPrice(fullOrderItem)),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )

                    Button(
                        onClick = {
                            onMinusOrderClick(fullOrderItem)
                        },
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9800),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("-")
                    }

                    Text(
                        text = fullOrderItem.count.toString(),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )

                    Button(
                        onClick = {
                            onPlusOrderClick(fullOrderItem)
                        },
                        modifier = Modifier.size(36.dp),
                        contentPadding = PaddingValues(0.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF9800),
                            contentColor = Color.Black
                        )
                    ) {
                        Text("+")
                    }
                }
            }
        }
    }
}

fun calculateFullOrderPrice(order: FullOrder): Float {
    val burgerPrice = order.burger.price

    val sousesPrice = order.souses.sumOf { selectedSouse ->
        (selectedSouse.souse.price * selectedSouse.count).toDouble()
    }.toFloat()

    val drinksPrice = order.drinks.sumOf { selectedDrink ->
        (selectedDrink.drink.price * selectedDrink.count).toDouble()
    }.toFloat()

    return burgerPrice + sousesPrice + drinksPrice
}

@Composable
fun OrderDeliveryOrRestCard(
    selectedRestoraunt: String?,
    selectedDelivery: String?,
    orderType: OrderType? = null,
    onOrderTypeChange: (OrderType) -> Unit,
    onChangeOrderClick: () -> Unit

)
{

    val mainText = when (orderType) {
        OrderType.RESTAURANT -> {
            if (selectedRestoraunt.isNullOrBlank()) {
                "Выберите ресторан"
            } else {
                selectedRestoraunt
            }
        }

        OrderType.DELIVERY -> {
            if (selectedDelivery.isNullOrBlank()) {
                "Выберите адрес доставки"
            } else {
                selectedDelivery
            }
        }

        null -> "Выберите тип заказа"
    }

    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 2.dp)
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp, bottomStart = 0.dp, bottomEnd = 0.dp)


    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Рестораны открыты до 22:00",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = orderType == OrderType.DELIVERY,
                onCheckedChange = { checked ->
                    onOrderTypeChange(
                        if (checked) {
                            OrderType.DELIVERY
                        } else {
                            OrderType.RESTAURANT
                        }
                    )
                }
            )
        }

    }
    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 4.dp)
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 14.dp, bottomEnd = 14.dp),
        onClick = onChangeOrderClick

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center

        ) {
            Row(
                modifier = Modifier.padding(start = 12.dp)
            ) {

                Text(
                    text = mainText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForwardIos,
                    contentDescription = null,
                    modifier = Modifier.padding(end=10.dp)


                )

            }

            Row(
                modifier = Modifier.padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = when (orderType) {
                        OrderType.RESTAURANT -> Icons.Default.RestaurantMenu
                        OrderType.DELIVERY -> Icons.Default.DirectionsBike
                        null -> Icons.Default.Add
                    },
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )

                Text(
                    text = when (orderType) {
                        OrderType.RESTAURANT -> "В ресторане"
                        OrderType.DELIVERY -> "Доставка"
                        null -> "Тип заказа не выбран"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewCartOrderScreen(){
    CartOrderScreen(
        onBackClick = {},
        selectedRestorauntAdres = "Улица ленина 25,Улица ленина 25,Улица ленина 25,Улица ленина 25",
        selectedDeliveryAdres = "Улица доставки 666",
        orderType = OrderType.RESTAURANT,
        onChangeOrderClick = {},
        onChangeTypeOrder = {},
        bonuses = 0,
        isBonusesUsed = false,
        onBonusesUsedChange = {},
        onChangeOrderItem = {},
        fullOrder = listOf(
            FullOrder(
                burger = burgerMenuList[0],
                souses = listOf(
                    SelectedSouse(souseMenuList[0], 2)
                ),
                drinks = listOf(
                    SelectedDrink(drinkMenuList[0], 1)
                )
            ),
            FullOrder(
                burger = burgerMenuList[1],
                souses = listOf(
                    SelectedSouse(souseMenuList[1], 3)
                ),
                drinks = listOf(
                    SelectedDrink(drinkMenuList[1], 2)
                )
            )
        ),
        onPlusOrderClick ={ },
        onMinusOrderClick = {}
    )

}