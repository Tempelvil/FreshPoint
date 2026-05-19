package com.example.freshpoint.ui

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.RestaurantMenu
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshpoint.model.FullOrder
import com.example.freshpoint.model.OrderType
import com.example.freshpoint.model.Restaurant
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
    fullOrder: List<FullOrder>
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
        
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            OrderDeliveryOrRestCard(
                selectedDelivery =selectedDeliveryAdres,
                selectedRestoraunt = selectedRestorauntAdres,
                orderType = orderType,
                onOrderTypeChange = onChangeTypeOrder,
                onChangeOrderClick = onChangeOrderClick
            )
            Spacer(modifier= Modifier.height(14.dp))

            bonusesCard(
                bonuses = bonuses,
                orderPrice = orderPrice,
                isBonusesUsed = isBonusesUsed,
                onBonusesUsedChange = onBonusesUsedChange,
            )
            
            LazyVerticalGrid(
                modifier = Modifier.padding(innerPadding).
                fillMaxSize().padding(start = 24.dp).padding(end = 24.dp).padding(top=24.dp),
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {


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
                .fillMaxSize().padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp).weight(1f),
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
            .padding(start = 12.dp, end = 12.dp,top=12.dp, bottom = 2.dp)
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
        fullOrder = emptyList(),
    )

}