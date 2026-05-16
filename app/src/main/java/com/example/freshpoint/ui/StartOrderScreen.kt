package com.example.freshpoint.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.zIndex
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshpoint.model.OrderType
import com.example.freshpoint.model.OrderUiState

@Composable
fun StartOrderScreen(
    uiState: OrderUiState,
    restaurants: List<String>,
    onDeliveryClick: () -> Unit,
    onRestaurantClick: () -> Unit,
    onDeliveryAddressSaved: () -> Unit,
    onDeliveryAddressChange: (String) -> Unit,
    onRestaurantSelected: (String) -> Unit,
    saveRestAddress: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var expandedCard by rememberSaveable {
        mutableStateOf<OrderType?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        bonusCard(
            bonuses = uiState.bonuses,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            val spacing = 12.dp
            val collapsedWidth = (maxWidth - spacing) / 2

            val deliveryExpanded = expandedCard == OrderType.DELIVERY
            val restaurantExpanded = expandedCard == OrderType.RESTAURANT

            inRestoranCard(
                expanded = restaurantExpanded,
                restaurants = restaurants,
                selectedRestaurantAddress = uiState.selectedRestaurantAddress,
                onRestaurantSelected = onRestaurantSelected,
                saveRestAddress = saveRestAddress,
                isRestAddressSaved = uiState.isRestaurantAddressSaved,
                onClick = {
                    expandedCard =
                        if (restaurantExpanded) {
                            null
                        } else {
                            OrderType.RESTAURANT
                        }

                    onRestaurantClick()
                },
                modifier = Modifier
                    .width(
                        if (restaurantExpanded) {
                            maxWidth
                        } else {
                            collapsedWidth
                        }
                    )
                    .height(
                        if (restaurantExpanded) {
                            220.dp
                        } else {
                            80.dp
                        }
                    )
                    .align(Alignment.TopEnd)
                    .zIndex(
                        if (restaurantExpanded) {
                            1f
                        } else {
                            0f
                        }
                    )
            )

            diliveryCard(
                expanded = deliveryExpanded,
                showDeliveryAddress = uiState.showDeliveryAddress,
                deliveryAddress = uiState.deliveryAddress,
                onDeliveryAddressChange = onDeliveryAddressChange,
                onDeliveryAddressSaved = onDeliveryAddressSaved,
                isDeliveryAddressSaved = uiState.isDeliveryAddressSaved,
                onClick = {
                    expandedCard =
                        if (deliveryExpanded) {
                            null
                        } else {
                            OrderType.DELIVERY
                        }

                    onDeliveryClick()
                },
                modifier = Modifier
                    .width(
                        if (deliveryExpanded) {
                            maxWidth
                        } else {
                            collapsedWidth
                        }
                    )
                    .height(
                        if (deliveryExpanded) {
                            220.dp
                        } else {
                            80.dp
                        }
                    )
                    .align(Alignment.TopStart)
                    .zIndex(
                        if (deliveryExpanded) {
                            1f
                        } else {
                            0f
                        }
                    )
            )
        }
    }
}

@Composable
fun diliveryCard(
    expanded: Boolean,
    deliveryAddress: String,
    showDeliveryAddress: String,
    isDeliveryAddressSaved: Boolean,
    onDeliveryAddressChange: (String) -> Unit,
    onDeliveryAddressSaved: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        LaunchedEffect(expanded) {
            if (expanded) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (expanded) {
                if (isDeliveryAddressSaved) {
                    Box(
                        modifier= Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ){
                        Icon(
                            imageVector = Icons.Default.CloudDone,
                            contentDescription = "Адрес сохранён",
                            tint = Color.DarkGray
                        )
                    }
                }
            }
            Text(text = "Доставка")

            if (expanded) {

                Spacer(modifier = Modifier.height(16.dp))


                OutlinedTextField(
                    value = deliveryAddress,
                    onValueChange = { newAddress ->
                        onDeliveryAddressChange(newAddress)
                    },
                    label = {
                        Text("Адрес доставки")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .focusRequester(focusRequester),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (deliveryAddress.isNotBlank()) {
                                onDeliveryAddressSaved()
                            }

                            focusManager.clearFocus()
                            keyboardController?.hide()
                        }
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (isDeliveryAddressSaved){
                    Text("Адрес доставки: "+ showDeliveryAddress)
                }
            }
        }
    }
}

@Composable
fun inRestoranCard(
    expanded: Boolean,
    restaurants: List<String>,
    isRestAddressSaved: Boolean,
    saveRestAddress: () -> Unit,
    selectedRestaurantAddress: String,
    onRestaurantSelected: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            if (expanded && isRestAddressSaved) {
                Icon(
                    imageVector = Icons.Default.CloudDone,
                    contentDescription = "Адрес сохранён",
                    tint = Color.DarkGray,
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "В ресторане")

                if (expanded) {
                    Spacer(modifier = Modifier.height(16.dp))

                    restaurants.forEach { restaurant ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedRestaurantAddress == restaurant,
                                onClick = {
                                    onRestaurantSelected(restaurant)
                                    saveRestAddress()
                                }
                            )

                            Text(text = restaurant)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun bonusCard(
    bonuses: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row {
                Column {
                    Text("Ваши бонусы:")
                    Text("$bonuses бонусов")
                }

                Image(
                    imageVector = Icons.Default.CurrencyBitcoin,
                    contentDescription = null
                )
            }

            Text("1 бонус = 1 рубль. Списание доступно от 300 ₽.")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartOrderScreenPreview() {
    FreshPointTheme {
        StartOrderScreen(
            uiState = OrderUiState(
                bonuses = 0,
                orderType = null,
                deliveryAddress = "",
                selectedRestaurantAddress = ""
            ),
            restaurants = listOf(
                "ул. Ленина, 10",
                "ул. Пушкина, 25",
                "ТЦ Центральный, 2 этаж"
            ),
            onDeliveryClick = {},
            onRestaurantClick = {},
            onDeliveryAddressChange = {},
            onRestaurantSelected = {},
            onDeliveryAddressSaved = {},
            saveRestAddress = {}
        )
    }
}